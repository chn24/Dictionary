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

public class editWordController {
    @FXML
    private TextField editWord;
    @FXML
    private TextField editNewMean;

    public void editDone(ActionEvent event) {
        String wordEdit = editWord.getText();
        String newMean = editNewMean.getText();
        String copyW = wordEdit + "1";
        String copyNM = newMean + "1";
        if (copyNM.equals("1") || copyW.equals("1") ){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Error");
            alert.setHeaderText("Word or New mean is empty");
            alert.setContentText("Try again");

            ButtonType buttonTypeYes = new ButtonType("Ok", ButtonBar.ButtonData.YES);
            alert.getButtonTypes().setAll(buttonTypeYes);
            Optional<ButtonType> result = alert.showAndWait();
        }
        if ( copyNM.equals("1") == false && copyW.equals("1") == false) {
            if (dictionary.containsKey(wordEdit)) {
                dictionary.put(wordEdit, newMean);
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
                alert.setHeaderText("Edit succesfully");
                //alert.setContentText("Try again");

                ButtonType buttonTypeYes = new ButtonType("Ok", ButtonBar.ButtonData.YES);
                alert.getButtonTypes().setAll(buttonTypeYes);
                Optional<ButtonType> result = alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Error");
                alert.setHeaderText("Word not found");
                alert.setContentText("Choose another word");

                ButtonType buttonTypeYes = new ButtonType("Ok", ButtonBar.ButtonData.YES);
                alert.getButtonTypes().setAll(buttonTypeYes);
                Optional<ButtonType> result = alert.showAndWait();
            }
        }
        editWord.clear();
        editNewMean.clear();
    }
    //    Back
    public void Back(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("hello-view.fxml"));
        Parent studentViewParent = loader.load();
        Scene scene = new Scene(studentViewParent);
        stage.setScene(scene);
    }
}
