module com.school.sample {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;


    opens com.school.sample to javafx.fxml;
    exports com.school.sample;
    exports com.school.sample.controller;
    opens com.school.sample.controller to javafx.fxml;
    exports com.school.sample.JVclass;
    opens com.school.sample.JVclass to javafx.fxml;
    exports com.school.sample.connection;
    opens com.school.sample.connection to javafx.fxml;
}