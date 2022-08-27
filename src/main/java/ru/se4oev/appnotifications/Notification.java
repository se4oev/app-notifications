package ru.se4oev.appnotifications;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

/**
 * Created by karpenko on 25.08.2022.
 * Description:
 */
public class Notification extends StackPane {

    private Label cross = new Label();
    private Text note = new Text();

    private Runnable onRemove = () -> {};

    public Notification(String text, NotificationType type) {
        getStyleClass().add("notification");

        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(note);
        cross.setText("Убрать Х");
        BorderPane.setAlignment(cross, Pos.TOP_RIGHT);
        borderPane.setTop(cross);

        note.setText(text);
        getChildren().add(borderPane);
        initialize();
    }

    private void initialize() {
        cross.getStyleClass().add("small-hyperlink-label");
        cross.setOnMouseClicked(e -> onRemove.run());
    }

    public void setOnRemove(Runnable onRemove) {
        this.onRemove = onRemove;
    }

}
