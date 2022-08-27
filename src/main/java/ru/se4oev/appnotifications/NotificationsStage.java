package ru.se4oev.appnotifications;

import javafx.animation.TranslateTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.*;
import javafx.util.Duration;

/**
 * Created by karpenko on 25.08.2022.
 * Description:
 */
public class NotificationsStage extends Stage {

    private AnchorPane rootPane = new AnchorPane();
    TranslateTransition openTransition = new TranslateTransition(new Duration(500), rootPane);
    TranslateTransition closeTransition = new TranslateTransition(new Duration(500), rootPane);
    private final Label clear = new Label("Очистить");
    private final Label close = new Label("Закрыть");
    private HBox buttonsBox = new HBox(clear, close);

    public NotificationsStage() {
        initStyle(StageStyle.TRANSPARENT);
        initModality(Modality.NONE);
        initStyles();
        Scene scene = new Scene(rootPane, Color.TRANSPARENT);
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
        setScene(scene);
        BorderPane borderPane = new BorderPane();
        setAnchors(borderPane);
        buttonsBox.setSpacing(30.0);
        buttonsBox.setPadding(new Insets(10, 10, 10, 10));
        borderPane.setBottom(buttonsBox);
        buttonsBox.setAlignment(Pos.CENTER);
        rootPane.getChildren().setAll(borderPane);

        initTransition();

        close.setOnMouseClicked(e -> hide());
        clear.setOnMouseClicked(e -> clear());

        //add notes
    }

    private void initTransition() {
        openTransition.setToX(0);
        closeTransition.setToX(400);
        closeTransition.setOnFinished(e -> super.hide());

        setOnShowing(e -> {
            Rectangle2D bounds = Screen.getPrimary().getVisualBounds();
            setX(bounds.getMaxX() - 400);
            setY(0);
            setHeight(bounds.getHeight());
            setWidth(800);
            rootPane.setTranslateX(400);
            openTransition.play();
        });
    }

    private void initStyles() {
        clear.getStyleClass().add("hyperlink-label");
        close.getStyleClass().add("hyperlink-label");
        rootPane.getStyleClass().add("root-pane");
    }

    private void clear() {

    }

    private void setAnchors(BorderPane borderPane) {
        AnchorPane.setLeftAnchor(borderPane, 0.0);
        AnchorPane.setRightAnchor(borderPane, 0.0);
        AnchorPane.setTopAnchor(borderPane, 0.0);
        AnchorPane.setBottomAnchor(borderPane, 0.0);
    }


    @Override
    public void hide() {
        closeTransition.play();
    }

}
