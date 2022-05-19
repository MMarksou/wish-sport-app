package univ.tln.i243.groupe1.campare;


public class Angle {
    public double calculateAngle(double x1, double y1, double z1,
                                 double x2, double y2, double z2,
                                 double x3, double y3, double z3)
    {

        // Trouver le rapport de direction de la ligne AB
        double ABx = x1 - x2;
        double ABy = y1 - y2;
        double ABz = z1 - z2;

        // Trouver le rapport de direction de la ligne BC
        double BCx = x3 - x2;
        double BCy = y3 - y2;
        double BCz = z3 - z2;

        // Trouver le produit scalair des lignes AB & BC
        double dotProduct = ABx * BCx +
                ABy * BCy +
                ABz * BCz;

        // Trouver la grandeur des AB & BC
        double magnitudeAB = ABx * ABx +
                ABy * ABy +
                ABz * ABz;
        double magnitudeBC = BCx * BCx +
                BCy * BCy +
                BCz * BCz;

        // Trouver le cosinus de l'angle formé par AB et BC
        double angle = dotProduct;
        angle /= Math.sqrt(magnitudeAB * magnitudeBC);

        // Trouver l'angle en radian
        angle = (angle * 180) / 3.14;

        // print de l'angle
        //System.out.printf("\n"+ Math.abs(angle) + "\n");

        return angle;
    }
}

