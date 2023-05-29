module com.vexed.vexed {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires java.sql;

    opens com.comp2059.app to javafx.fxml;
    exports com.comp2059.app;
    exports com.comp2059.app.Color;
    opens com.comp2059.app.Color to javafx.fxml;
    opens com.comp2059.app.controller to javafx.fxml;
    exports com.comp2059.app.controller;
}