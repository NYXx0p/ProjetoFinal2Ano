module com.school.sample {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.school.sample to javafx.fxml;
    exports com.school.sample;
}