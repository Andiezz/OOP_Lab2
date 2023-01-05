package lab05.screen;

import aims.cart.Cart;
import aims.media.*;
import aims.store.Store;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

import org.json.simple.parser.ParseException;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class CartScreenController {
    private Cart cart;
    @FXML
    private TableView<Media> tblMedia;
    @FXML
    private TableColumn<Media, String> colMediaTitle;
    @FXML
    private TableColumn<Media, String> colMediaCategory;
    @FXML
    private TableColumn<Media, Float> colMediaCost;
    @FXML
    private Button btnPlay;
    @FXML
    private Button btnRemove;
    @FXML
    private BorderPane cartPane;

    @FXML
    private MenuItem btnViewStore;
    @FXML
    private ToggleGroup filterCategory;
    @FXML
    private Label totalCostLabel;

    private TextField tfFilter;
    private RadioButton radioBtnFilterId;
    private RadioButton radioBtnFilterTitle;

    public CartScreenController(Cart cart) {
        super();
        this.cart = cart;
    }

    @FXML
    private void initialize() {
        colMediaTitle.setCellValueFactory(
                new PropertyValueFactory<Media, String>("title"));
        colMediaCategory.setCellValueFactory(
                new PropertyValueFactory<Media, String>("category"));
        colMediaCost.setCellValueFactory(
                new PropertyValueFactory<Media, Float>("cost"));
        tblMedia.setItems(this.cart.getItemsOrdered());

        btnPlay.setVisible(false);
        btnRemove.setVisible(false);

        this.totalCostLabel.setText(cart.totalCost() + " $");

        tblMedia.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<Media>() {
                    @Override
                    public void changed(ObservableValue<? extends Media> observable, Media oldValue, Media newValue) {
                        if (newValue != null) {
                            updateButtonBar(newValue);
                        }
                    }
                }
        );

//        tfFilter.textProperty().addListener(new ChangeListener<String>() {
//            @Override
//            public void changed(ObservableValue<? extends String> observable,
//                                String oldValue, String newValue) {
//                showFilteredMedia(newValue);
//            }
//        });
    }

    void updateButtonBar(Media newValue) {
        btnRemove.setVisible(true);
        btnPlay.setVisible(newValue instanceof Playable);
    }

    @FXML
    void btnRemovePressed(MouseEvent event) throws IOException {
        Media media = tblMedia.getSelectionModel().getSelectedItem();
        cart.removeMedia(media);
        cart.saveItemsToJSON();
        this.totalCostLabel.setText(cart.totalCost() + " $");
    }

    @FXML
    void btnPlayPressed(MouseEvent event) {
        Media media = tblMedia.getSelectionModel().getSelectedItem();
        Playable playable = (Playable) media;
        playable.play();

        JDialog dialog = new JDialog();
        JLabel label = new JLabel(playable.play());
        dialog.add(label);

        dialog.pack();
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }

    @FXML
    void btnPlaceOrderPressed(MouseEvent event) throws IOException {
        float totalCost = cart.totalCost();
        int VAT = 8;

        JDialog dialog = new JDialog();
        dialog.setLayout(new GridLayout(4, 2));

        JLabel label1_1 = new JLabel("Order successfully");
        JLabel label1_2 = new JLabel("");
        JLabel label2_1 = new JLabel("Total cost before VAT:");
        JLabel label2_2 = new JLabel(Float.toString(totalCost));
        JLabel label3_1 = new JLabel("VAT:");
        JLabel label3_2 = new JLabel(Integer.toString(VAT) + "%");
        JLabel label4_1 = new JLabel("Total cost after VAT:");
        JLabel label4_2 = new JLabel(Float.toString(totalCost + totalCost * VAT / 100));

        dialog.add(label1_1);
        dialog.add(label1_2);
        dialog.add(label2_1);
        dialog.add(label2_2);
        dialog.add(label3_1);
        dialog.add(label3_2);
        dialog.add(label4_1);
        dialog.add(label4_2);

        dialog.pack();
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);

        cart.removeAllItem();
        cart.saveItemsToJSON();
        this.totalCostLabel.setText(cart.totalCost() + " $");
    }

    @FXML
    void btnViewStoreClicked(ActionEvent event) throws IOException, ParseException {

        Store store = new Store();
        store.loadItemsFromJSON();
        new StoreScreen(store);
//        ((MenuItem)(event.getSource())).getParentMenu().getScene().getWindow().hide();
//        MenuItem menuItem = (MenuItem)event.getSource();
//        Scene scene = menuItem.getParentMenu().getScene();
    }

    void showFilteredMedia(String filter) {
        String filterType;
        if (radioBtnFilterTitle.isSelected()) {
            filterType = "title";
        } else {
            filterType = "id";
        }

        FilteredList<Media> list = new FilteredList<>(cart.getItemsOrdered(), null);
        list.setPredicate(media -> media.filterProperty(filter, filterType));

        if (cart.getItemsOrdered()!= null) {
            tblMedia.setItems(list);
        }
    }
}
