package univ.tln.i243.groupe1;

import com.jme3.app.SimpleApplication;
import com.jme3.material.Material;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.math.ColorRGBA;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Cylinder;
import com.jme3.scene.shape.Sphere;
import com.jme3.system.AppSettings;
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
        app.setShowSettings(false);
        AppSettings settings = new AppSettings(true);
        settings.setFrameRate(30);
        settings.setResolution(1200, 720);
        app.setSettings(settings);
        app.start();
    }

    @Override
    public void simpleInitApp() {
            viewPort.setBackgroundColor(new ColorRGBA(0.1f,0.8f,1f,1f));
            flyCam.setMoveSpeed(flyCam.getMoveSpeed()*10);
            cam.setLocation(new Vector3f(0.026f, 0.016f, 3.10f));

            enregistrement = new EnregistrementDao(em).rechercher(1);
            Frame frame = enregistrement.getFrames().get(0);
            List<Jointure> listeJointures = frame.getJointures();

            initGeometriesCylindres(initListeGeometriesJointures(listeJointures));
    }

    @Override
    public void simpleUpdate(float tpf) {

        long tempsActuel = System.currentTimeMillis();

        if (tempsActuel - tempsTotal >= compteur) {
            if(numero < enregistrement.getFrames().size()) {
                rootNode.detachAllChildren();
                Frame frame = enregistrement.getFrames().get(numero);
                initGeometriesCylindres(initListeGeometriesJointures(frame.getJointures()));
                numero++;
            }
            tempsTotal = tempsActuel; // Reset to now.
        }
    }

    public Map<String,Geometry> initListeGeometriesJointures(List<Jointure> listeJointure){
            Map<String, Geometry> carteGeometriesJointures = new HashMap<>();

            Node geometriesJointures = new Node();

            Sphere jointureSphere = new Sphere(20, 20, rayonSphere);
            Material mat = new Material(assetManager,
                    "Common/MatDefs/Light/Lighting.j3md");
            mat.setColor("Ambient", ColorRGBA.Cyan);

            for(int i = 0; i < listeJointure.size(); i++){

                    // Jointure
                    carteGeometriesJointures.put(listeJointure.get(i).getNom(), new Geometry("Jointure_" + i, jointureSphere));
                    carteGeometriesJointures.get(listeJointure.get(i).getNom()).setMaterial(mat);
                    carteGeometriesJointures.get(listeJointure.get(i).getNom()).setLocalTranslation(new Vector3f(-listeJointure.get(i).getX(), -listeJointure.get(i).getY(), listeJointure.get(i).getZ()).normalize());

                    geometriesJointures.attachChild(carteGeometriesJointures.get(listeJointure.get(i).getNom()));
            }

            rootNode.attachChild(geometriesJointures);
            return carteGeometriesJointures;
    }

    public List<Geometry> initGeometriesCylindres(Map<String, Geometry> carteGeometriesJointures){

        List<Geometry> listeGeometriesCylindres = new ArrayList<>();

        listeGeometriesCylindres.add(calcCylindre(carteGeometriesJointures.get("K4ABT_JOINT_HEAD"), carteGeometriesJointures.get("K4ABT_JOINT_NECK")));
        listeGeometriesCylindres.add(calcCylindre(carteGeometriesJointures.get("K4ABT_JOINT_NECK"), carteGeometriesJointures.get("K4ABT_JOINT_SPINE_CHEST")));
        listeGeometriesCylindres.add(calcCylindre(carteGeometriesJointures.get("K4ABT_JOINT_NECK"), carteGeometriesJointures.get("K4ABT_JOINT_CLAVICLE_LEFT")));
        listeGeometriesCylindres.add(calcCylindre(carteGeometriesJointures.get("K4ABT_JOINT_CLAVICLE_LEFT"), carteGeometriesJointures.get("K4ABT_JOINT_SHOULDER_LEFT")));
        listeGeometriesCylindres.add(calcCylindre(carteGeometriesJointures.get("K4ABT_JOINT_NECK"), carteGeometriesJointures.get("K4ABT_JOINT_CLAVICLE_RIGHT")));



        return listeGeometriesCylindres;
    }

    public Geometry calcCylindre(Geometry geo1, Geometry geo2){
        Cylinder cylindre = new Cylinder(100, 100 ,0.005f, geo1.getLocalTranslation().distance(geo2.getLocalTranslation()), true);

        Geometry resultat = new Geometry("test", cylindre);
        Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        //mat.setColor("", new ColorRGBA(1f,1f,1f,1f));
        resultat.setMaterial(mat);

        resultat.setLocalTranslation(geo1.getLocalTranslation().add(geo2.getLocalTranslation()).divide(2));
        resultat.lookAt(geo1.getLocalTranslation(),geo2.getLocalTranslation());

        rootNode.attachChild(resultat);

        return resultat;
    }
}
