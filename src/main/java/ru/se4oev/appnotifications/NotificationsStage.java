package ru.se4oev.appnotifications;

import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.*;
import javafx.util.Duration;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by karpenko on 25.08.2022.
 * Description:
 */
public class NotificationsStage extends Stage {

    private final VBox noteList = new VBox();
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

        ScrollPane scrollPane = new ScrollPane(noteList);
        scrollPane.getStyleClass().add("notes-scroll-pane");
        scrollPane.setFitToWidth(true);
        borderPane.setCenter(scrollPane);
        BorderPane.setAlignment(scrollPane, Pos.BOTTOM_RIGHT);
        noteList.setAlignment(Pos.BOTTOM_RIGHT);
        //TODO: если не задавать высоту, то скукоживается
        // нужно привязать к высоте панели
        noteList.setPrefHeight(1000);

        initTransition();

        close.setOnMouseClicked(e -> hide());
        clear.setOnMouseClicked(e -> clear());
    }

    private void initTransition() {
        openTransition.setToX(0);
        openTransition.setOnFinished(a -> addNotes());
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

    private void addNotes() {
        Timer timer = new Timer();
        AtomicInteger count = new AtomicInteger(1);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                            Notification notification = new Notification("Превед, Медвед " + count.getAndIncrement(), NotificationType.INFO);
                            notification.setOnRemove(() -> noteList.getChildren().remove(notification));
                            noteList.getChildren().add(notification);
                        }
                );
            }
        }, 1000, 500);
    }

    private void initStyles() {
        clear.getStyleClass().add("hyperlink-label");
        close.getStyleClass().add("hyperlink-label");
        rootPane.getStyleClass().add("root-pane");
        noteList.getStyleClass().add("note-list");
    }

    private void clear() {
        noteList.getChildren().clear();
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
