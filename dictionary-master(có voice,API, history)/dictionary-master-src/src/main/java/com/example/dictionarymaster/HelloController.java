package com.example.dictionarymaster;

import javafx.application.Platform;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javax.speech.Central;
import javax.speech.synthesis.Synthesizer;
import javax.speech.synthesis.SynthesizerModeDesc;
import java.util.Locale;
import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import static com.example.dictionarymaster.Dictionary.dictionary;
import static com.example.dictionarymaster.Dictionary.historyList;
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
    private ListView<String> listHistoryView;

    @FXML
    private FlowPane showSolve;

    @FXML
    private VBox listTarget = new VBox();

    @FXML
    private VBox listTab = new VBox();

    @FXML
    private VBox listExplain = new VBox();

    private ObservableList<Word> wordList = FXCollections.observableArrayList();

    private ObservableList<String> listWordHistory = FXCollections.observableArrayList();

    private ObservableList<String> listWordSearch = FXCollections.observableArrayList();

    private String wordSelect = new String();

    public void closeWindow (ActionEvent event) {
        dictionaryExportToFile();
        Platform.exit();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        for (Map.Entry<String, String> getWord : dictionary.entrySet()){
            Word newWord = new Word();
            newWord.setWord_target(getWord.getKey());
            newWord.setWord_explain(getWord.getValue());
            wordList.add(newWord);
        }
        targetColumn.setCellValueFactory(new PropertyValueFactory<Word, String>("word_target"));
        explainColumn.setCellValueFactory(new PropertyValueFactory<Word, String>("word_explain"));
        table.setItems(wordList);

        for (int i=0; i<historyList.size(); i++) {
            listWordHistory.add(historyList.get(i));
        }
        listHistoryView.getItems().addAll(listWordHistory);
    }

    @FXML
    public void search(KeyEvent key) {
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
        wordSelect = newWord.getValue();
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
        wordText.clear();
    }

    public void searchAPI(ActionEvent event) throws IOException {
        wordSelect = wordText.getText();
        if (!historyList.contains(wordSelect)) {
            historyList.add(wordSelect);
            listWordHistory.add(wordSelect);
            listHistoryView.getItems().clear();
            listHistoryView.getItems().addAll(listWordHistory);

        }
        DictionaryManagement.historyExportToFile();
        wordText.clear();
        String explainSelect = Translater.translate("en", "vi", wordSelect);
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

    public void voiceButton(ActionEvent e) {
        try {
            System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us" + ".cmu_us_kal.KevinVoiceDirectory");
            Central.registerEngineCentral("com.sun.speech.freetts" + ".jsapi.FreeTTSEngineCentral");
            Synthesizer voice = Central.createSynthesizer(new SynthesizerModeDesc(Locale.US));
            voice.allocate();
            voice.resume();
            voice.speakPlainText(wordSelect, null);
            voice.waitEngineState(Synthesizer.QUEUE_EMPTY);

        } catch (Exception even) {
            System.out.println("Please check your voice");
        }


    }

//  Add Scene
    public void newWordScene (ActionEvent event) throws IOException {

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Add2.fxml"));
        Parent AddView = loader.load();
        Scene scene = new Scene(AddView);
        stage.setScene(scene);
}
    //    Edit Scene
    public void editWordScene (ActionEvent event) throws IOException {

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Edit.fxml"));
        Parent AddView = loader.load();
        Scene scene = new Scene(AddView);
        stage.setScene(scene);
    }
    //    Delete Scene
    public void deleteWordScene (ActionEvent event) throws IOException {

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Delete.fxml"));
        Parent AddView = loader.load();
        Scene scene = new Scene(AddView);
        stage.setScene(scene);
    }

    public void clearHistory(ActionEvent event) {
        historyList.clear();
        listHistoryView.getItems().clear();
        listWordHistory.clear();
        DictionaryManagement.historyExportToFile();
    }
}