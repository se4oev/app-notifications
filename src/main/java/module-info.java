module ru.se4oev.appnotifications {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens ru.se4oev.appnotifications to javafx.fxml;
    exports ru.se4oev.appnotifications;
}