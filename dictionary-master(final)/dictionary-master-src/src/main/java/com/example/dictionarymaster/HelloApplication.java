package com.example.dictionarymaster;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import static com.example.dictionarymaster.DictionaryCommandLine.dictionaryAdvanced;

public class HelloApplication extends Application {
    @Override
    public void start(Stage primaryStage) {

        try {
            Parent root = FXMLLoader.load(this.getClass().getResource("hello-view.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setTitle("Dictionary");
            //scene.getStylesheets().add(getClass().getResource("theme.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.show();
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public static void main(String[] args) {
        try {
            dictionaryAdvanced();
        } catch (Exception e) {
            System.out.println("1");
        }
        launch();

    }
}