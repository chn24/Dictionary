package com.example.dictionarymaster;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.Map;
import java.util.ResourceBundle;

import static com.example.dictionarymaster.Dictionary.dictionary;
import static com.example.dictionarymaster.DictionaryManagement.dictionaryExportToFile;

public class HelloController implements Initializable {
    @FXML
    private TableView<Word> table;

    @FXML
    private TableColumn<Word, String> targetColumn;

    @FXML
    private TableColumn<Word, String> explainColumn;

    @FXML
    private TextField wordText;

    @FXML
    private ListView<String> listWordView;

    @FXML
    private FlowPane showSolve;

    @FXML
    private VBox listNumber = new VBox();

    @FXML
    private VBox listTab1 = new VBox();

    @FXML
    private VBox listTarget = new VBox();

    @FXML
    private VBox listTab2 = new VBox();

    @FXML
    private VBox listExplain = new VBox();

    private ObservableList<Word> wordList;



    public void closeWindow (ActionEvent event) {
        dictionaryExportToFile();
        Platform.exit();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        wordList = FXCollections.observableArrayList();
        for (Map.Entry<String, String> getWord : dictionary.entrySet()){
            Word newWord = new Word();
            newWord.setWord_target(getWord.getKey());
            newWord.setWord_explain(getWord.getValue());
            wordList.add(newWord);
        }
        targetColumn.setCellValueFactory(new PropertyValueFactory<Word, String>("word_target"));
        explainColumn.setCellValueFactory(new PropertyValueFactory<Word, String>("word_explain"));
        table.setItems(wordList);
    }

    public void search(ActionEvent e) {
        int STT = 1;
        ObservableList<String> listWordSearch = FXCollections.observableArrayList();
        String wordSearch = new String();
        wordSearch = wordText.getText();
        showSolve.getChildren().clear();
        listWordSearch.clear();

        listNumber.getChildren().clear();
        listNumber.getChildren().add(new Label("No"));
        listNumber.setPadding(new Insets(20,20, 0,20));

        listTab1.getChildren().clear();
        listTab1.getChildren().add(new Label(" "));
        listTab1.setPadding(new Insets(20,20, 0,20));

        listTab2.getChildren().clear();
        listTab2.getChildren().add(new Label(" "));
        listTab2.setPadding(new Insets(20,20, 0,20));

        listTarget.getChildren().clear();
        listTarget.getChildren().add(new Label("Target"));
        listTarget.setPadding(new Insets(20,20, 0,20));

        listExplain.getChildren().clear();
        listExplain.getChildren().add(new Label("Explain"));
        listExplain.setPadding(new Insets(20,20, 0,20));

        for (Map.Entry<String, String> getWord : dictionary.entrySet()) {
            int k = 0;
            String WordNS = getWord.getKey();
            for (int i = 0; i < wordSearch.length(); i++){
                if(wordSearch.charAt(i) != WordNS.charAt(i)){
                    k =1;
                    break;
                }
            }
            if (k == 0) {
                listWordSearch.add(getWord.getKey());
                listNumber.getChildren().add(new Label(String.valueOf(STT)));
                listTab1.getChildren().add(new Label("|"));
                listTab2.getChildren().add(new Label("|"));
                listTarget.getChildren().add(new Label(getWord.getKey()));
                listExplain.getChildren().add(new Label(getWord.getValue()));
                STT++;
            }
        }
        listWordView.getItems().setAll(listWordSearch);
        showSolve.getChildren().addAll(listNumber, listTab1, listTarget, listTab2, listExplain);
    }
}