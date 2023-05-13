module yugioh {
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.controls;
    requires java.sql;
    requires lombok;
    requires com.fasterxml.jackson.databind;
    requires javafx.web;
    requires org.controlsfx.controls;
    requires annotations;
    requires org.slf4j;
    opens com.example.yugioh to javafx.fxml;

    exports com.example.yugioh.application;
    exports com.example.yugioh.controllers;

    opens com.example.yugioh.application to javafx.fxml;
    opens com.example.yugioh.controllers to javafx.fxml;
}