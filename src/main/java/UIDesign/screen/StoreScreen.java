package UIDesign.screen;

import aims.cart.Cart;
import aims.store.Store;
import aims.media.Media;
import exception.LimitExceededException;
import org.json.simple.parser.ParseException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.ArrayList;

public class StoreScreen extends JFrame {
    private Store store;

    public StoreScreen(Store store){
        this.store = store;
        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());

        cp.add(createNorth(), BorderLayout.NORTH);
        cp.add(createCenter(), BorderLayout.CENTER);

        setVisible(true);
        setTitle("Store");
        setSize(1024, 768);
    }
    JPanel createNorth(){
        JPanel north = new JPanel();
        north.setLayout(new BoxLayout(north, BoxLayout.Y_AXIS));
        north.add(createMenuBar());
        north.add(createHeader());
        return north;
    }
    JMenuBar createMenuBar(){
        ViewCartButtonListener btnListener = new ViewCartButtonListener();
        JMenu menu = new JMenu("Options");

        JMenu smUpdateStore = new JMenu("Update Store");

        JMenuItem addBookMenuItem = new JMenuItem("Add Book");
        addBookMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddBookToStoreScreenFX addBookToStoreScreenFX = new AddBookToStoreScreenFX();
                addBookToStoreScreenFX.setVisible(true);
                setVisible(false);
            }
        });
        smUpdateStore.add(addBookMenuItem);

        JMenuItem addCDMenuItem = new JMenuItem("Add CD");
        addCDMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddCompactDiscToStoreScreenFX addCompactDiscToStoreScreenFX = new AddCompactDiscToStoreScreenFX();
                addCompactDiscToStoreScreenFX.setVisible(true);
                setVisible(false);
            }
        });
        smUpdateStore.add(addCDMenuItem);

        JMenuItem addDVDMenuItem = new JMenuItem("Add DVD");
        addDVDMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddDigitalVideoDiscToStoreScreenFX addDigitalVideoDiscToStoreScreenFX = new AddDigitalVideoDiscToStoreScreenFX();
                addDigitalVideoDiscToStoreScreenFX.setVisible(true);
                setVisible(false);
            }
        });
        smUpdateStore.add(addDVDMenuItem);

        menu.add(smUpdateStore);

        JMenuItem viewStoreMenuItem = new JMenuItem("View store");
        menu.add(viewStoreMenuItem);

        JMenuItem viewCartMenuItem = new JMenuItem("View cart");
        viewCartMenuItem.addActionListener(btnListener);
        menu.add(viewCartMenuItem);

        JMenuBar menuBar = new JMenuBar();
        menuBar.setLayout(new FlowLayout(FlowLayout.LEFT));
        menuBar.add(menu);

        return menuBar;
    }

    JPanel createHeader(){
        ViewCartButtonListener btnListener = new ViewCartButtonListener();
        JPanel header = new JPanel();
        header.setLayout(new BoxLayout(header, BoxLayout.X_AXIS));

        JLabel title = new JLabel("AIMS");
        title.setFont(new Font(title.getFont().getName(), Font.PLAIN, 50));
        title.setForeground(Color.CYAN);

        JButton cart = new JButton("View cart");
        cart.setPreferredSize(new Dimension(100, 50));
        cart.setMaximumSize(new Dimension(100, 50));
        cart.addActionListener(btnListener);

        header.add(Box.createRigidArea(new Dimension(10, 10)));
        header.add(title);
        header.add(Box.createHorizontalGlue());
        header.add(cart);
        header.add(Box.createRigidArea(new Dimension(10, 10)));

        return header;
    }

    JPanel createCenter(){
        JPanel center = new JPanel();
        center.setLayout(new GridLayout(3, 3, 2, 2));

        ArrayList<Media> mediaInStore = store.getItemsInStore();
        for (Media media : mediaInStore) {
            MediaStore cell = new MediaStore(media);
            center.add(cell);
        }
        return center;
    }

    private class ViewCartButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Cart cart = new Cart();
            try {
                cart.loadItemsFromJSON();
            } catch (IOException | ParseException | LimitExceededException exception) {
                throw new RuntimeException(exception);
            }

            new CartScreen(cart);
            setVisible(false);
            dispose();

        }
    }

    public static void main(String[] args) throws IOException, ParseException {
        Store store = new Store();
        store.loadItemsFromJSON();
        new StoreScreen(store);
    }
}
