module univ.tln.I243.groupe1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.annotation;
    requires lombok;
    requires eclipselink;
    requires java.persistence;
    exports univ.tln.i243.groupe1;

    opens univ.tln.i243.groupe1 to javafx.fxml, javafx.base;
    opens univ.tln.i243.groupe1.controller to javafx.base, javafx.fxml;
    exports univ.tln.i243.groupe1.controller;
}