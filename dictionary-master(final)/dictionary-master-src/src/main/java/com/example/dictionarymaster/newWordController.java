package com.example.dictionarymaster;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Map;

import javafx.stage.Stage;
import java.io.IOException;
import java.lang.reflect.*;
import java.util.Optional;

import static com.example.dictionarymaster.Dictionary.dictionary;

public class newWordController {

    @FXML
    private TextField addWord;
    @FXML
    private TextField addMean;

    // Add a Word
    public void Add(ActionEvent event) {
        String newWord = addWord.getText();
        String newMean = addMean.getText();
        String copyNW = newWord + "1";
        String copyNM = newMean + "1";
        if (copyNM.equals("1") || copyNW.equals("1") ){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Error");
            alert.setHeaderText("New word or Mean is empty");
            alert.setContentText("Try again");

            ButtonType buttonTypeYes = new ButtonType("Ok", ButtonBar.ButtonData.YES);
            alert.getButtonTypes().setAll(buttonTypeYes);
            Optional<ButtonType> result = alert.showAndWait();
        }
        if ( copyNM.equals("1") == false && copyNW.equals("1") == false) {
            if (dictionary.containsKey(newWord)) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Error");
                alert.setHeaderText("Word is already in Dictionary");
                alert.setContentText("Choose another word");

                ButtonType buttonTypeYes = new ButtonType("Ok", ButtonBar.ButtonData.YES);
                alert.getButtonTypes().setAll(buttonTypeYes);
                Optional<ButtonType> result = alert.showAndWait();
            } else {
                dictionary.put(newWord, newMean);
                try {
                    FileWriter fw = new FileWriter("data.txt");
                    BufferedWriter bw = new BufferedWriter(fw);
                    for (Map.Entry<String, String> getWord : dictionary.entrySet()) {
                        String WordtoS = getWord.getKey() + "\t" + getWord.getValue();
                        bw.write(WordtoS);
                        bw.newLine();
                    }
                    bw.close();
                    fw.close();
                } catch (Exception e) {
                }
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Message");
                alert.setHeaderText("Add succesfully");
                //alert.setContentText("Try again");

                ButtonType buttonTypeYes = new ButtonType("Ok", ButtonBar.ButtonData.YES);
                alert.getButtonTypes().setAll(buttonTypeYes);
                Optional<ButtonType> result = alert.showAndWait();
            }
        }
        addWord.clear();
        addMean.clear();
    }
    //    Back to Menu
    public void Back(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("hello-view.fxml"));
        Parent studentViewParent = loader.load();
        Scene scene = new Scene(studentViewParent);
        stage.setScene(scene);
    }
}
