package univ.tln.i243.groupe1;

import com.jme3.app.SimpleApplication;
import com.jme3.material.Material;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.math.ColorRGBA;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Cylinder;
import com.jme3.scene.shape.Sphere;
import univ.tln.i243.groupe1.daos.EnregistrementDao;
import univ.tln.i243.groupe1.entitees.Enregistrement;
import univ.tln.i243.groupe1.entitees.Frame;
import univ.tln.i243.groupe1.entitees.Jointure;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.*;

/** Sample 1 - how to get started with the most simple JME 3 application.
 * Display a blue 3D cube and view from all sides by
 * moving the mouse and pressing the WASD keys. */
public class JME extends SimpleApplication {

        private float rayonSphere = 0.01f;
        private EntityManager em = Persistence.createEntityManagerFactory("bddlocal").createEntityManager();
        private Enregistrement enregistrement;

        private int numero = 0;
        private long compteur = 1000/30;
        private long tempsTotal;

    public static void main(String[] args){
        JME app = new JME();
        app.start(); // start the game
    }

    @Override
    public void simpleInitApp() {
            viewPort.setBackgroundColor(new ColorRGBA(0.7f,0.8f,1f,1f));
            flyCam.setMoveSpeed(flyCam.getMoveSpeed()*10);
            cam.setLocation(new Vector3f(0.026f, 0.016f, 3.10f));

            enregistrement = new EnregistrementDao(em).rechercher(1);
            Frame frame = enregistrement.getFrames().get(0);
            List<Jointure> listeJointures = frame.getJointures();

            initListeGeometriesJointures(listeJointures);
    }

    @Override
    public void simpleUpdate(float tpf) {

        long tempsActuel = System.currentTimeMillis();

        if (tempsActuel - tempsTotal >= compteur) {
            if(numero < enregistrement.getFrames().size()) {
                rootNode.detachAllChildren();
                Frame frame = enregistrement.getFrames().get(numero);
                initListeGeometriesJointures(frame.getJointures());
                numero++;
            }
            tempsTotal = tempsActuel; // Reset to now.
        }
    }

    public List<Geometry> initListeGeometriesJointures(List<Jointure> listeJointure){
            List<Geometry> listeGeometriesJointures = new ArrayList<>();
            Node geometriesJointures = new Node();

            Sphere jointureSphere = new Sphere(20, 20, rayonSphere);
            Material mat = new Material(assetManager,
                    "Common/MatDefs/Light/Lighting.j3md");
            mat.setColor("Ambient", ColorRGBA.Cyan);

            for(int i = 0; i < listeJointure.size(); i++){

                    // Jointure
                    listeGeometriesJointures.add(new Geometry("Jointure_" + i, jointureSphere));
                    listeGeometriesJointures.get(i).setMaterial(mat);
                    listeGeometriesJointures.get(i).setLocalTranslation(new Vector3f(-listeJointure.get(i).getX(), -listeJointure.get(i).getY(), listeJointure.get(i).getZ()).normalize());
                    geometriesJointures.attachChild(listeGeometriesJointures.get(i));
            }

            rootNode.attachChild(geometriesJointures);
            return listeGeometriesJointures;
    }

    public List<Geometry> initGeometriesCylindres(List<Geometry> listeGeometriesJointures, List<Jointure> listeJointures){

        List<Geometry> listeGeometriesCylindres = new ArrayList<>();

        return listeGeometriesCylindres;
    }
}
