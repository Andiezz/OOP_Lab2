package UIDesign.screen;

import aims.media.DigitalVideoDisc;
import aims.store.Store;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class AddDVDController extends AddItemController {
    @FXML
    private Button btnAdd;

    @FXML
    private Button btnCancel;

    @FXML
    private TextField inpCategory;

    @FXML
    private TextField inpCost;

    @FXML
    private TextField inpDirector;

    @FXML
    private TextField inpLength;

    @FXML
    private TextField inpTitle;

    @FXML
    void btnAddPressed(MouseEvent event) {
        DigitalVideoDisc newDVD = new DigitalVideoDisc(inpTitle.getText(),
                                                        inpCategory.getText(),
                                                        inpDirector.getText(),
                                                        Integer.parseInt(inpLength.getText()),
                                                        Float.parseFloat(inpCost.getText()));
        Store store = new Store();
        try {
            store.loadItemsFromJSON();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        store.addMedia(newDVD);
        try {
            store.saveItemsToJSON();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        new StoreScreen(store);
    }

    @FXML
    void btnCancelPressed(MouseEvent event) {
        Store store = new Store();
        try {
            store.loadItemsFromJSON();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        } catch (ParseException ex) {
            throw new RuntimeException(ex);
        }
        new StoreScreen(store);
    }
}
