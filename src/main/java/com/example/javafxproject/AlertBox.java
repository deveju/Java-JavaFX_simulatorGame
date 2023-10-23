package com.example.javafxproject;

import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.geometry.*;
import javafx.scene.layout.VBox;

public class AlertBox {
    public static void display(String title, String message) {
        Stage window = new Stage();
        window.setResizable(false);

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(200);

        Label label1 = new Label();
        label1.setText(message);
        Button closeButton = new Button("Close");
        closeButton.setOnAction(e -> {
            window.close();
        });

        VBox layout = new VBox(15);
        layout.getChildren().addAll(closeButton,label1);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.show();

    }
}
