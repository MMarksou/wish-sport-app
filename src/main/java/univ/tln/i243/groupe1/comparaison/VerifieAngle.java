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
    static List<Integer> repetition = new ArrayList<>();
    static EnregistrementDao enregistrementDao = new EnregistrementDao(em);
    static Angle angle = new Angle();

    /**
     * Methode qui permet de trouver la frame de l'angle maximale lors du mouvement en prenant la derniere frame avant l'execution du mouvement en contre direction
     * return int : frame equivalente a l 'angle
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
     * qui forment l'angle et le nom de list qui contient les valeurs de l'enregistremnt
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

            /** calcul de l'angle apres un certain nombre de frames pour s'avoir la direction de mouvement   **/
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
            /**ajout de a0 l angle de debut de mouvement aller**/
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
            /**ajout de a1 l 'angle de debut de mouvement retour**/
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
            repetition.add(rep);
        }
        return angles;
    }

    /**
     * Methode qui permet de comparer les angle calculer pour un enregistrement avec
     * les angles precalculé recuperer de la base de données d'un mouvement reference
     * return les 3 jointure qui contruisent l'angle ,la frame de debut et fin de mouvement ,score pour chaque repitition
     **/
    public static Map<String, String> lancerComparaison(String j1, String j2, String j3, Double angleDebut, Double angleFin, Enregistrement enregistrementCible) {
        int y=0;
        HashMap<String, String> anglesValues = new HashMap<>();

        List<Double> angleReferent;
        List<Double> angleValue;
        int repNumber = enregistrementCible.getRepetition();

        angleReferent = calculerAngle(enregistrementCible.getFrames(), enregistrementCible.getRepetition(), j1, j2, j3);

        angleValue = calculerAngle(enregistrementCible.getFrames(), enregistrementCible.getRepetition(), j1, j2, j3);

        /**assignation un score au repition en dependant de l'incertitude des angles **/
            for (int j = 0; j < 2 * repNumber; j += 2) {
                double dif1 = Math.abs(angleReferent.get(0) - angleValue.get(j));
                double dif2 = Math.abs(angleReferent.get(1) - angleValue.get(j + 1));
                if ((dif1 + dif2) / 2 <= 15.0)
                    etat = "bien fait";
                if ((dif1 + dif2) / 2 > 15.0 && (dif1 + dif2) / 2 <= 30.0)
                    etat = "pas mal fait";
                if ((dif1 + dif2) / 2 > 40.0)
                    etat = "tres mal fait";
                anglesValues.put("" + j1 + "," + j2 + "," + j3 + "," + frameValue.get(y) + "," + repetition.get(y), etat);
                y++;
            }
        return anglesValues;
    }
}