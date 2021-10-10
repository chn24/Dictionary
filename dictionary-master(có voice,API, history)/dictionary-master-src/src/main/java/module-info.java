module com.example.dictionarymaster {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires jsapi;


    opens com.example.dictionarymaster to javafx.fxml;
    exports com.example.dictionarymaster;
}