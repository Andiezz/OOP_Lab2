module com.lab05 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.datatransfer;
    requires java.desktop;
    requires javafx.swing;
    requires json.simple;

    opens lab05.javafx;
    exports lab05.javafx;

    opens lab05.screen;
    opens aims.media;

    provides javafx.application.Application with lab05.javafx.Painter;
}