package com.example.dictionarymaster;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.Optional;

import static com.example.dictionarymaster.Dictionary.dictionary;

public class deleteWordController {
    @FXML
    private TextField deleteWord;

    public void deleteDone(ActionEvent event){
        String wordDelete = deleteWord.getText();
        String copyW = wordDelete + "1";
        if ( copyW.equals("1") ){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Error");
            alert.setHeaderText("Word is empty");
            alert.setContentText("Try again");

            ButtonType buttonTypeYes = new ButtonType("Ok", ButtonBar.ButtonData.YES);
            alert.getButtonTypes().setAll(buttonTypeYes);
            Optional<ButtonType> result = alert.showAndWait();
        }
        else {
            if (dictionary.containsKey(wordDelete)) {
                dictionary.remove(wordDelete);
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
                alert.setHeaderText("Delete succesfully");
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
        deleteWord.clear();
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
