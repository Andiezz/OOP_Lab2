package aims;

import aims.cart.Cart;
import aims.media.*;
import aims.store.Store;
import lab05.screen.CartScreen;
import lab05.screen.StoreScreen;
import org.json.simple.parser.ParseException;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException, ParseException {
//        Store store = new Store();
//        store.loadItemsFromJSON();
//        new StoreScreen(store);
        Cart cart = new Cart();
        cart.loadItemsFromJSON();
        new CartScreen(cart);
    }
}
