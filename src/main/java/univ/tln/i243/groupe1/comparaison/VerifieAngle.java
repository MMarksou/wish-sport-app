package univ.tln.i243.groupe1.comparaison;

import univ.tln.i243.groupe1.daos.EnregistrementDao;
import univ.tln.i243.groupe1.entitees.Enregistrement;
import univ.tln.i243.groupe1.entitees.Frame;
import univ.tln.i243.groupe1.entitees.Jointure;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.Math.*;

public class VerifieAngle {
    static EntityManager em = Persistence.createEntityManagerFactory("bddlocal").createEntityManager();
    static double a = 0;
    static double aPrecedente = 0;
    static double x1 = 0;
    static double x2 = 0;
    static double x3 = 0;
    static double y1 = 0;
    static double y2 = 0;
    static double y3 = 0;
    static double z1 = 0;
    static double z2 = 0;
    static double z3 = 0;
    static String etat;
    static List<Integer> frameValue =new ArrayList<>();
    static EnregistrementDao enregistrementDao = new EnregistrementDao(em);
    static Angle angle = new Angle();

    /**
     * Methode qui permet de trouver l'angle maximale lors du mouvement
     * return int : angle maximale
     **/
    public static int maxAngle(double a0, List<Frame> listeFrame, String j1, String j2, String j3, int frame, int direction) {

        int frameNumber = listeFrame.size();

        double a1 = a0;
        int framefin = 0;
        a = a0;
        aPrecedente = a;
        while (frameNumber>frame){
            List<Jointure> jointuresList3 = listeFrame.get(frame).getJointures();

            for (Jointure jointure : jointuresList3) {
                if (jointure.getNom().equals(j1)) {
                    x1 = jointure.getX();
                    y1 = jointure.getY();
                    z1 = jointure.getZ();
                }
                else if (jointure.getNom().equals(j2)) {
                    x2 = jointure.getX();
                    y2 = jointure.getY();
                    z2 = jointure.getZ();
                }
                else if (jointure.getNom().equals(j3)) {
                    x3 = jointure.getX();
                    y3 = jointure.getY();
                    z3 = jointure.getZ();
                }
            }

            aPrecedente = a;
            a = angle.calculateAngle(x1, y1, z1, x2, y2, z2, x3, y3, z3);
            if (direction*(a-a1)>0) {
                a1 = a;
                framefin = frame;
            }else if(direction*(a-a1)<-20){
                return framefin;
            }
            frame++;
        }
        return framefin;
    }

    /**
     * Methode qui permet de calculer des angles en prennant les coordonnees des trois jointures
     * qui forment l'angle et le nom de l'enregistremnt
     * return List<Double> : la liste des angles
     **/
    public static List<Double> calculerAngle(List<Frame> listeFrame, int repNumber, String j1, String j2, String j3) {
        List<Double> angles = new ArrayList<>();
        List<Jointure> jointuresList = listeFrame.get(1).getJointures();

        int frameNumber = listeFrame.size();

        /** recuperer les coordonnees des jointures pour calculer l'angle en etat initale **/
        for (Jointure jointure : jointuresList) {
            if (jointure.getNom().equals(j1)) {
                x1 = jointure.getX();
                y1 = jointure.getY();
                z1 = jointure.getZ();
            }
            else if (jointure.getNom().equals(j2)) {
                x2 = jointure.getX();
                y2 = jointure.getY();
                z2 = jointure.getZ();
            }
            else if (jointure.getNom().equals(j3)) {
                x3 = jointure.getX();
                y3 = jointure.getY();
                z3 = jointure.getZ();
            }
        }

        /** Calculer de l'angle en etat initiale **/
        double a0 = angle.calculateAngle(x1, y1, z1, x2, y2, z2, x3, y3, z3);
        int direction=0;
        int iframe=0;
        do{

            List<Jointure> jointuresList2 = listeFrame.get(iframe).getJointures();

            for (Jointure jointure : jointuresList2) {
                if (jointure.getNom().equals(j1)) {
                    x1 = jointure.getX();
                    y1 = jointure.getY();
                    z1 = jointure.getZ();
                }
                else if (jointure.getNom().equals(j2)) {
                    x2 = jointure.getX();
                    y2 = jointure.getY();
                    z2 = jointure.getZ();
                }
                else if (jointure.getNom().equals(j3)) {
                    x3 = jointure.getX();
                    y3 = jointure.getY();
                    z3 = jointure.getZ();
                }
            }

            /** calcul de l'angle apres 30 frames  **/
            double a1 = angle.calculateAngle(x1, y1, z1, x2, y2, z2, x3, y3, z3);

            /**Verifier si l'angle augmente ou diminue**/
            if(abs(a1-a0)>15){
                if (a0 < a1) {
                    direction = 1;
                } else {
                    direction = -1;
                }
            }else{
                iframe+=5;
            }
        }while (direction==0 && iframe < frameNumber);
        int frame =1;
        /** Continuer a calculer les angles en fonction de nombre de repetition et frame **/
        int rep = 0;
        while (rep < repNumber && frame>0) {
            angles.add(a0);
            frame = maxAngle(a0, listeFrame, j1, j2, j3, frame, direction);
            frameValue.add(frame);
            jointuresList = listeFrame.get(frame).getJointures();
            for (Jointure jointure : jointuresList) {
                if (jointure.getNom().equals(j1)) {
                    x1 = jointure.getX();
                    y1 = jointure.getY();
                    z1 = jointure.getZ();
                }
                else if (jointure.getNom().equals(j2)) {
                    x2 = jointure.getX();
                    y2 = jointure.getY();
                    z2 = jointure.getZ();
                }
                else if (jointure.getNom().equals(j3)) {
                    x3 = jointure.getX();
                    y3 = jointure.getY();
                    z3 = jointure.getZ();
                }
            }

            double a1 = angle.calculateAngle(x1, y1, z1, x2, y2, z2, x3, y3, z3);
            angles.add(a1);

            frame = maxAngle(a1, listeFrame, j1, j2, j3, frame, -1*direction);
            frameValue.add(frame);
            jointuresList = listeFrame.get(frame).getJointures();

            for (Jointure jointure : jointuresList) {
                if (jointure.getNom().equals(j1)) {
                    x1 = jointure.getX();
                    y1 = jointure.getY();
                    z1 = jointure.getZ();
                }
                else if (jointure.getNom().equals(j2)) {
                    x2 = jointure.getX();
                    y2 = jointure.getY();
                    z2 = jointure.getZ();
                }
                else if (jointure.getNom().equals(j3)) {
                    x3 = jointure.getX();
                    y3 = jointure.getY();
                    z3 = jointure.getZ();
                }
            }

            a0 = angle.calculateAngle(x1, y1, z1, x2, y2, z2, x3, y3, z3);
            rep++;

        }
        return angles;
    }

