package com.example.javafxproject;

import javafx.application.Application;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.geometry.*;
import javafx.scene.layout.VBox;
import javafx.beans.property.SimpleDoubleProperty;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.Random;
import javafx.beans.binding.Bindings;

public class Main extends Application {
    // Renaming stage
    Stage window;
    // Buttons
    Button  button1,button2,button3,button4,button5,buttonBack;
    // Scenes
    Scene scene1, scene2;
    // Random class obj
    Random rnd = new Random();
    // Main
    public static void main(String[] args) {
        launch(args);
    }
    // Cash variable
    SimpleDoubleProperty totalCash = new SimpleDoubleProperty(0);
    // "Salary" variable
    SimpleDoubleProperty moneyCoef = new SimpleDoubleProperty(5);
    // Countdown class obj
    ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

    @Override
    public void start(Stage stage) throws Exception {
        // Stage rename, title and setting to non-resizable
        window = stage;
        window.setResizable(false);
        window.setTitle("Life Simulator T1");

        // Button 1, work button.
        Button button1 = new Button("Work");
        button1.setOnAction(e -> {
            AlertBox.display("Work rewards",("+ R$"+moneyCoef.get()));
            totalCash.set(totalCash.get() + moneyCoef.get());
        });

        // Consuming close request to fix minor bugs
        window.setOnCloseRequest(e -> {
            e.consume();
            closeProgram();
        });

        // Total cash labels
        Label cash1 = new Label();
        cash1.textProperty().bind(Bindings.format("Total Cash: $%.2f", totalCash));

        Label cash2 = new Label();
        cash2.textProperty().bind(Bindings.format("Total Cash: $%.2f", totalCash));

        // Store button
        Button button2 = new Button("Go to store");
        button2.setOnAction(e -> {
            window.setScene(scene2);
        });

        // First scene layout with VBox
        VBox layout = new VBox(15);
        layout.getChildren().addAll(cash1,button1,button2);
        layout.setAlignment(Pos.CENTER);

        Scene scene1 = new Scene(layout, 300, 250);
        window.setScene(scene1);
        window.show();

        // Store
        Label labelStore = new Label(); // Title
        labelStore.setText("Store");

        Button button3 = new Button("Book - $50,00"); // Book price and text and function
        button3.setOnAction(e -> {
            if(totalCash.get()>=50) {
                totalCash.set(totalCash.get()-50);
                AlertBox.display("Transaction","Successful");
                moneyCoef.set(moneyCoef.get()+0.5);
            } else {
                AlertBox.display("Transaction","Not enough Money");
            }
        });
        Button button4 = new Button("Degree - $2.000,00"); // Degree price and text and function
        button4.setOnAction(e -> {
            if(totalCash.get()>=2000) {
                totalCash.set(totalCash.get()-2000);
                AlertBox.display("Transaction","Successful");
                moneyCoef.set(moneyCoef.get()+100);
            } else {
                AlertBox.display("Transaction","Not enough Money");
            }
        });
        Button button5 = new Button("Drugs - $10,00"); // Drugs price, text and function
        button5.setOnAction(e -> {
            if(totalCash.get()>=10) {
                totalCash.set(totalCash.get()-10);
                if(totalCash.get()>0) {
                    double tempMoney = rnd.nextDouble(totalCash.get());
                    AlertBox.display("Messed up", String.format("You got some cash stolen on your way home. Don't do drugs, pal... Money lost $%.2f", tempMoney));
                    totalCash.set(totalCash.get() - tempMoney);
                }
            } else {
                AlertBox.display("Transaction","Not enough Money");
            }
        });

        // Go back button from store
        Button buttonBack = new Button("Go home");
        buttonBack.setOnAction(e -> {
            window.setScene(scene1);
        });

        // Scene 2 layout with VBox(store scene)
        VBox layout2 = new VBox(15);
        layout2.getChildren().addAll(labelStore,cash2,button3,button4,button5,buttonBack);
        layout2.setAlignment(Pos.CENTER);

        scene2 = new Scene(layout2,300,250);

        }
    // Close game confirmation
    private void closeProgram() {
        Boolean answer = ConfirmBox.display("Exit?","Sure you wanna exit?");
        if(answer) {
            window.close();
        }
    }
}
