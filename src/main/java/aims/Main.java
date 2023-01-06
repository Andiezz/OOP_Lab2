package aims;

import aims.cart.Cart;
import exception.LimitExceededException;
import UIDesign.screen.CartScreen;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, ParseException, LimitExceededException {
//        Store store = new Store();
//        store.loadItemsFromJSON();
//        new StoreScreen(store);
        Cart cart = new Cart();
        cart.loadItemsFromJSON();
        new CartScreen(cart);
    }
}