    public static Map<String, String> lancerComparaison(String j1, String j2, String j3, Double angleDebut, Double angleFin, Enregistrement enregistrementCible) {
        int y=0;
        HashMap<String, String> anglesValues = new HashMap<>();

        List<Double> angleReferent;
        List<Double> angleValue;
        int repNumber = enregistrementCible.getRepetition();

        //for (int i = 0; i < listAngles.size(); i += 3) {

        angleReferent = calculerAngle(enregistrementCible.getFrames(), enregistrementCible.getRepetition(), j1, j2, j3);

        angleValue = calculerAngle(enregistrementCible.getFrames(), enregistrementCible.getRepetition(), j1, j2, j3);


            for (int j = 0; j < 2 * repNumber; j += 2) {
                double dif1 = Math.abs(angleReferent.get(0) - angleValue.get(j));
                double dif2 = Math.abs(angleReferent.get(1) - angleValue.get(j + 1));
                if ((dif1 + dif2) / 2 <= 15.0)
                    etat = "bien fait";
                if ((dif1 + dif2) / 2 > 15.0 && (dif1 + dif2) / 2 <= 30.0)
                    etat = "pas mal fait";
                if ((dif1 + dif2) / 2 > 40.0)
                    etat = "tres mal fait";
                anglesValues.put("" + j1 + "," + j2 + "," + j3 + "," + frameValue.get(y), etat);
                y++;
            }
        //}
        for (String name: anglesValues.keySet()) {
            String key = name;
            String value = anglesValues.get(name);
            System.out.println(key + " " + value);
        }
        return anglesValues;
    }

    /*public static void main(String[] args) {
//        List<String> listAngles = new ArrayList<>();
//
//        listAngles.add("EPAULE_DROITE");
//        listAngles.add("COUDE_DROIT");
//        listAngles.add("POIGNET_DROIT");
//
//        listAngles.add("EPAULE_GAUCHE");
//        listAngles.add("COUDE_GAUCHE");
//        listAngles.add("POIGNET_GAUCHE");
//
//        listAngles.add("HANCHE_DROITE");
//        listAngles.add("GENOUX_DROIT");
//        listAngles.add("CHEVILLE_DROITE");
//
//        listAngles.add("HANCHE_GAUCHE");
//        listAngles.add("GENOUX_GAUCHE");
//        listAngles.add("CHEVILLE_GAUCHE");
//
//        lancerComparaison(listAngles,"mouvement5","exercice5");


        List<String> listAngles = new ArrayList<>();

        listAngles.add("EPAULE_DROITE");
        listAngles.add("COUDE_DROIT");
        listAngles.add("POIGNET_DROIT");

        listAngles.add("EPAULE_GAUCHE");
        listAngles.add("COUDE_GAUCHE");
        listAngles.add("POIGNET_GAUCHE");

        listAngles.add("HANCHE_DROITE");
        listAngles.add("GENOUX_DROIT");
        listAngles.add("CHEVILLE_DROITE");

        listAngles.add("HANCHE_GAUCHE");
        listAngles.add("GENOUX_GAUCHE");
        listAngles.add("CHEVILLE_GAUCHE");

        List<Double> angleReferent;
        List<Double> angleValue;
        int repNumber = enregistrementDao.rechercherParNom("exercice5").getRepetition();

        for (int i = 0; i < listAngles.size(); i += 3) {

            angleReferent = calculerAngle("mouvement5", listAngles.get(i), listAngles.get(i + 1), listAngles.get(i + 2));

            angleValue = calculerAngle("exercice5", listAngles.get(i), listAngles.get(i + 1), listAngles.get(i + 2));

            for (int j = 0; j < angleValue.size(); j += 2) {
                double dif1 = Math.abs(angleReferent.get(0) - angleValue.get(j));
                double dif2 = Math.abs(angleReferent.get(1) - angleValue.get(j + 1));
                if ((dif1 + dif2) / 2 <= 10.0)
                    System.out.println("bien\n");
                if ((dif1 + dif2) / 2 > 10.0 && (dif1 + dif2) / 2 <= 25.0)
                    System.out.println("pas mal\n");
                if ((dif1 + dif2) / 2 > 25.0)
                    System.out.println("tres mal fait\n");
            }
        }
    }*/
}