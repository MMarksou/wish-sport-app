package univ.tln.i243.groupe1;

import com.jme3.app.SimpleApplication;
import com.jme3.font.BitmapText;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Cylinder;
import com.jme3.scene.shape.Sphere;
import com.jme3.system.AppSettings;
import javafx.util.Pair;
import univ.tln.i243.groupe1.entitees.Frame;
import univ.tln.i243.groupe1.entitees.Jointure;
import univ.tln.i243.groupe1.entitees.Liaisons;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JMEComparaison extends SimpleApplication {

    private float rayonSphere = 0.015f;

    private int numero = 0;
    private long compteur = 1000/30;
    private long tempsTotal;

    private Liaisons liaisons = new Liaisons();
    private Map<String, Geometry> carteGeometriesJointures = new HashMap<>();

    private static List<Frame> listeFrameRefActive;
    private static List<Map<String, String>> listeComparaisonActive;

    private ColorRGBA couleurSphere = ColorRGBA.Black;

    private Map<String, ColorRGBA> listeChangementsJointures = new HashMap<>();

    /**
     * Fonction principale qui démarre une fenêtre jMonkey.
     * @param listeFrame Une liste d'objet Frame
     */
    public static void main(List<Frame> listeFrame, List<Map<String, String>> listeComparaison){
        JMEComparaison.listeFrameRefActive = listeFrame;
        JMEComparaison.listeComparaisonActive = listeComparaison;
        JMEComparaison app = new JMEComparaison();
        app.setShowSettings(false);
        AppSettings settings = new AppSettings(true);
        settings.setResolution(1200, 720);
        settings.setFrameRate(30);
        app.setSettings(settings);
        app.setDisplayStatView(false);
        app.start();
    }

    /**
     * Redéfinition de la fonction d'initialisation qui crée le squelette de la 1ère frame.
     */
    @Override
    public void simpleInitApp() {
        viewPort.setBackgroundColor(new ColorRGBA(0.1f,0.8f,1f,1f));
        flyCam.setEnabled(false);
        cam.setLocation(new Vector3f(0.026f, 0.016f, 3.10f));

        Frame frame = listeFrameRefActive.get(0);
        List<Jointure> listeJointures = frame.getJointures();

        initGeometriesCylindres(initListeGeometriesJointures(listeJointures));
    }

    /**
     * Redéfinition de la fonction mise à jour pour l'animation du squelette.
     * @param tpf le compteur de jMonkey
     */
    @Override
    public void simpleUpdate(float tpf) {

        long tempsActuel = System.currentTimeMillis();

        if (tempsActuel - tempsTotal >= compteur) {
            if(numero < listeFrameRefActive.size()) {
                rootNode.detachAllChildren();
                Frame frame = listeFrameRefActive.get(numero);
                initGeometriesCylindres(initListeGeometriesJointures(frame.getJointures()));
                verifierComparaison(numero);
                numero++;
            }
            tempsTotal = tempsActuel;
        }
    }

    /**
     * Fonction de la construction des jointures du squelette.
     * @param listeJointure liste des jointures
     * @return la liste des géométries des jointures
     */
    public Map<String, Geometry> initListeGeometriesJointures(List<Jointure> listeJointure){
        Node geometriesJointures = new Node();

        Sphere jointureSphere = new Sphere(20, 20, rayonSphere);

        for(int i = 0; i < listeJointure.size(); i++){

            Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");

            if(listeChangementsJointures.keySet().contains(listeJointure.get(i).getNom())){
                mat.setColor("Color", listeChangementsJointures.get(listeJointure.get(i).getNom()));
            } else {
                mat.setColor("Color", couleurSphere);
            }

            carteGeometriesJointures.put(listeJointure.get(i).getNom(), new Geometry(listeJointure.get(i).getNom(), jointureSphere));
            carteGeometriesJointures.get(listeJointure.get(i).getNom()).setMaterial(mat);
            carteGeometriesJointures.get(listeJointure.get(i).getNom()).setLocalTranslation(new Vector3f(-listeJointure.get(i).getX(), -listeJointure.get(i).getY(), listeJointure.get(i).getZ()).normalize());

            geometriesJointures.attachChild(carteGeometriesJointures.get(listeJointure.get(i).getNom()));
        }

        rootNode.attachChild(geometriesJointures);
        return carteGeometriesJointures;
    }

    /**
     * Fonction qui génère les os du squelette
     * @param carteGeometriesJointures la liste des géométries des jointures
     * @return la liste des Géométries des Cylindres
     */
    public List<Geometry> initGeometriesCylindres(Map<String, Geometry> carteGeometriesJointures){

        List<Geometry> listeGeometriesCylindres = new ArrayList<>();

        for (Pair<String, String> paires: liaisons.getListeLiaisons()) {
            //la taille du cylindre est en fonction de la distance des deux jointures
            listeGeometriesCylindres.add(calcCylindre(carteGeometriesJointures.get(paires.getKey()), carteGeometriesJointures.get(paires.getValue())));
        }

        return listeGeometriesCylindres;
    }

    public void verifierComparaison(int numero){
        String repetition;
        for (Map<String, String> comparaisons : listeComparaisonActive) {
            for (String clef: comparaisons.keySet()) {
                String[] valeurs = clef.split(",");
                repetition = valeurs[4];
                if(Integer.valueOf(valeurs[3]) == numero){
                    guiNode.detachAllChildren();
                    if(listeChangementsJointures.containsKey(valeurs[1])) {
                        listeChangementsJointures.remove(valeurs[1]);
                    }
                    if (comparaisons.get(clef).equals("tres mal fait")) {
                        listeChangementsJointures.put(valeurs[1], ColorRGBA.Red);
                    } else if (comparaisons.get(clef).equals("pas mal fait")) {
                        listeChangementsJointures.put(valeurs[1], ColorRGBA.Orange);
                    } else if (comparaisons.get(clef).equals("bien fait")) {
                        listeChangementsJointures.put(valeurs[1], ColorRGBA.Green);
                    }
                }
                /*BitmapText nbRep = new BitmapText(guiFont, false);
                nbRep.setSize(guiFont.getCharSet().getRenderedSize() * 2);
                nbRep.setText(String.valueOf("Répétition : " + repetition));
                nbRep.setLocalTranslation(
                        settings.getWidth() / 6 - nbRep.getLineWidth()/2,
                        settings.getHeight() / 6 + nbRep.getLineHeight()/2, 0);
                nbRep.setColor(ColorRGBA.Black);
                guiNode.attachChild(nbRep);*/
            }
        }
    }

    /**
     * Fonction qui fabrique un cylindre représentant un os.
     * @param geo1 point de départ du cylindre
     * @param geo2 point final du cylindre
     * @return la géométrie du cylindre
     */
    public Geometry calcCylindre(Geometry geo1, Geometry geo2){
        Cylinder cylindre = new Cylinder(100, 100 ,0.005f, geo1.getLocalTranslation().distance(geo2.getLocalTranslation()), true);

        Geometry resultat = new Geometry("test", cylindre);
        Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        resultat.setMaterial(mat);

        resultat.setLocalTranslation(geo1.getLocalTranslation().add(geo2.getLocalTranslation()).divide(2));
        resultat.lookAt(geo1.getLocalTranslation(),geo2.getLocalTranslation());

        rootNode.attachChild(resultat);

        return resultat;
    }
}