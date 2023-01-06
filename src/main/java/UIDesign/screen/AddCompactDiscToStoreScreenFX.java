package UIDesign.screen;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javax.swing.*;
import java.io.IOException;

public class AddCompactDiscToStoreScreenFX extends JFrame {
    public AddCompactDiscToStoreScreenFX() {
        super();

        JFXPanel fxPanel = new JFXPanel();
        this.add(fxPanel);

        this.setTitle("Update Store");
        this.setSize(600, 500);
        this.setVisible(true);
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/UIDesign/screen/addCD.fxml"));
                    AddCDController controller = new AddCDController();
                    loader.setController(controller);
                    Parent root = loader.load();
                    fxPanel.setScene(new Scene(root));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public static void main(String[] args) {
        AddCompactDiscToStoreScreenFX addCompactDiscToStoreScreenFX = new AddCompactDiscToStoreScreenFX();
    }
}
