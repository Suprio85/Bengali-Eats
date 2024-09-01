module  java{
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;

    opens Client.myapp to javafx.fxml;
    exports Client.myapp;
    exports Controllers;
    exports Models;

}

