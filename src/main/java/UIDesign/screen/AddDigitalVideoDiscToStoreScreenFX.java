package UIDesign.screen;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javax.swing.*;
import java.io.IOException;
public class AddDigitalVideoDiscToStoreScreenFX extends JFrame {
    public AddDigitalVideoDiscToStoreScreenFX() {
        super();

        JFXPanel fxPanel = new JFXPanel();
        this.add(fxPanel);

        this.setTitle("Update Store");
        this.setSize(512, 364);
        this.setVisible(true);
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/UIDesign/screen/addDVD.fxml"));
                    AddDVDController controller = new AddDVDController();
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
        AddDigitalVideoDiscToStoreScreenFX addDigitalVideoDiscToStoreScreenFX = new AddDigitalVideoDiscToStoreScreenFX();
//        AddDigitalVideoDiscToStoreScreenFX.setVisible(true);
    }
}
