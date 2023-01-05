package lab05.screen;

import aims.store.Store;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class AddItemController {
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

    }

    @FXML
    void btnCancelPressed(MouseEvent event) {

    }
}
