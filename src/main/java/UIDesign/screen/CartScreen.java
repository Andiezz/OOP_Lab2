package UIDesign.screen;

import aims.cart.Cart;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;


import javax.swing.*;
import java.io.IOException;

public class CartScreen extends JFrame {
    private Cart cart;

    public CartScreen(Cart cart) {
        super();

        this.cart = cart;

        JFXPanel fxPanel = new JFXPanel();
        this.add(fxPanel);

        this.setTitle("Cart");
        this.setSize(1024, 768);
        this.setVisible(true);
        Platform.runLater(new Runnable() {
           @Override
           public void run() {
               try {
                   FXMLLoader loader = new FXMLLoader(getClass().getResource("/UIDesign/screen/cart.fxml"));
                   CartScreenController controller = new CartScreenController(cart);
                   loader.setController(controller);
                   Parent root = loader.load();
                   fxPanel.setScene(new Scene(root));
               } catch (IOException e) {
                   e.printStackTrace();
               }
           }
        });
    }
}
