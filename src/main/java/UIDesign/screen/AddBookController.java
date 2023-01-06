package UIDesign.screen;

import aims.media.Book;
import aims.store.Store;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class AddBookController extends AddItemController {
    @FXML
    private BorderPane borderPane;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnCancel;

    @FXML
    private TextField inpCategory;

    @FXML
    private TextField inpCost;

    @FXML
    private TextField inpTitle;

    @FXML
    void btnAddPressed(MouseEvent event) {
        Book newBook = new Book(inpTitle.getText(),
                                inpCategory.getText(),
                                Float.parseFloat(inpCost.getText()));
        Store store = new Store();
        try {
            store.loadItemsFromJSON();
        } catch (ParseException e) {
                throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        store.addMedia(newBook);
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
