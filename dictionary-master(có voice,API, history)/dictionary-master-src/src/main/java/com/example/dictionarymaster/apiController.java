package com.example.dictionarymaster;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;

import static com.example.dictionarymaster.Dictionary.historyList;

public class apiController {

    @FXML
    private TextArea apiWord;

    @FXML
    private TextArea apiMean;

    public void Search(ActionEvent event) throws IOException {
        String wordSelect = apiWord.getText();
        String explainSelect = Translater.translate("en", "vi", wordSelect);
        apiMean.setText(explainSelect);
    }
    public void Clear(ActionEvent event) {
        apiWord.clear();
        apiMean.clear();
    }

    public void Back(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("hello-view.fxml"));
        Parent studentViewParent = loader.load();
        Scene scene = new Scene(studentViewParent);
        stage.setScene(scene);
    }
}
