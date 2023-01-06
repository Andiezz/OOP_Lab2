module com.lab05 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.datatransfer;
    requires java.desktop;
    requires javafx.swing;
    requires json.simple;

    opens UIDesign.javafx;
    exports UIDesign.javafx;

    opens UIDesign.screen;
    opens aims.media;

    provides javafx.application.Application with UIDesign.javafx.Painter;
}