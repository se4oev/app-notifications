package ru.se4oev.appnotifications;

import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

/**
 * Created by karpenko on 25.08.2022.
 * Description:
 */
public class Notification extends StackPane {

    private ImageView cross = new ImageView();

    public Notification(String text, NotificationType type) {
        Text text1 = new Text(text);
        getChildren().add(text1);
        initialize();
    }

    private void initialize() {
        cross.getStyleClass().add("notification-cross");
    }

}
