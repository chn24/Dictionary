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
import java.io.IOException;
import java.util.Locale;

import static com.example.dictionarymaster.Dictionary.historyList;

public class apiController {

    @FXML
    private TextArea apiWord;

    @FXML
    private TextArea apiMean;

    String wordSelect = new String();

    public void Search(ActionEvent event) throws IOException {
        wordSelect = apiWord.getText();
        String explainSelect = Translater.translate("en", "vi", wordSelect);
        apiMean.setText(explainSelect);
    }

    public void Clear(ActionEvent event) {
        apiWord.clear();
        apiMean.clear();
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
}
