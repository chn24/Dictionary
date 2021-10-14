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

import javax.speech.Central;
import javax.speech.synthesis.Synthesizer;
import javax.speech.synthesis.SynthesizerModeDesc;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Locale;
import java.util.Map;

import static com.example.dictionarymaster.Dictionary.dictionary;
import static com.example.dictionarymaster.Dictionary.historyList;

public class vtoeController {

    @FXML
    private TextArea vnm;

    @FXML
    private TextArea eng;

    String wordSelect = new String();

    String explainSelect = new String();

    public void Search(ActionEvent event) throws IOException {
        wordSelect = vnm.getText();
        explainSelect = Translatev.translate("vi", "en", wordSelect);
        eng.setText(explainSelect);

    }

    public void Clear(ActionEvent event) {
        vnm.clear();
        eng.clear();
        wordSelect = "";
    }

    public void Back(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("hello-view.fxml"));
        Parent studentViewParent = loader.load();
        Scene scene = new Scene(studentViewParent);
        stage.setScene(scene);
    }

    public void Swap(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Api.fxml"));
        Parent studentViewParent = loader.load();
        Scene scene = new Scene(studentViewParent);
        stage.setScene(scene);
    }

    public void voiceButton(ActionEvent e) {
        try {
            System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us" + ".cmu_us_kal.KevinVoiceDirectory");
            Central.registerEngineCentral("com.sun.speech.freetts" + ".jsapi.FreeTTSEngineCentral");
            Synthesizer voice = Central.createSynthesizer(new SynthesizerModeDesc(Locale.US));
            voice.allocate();
            voice.resume();
            voice.speakPlainText(explainSelect, null);
            voice.waitEngineState(Synthesizer.QUEUE_EMPTY);

        } catch (Exception even) {
            System.out.println("Please check your voice");
        }
    }
}
