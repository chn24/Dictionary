package com.example.dictionarymaster;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    public void closeWindow (ActionEvent event) {
        Platform.exit();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}