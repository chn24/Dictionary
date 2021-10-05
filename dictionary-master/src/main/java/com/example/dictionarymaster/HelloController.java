package com.example.dictionarymaster;

import javafx.application.Platform;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

import java.net.URL;
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
    private VBox listTarget = new VBox();

    @FXML
    private VBox listTab = new VBox();

    @FXML
    private VBox listExplain = new VBox();

    private ObservableList<Word> wordList;

    ObservableList<String> listWordSearch = FXCollections.observableArrayList();

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

    @FXML
    public void search(ActionEvent e) {
        listWordSearch.clear();
        String wordSearch = new String();
        wordSearch = wordText.getText();
        showSolve.getChildren().clear();
        listTarget.getChildren().clear();
        listTab.getChildren().clear();
        listExplain.getChildren().clear();

        for (Map.Entry<String, String> getWord : dictionary.entrySet()) {
            int k = 0;
            String WordNS = getWord.getKey();
            showSolve.getChildren().clear();
            for (int i = 0; i < wordSearch.length(); i++){
                if(wordSearch.charAt(i) != WordNS.charAt(i)){
                    k =1;
                    break;
                }
            }
            if (k == 0) {
                listWordSearch.add(getWord.getKey());
            }
        }
        listWordView.getItems().setAll(listWordSearch);
    }

    @FXML
    public void wordClick (MouseEvent e) {
        ReadOnlyObjectProperty<String> newWord = listWordView.getSelectionModel().selectedItemProperty();
        String wordSelect = newWord.getValue();
        String explainSelect = dictionary.get(wordSelect);
        showSolve.getChildren().clear();

        listTarget.getChildren().clear();
        listTarget.getChildren().add(new Label("Target"));
        listTarget.setPadding(new Insets(20,20, 0,20));
        listTarget.getChildren().add(new Label(wordSelect));

        listTab.getChildren().clear();
        listTab.getChildren().add(new Label(" "));
        listTab.setPadding(new Insets(20,20, 0,20));
        listTab.getChildren().add(new Label("|"));

        listExplain.getChildren().clear();
        listExplain.getChildren().add(new Label("Explain"));
        listExplain.setPadding(new Insets(20,20, 0,20));
        listExplain.getChildren().add(new Label(explainSelect));

        showSolve.getChildren().addAll(listTarget, listTab, listExplain);
    }



}