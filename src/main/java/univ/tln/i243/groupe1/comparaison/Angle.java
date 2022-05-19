package univ.tln.i243.groupe1.comparaison;


public class Angle {

    public double calculateAngle(double x1, double y1, double z1,
                                 double x2, double y2, double z2,
                                 double x3, double y3, double z3)
    {

        // Trouver le rapport de direction de la ligne AB
        double xAB = x1 - x2;
        double yAB = y1 - y2;
        double zAB = z1 - z2;

        // Trouver le rapport de direction de la ligne BC
        double xBC = x3 - x2;
        double yBC = y3 - y2;
        double zBC = z3 - z2;

        // Trouver le produit scalair des lignes AB & BC
        double dotProduct = xAB * xBC +
                yAB * yBC +
                zAB * zBC;

        // Trouver la grandeur des AB & BC
        double magnitudeAB = xAB * xAB +
                yAB * yAB +
                zAB * zAB;
        double magnitudeBC = xBC * xBC +
                yBC * yBC +
                zBC * zBC;

        // Trouver le cosinus de l'angle formé par AB et BC
        double angle = dotProduct;
        angle /= Math.sqrt(magnitudeAB * magnitudeBC);

        // Trouver l'angle en radian
        angle = (angle * 180) / 3.14;

        return angle;
    }
}

