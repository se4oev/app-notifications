package ru.se4oev.appnotifications;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

public class HelloController {

    @FXML Button btn;
    @FXML ImageView notificationsImage;

    NotificationsStage notificationsStage = new NotificationsStage();

    @FXML
    public void initialize() {
        notificationsImage.setOnMouseClicked(e -> {
            if (e.getButton().equals(MouseButton.PRIMARY))
                openNotificationsStage();
        });
    }

    private void openNotificationsStage() {
        if (notificationsStage.isShowing())
            notificationsStage.hide();
        else
            notificationsStage.show();
    }

    protected void onHelloButtonClick() {
        Notifications notifications = Notifications.create()
                .title("Download Complete!")
                .text("Saved to /home/downloads")
                .graphic(null)
                .hideAfter(Duration.seconds(5))
                .position(Pos.BOTTOM_RIGHT)
                .onAction(e -> {})
                .darkStyle();

        notifications.show();
    }

}