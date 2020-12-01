module org.ciclo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires javafx.graphics;
    requires java.xml.bind;
    requires java.persistence;

    opens org.ciclo.model to mysql.connector.java;
    opens org.ciclo to javafx.fxml;
    exports org.ciclo to javafx.graphics;

    opens org.ciclo.controller to javafx.fxml;
    exports org.ciclo.controller;

}