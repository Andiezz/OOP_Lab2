package aims.cart;

import aims.media.*;
import exception.LimitExceededException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.Scanner;

public class Cart {
    public final static int MAX_NUMBERS_ORDERED = 20;
    private ObservableList<Media> itemsOrdered = FXCollections.observableArrayList();
    public int getNumberOrdered() {
        return itemsOrdered.size();
    }

    public ObservableList<Media> getItemsOrdered() {
        return itemsOrdered;
    }

    public void addMedia(Media item) throws LimitExceededException {
        if(itemsOrdered.size() < MAX_NUMBERS_ORDERED) {
            itemsOrdered.add(item);
            System.out.println("Item added successfully!");
        } else {
            throw new LimitExceededException("ERROR: The number of media has reached its limit");
        }
    }

    public void removeMedia(Media item) {
        for (Media i: itemsOrdered) {
            if (i.getTitle().equals(item.getTitle())) {
                itemsOrdered.remove(i);
                System.out.println("Item removed successfully!");
                return;
            }
        }
        System.out.println("Can't find item!");
    }

    public float totalCost() {
        float sum = 0f;
        for (Media m : itemsOrdered) {
            sum += m.getCost();
        }
        return sum;
    }

    public Media searchByTitle(String title) {
        for (Media m : itemsOrdered) {
            if (m.getTitle().equals(title)) return m;
        }
        return null;
    }

    public void findByTitle() {
        Scanner keyboard2 = new Scanner(System.in);
        System.out.println("Find the Media by Title:");
        String s = keyboard2.nextLine();
        for (Media i: itemsOrdered) {
            if (i.getTitle().equals(s)) {
                System.out.println(i.toString());
                return;
            }
        }
        System.out.println("No product found!");
    }

    public void findById() {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Find the Media by ID: ");
        int n = keyboard.nextInt();
        for (Media i: itemsOrdered) {
            if (i.getId() == n) {
                System.out.println(i.toString());
                return;
            }
        }
        System.out.println("No product found!");
    }

    public void sortByTitleCost() {
        Collections.sort(itemsOrdered, Media.COMPARE_BY_TITLE_COST);
    }

    public void sortByCostTitle() {
        Collections.sort(itemsOrdered, Media.COMPARE_BY_COST_TITLE);
    }

    public void removeAllItem() {
        itemsOrdered.clear();
    }

    public boolean contains (Object obj) {
        for (Media i: itemsOrdered) {
            if (i.equals(obj)) {
                return true;
            }
        }
        return false;
    }

    public void loadItemsFromJSON() throws IOException, ParseException, LimitExceededException {
        FileReader reader = new FileReader("data/cart.json");
        JSONParser parser = new JSONParser();
        Object obj  = parser.parse(reader);
        JSONArray jsonArray = (JSONArray) obj;
        for(Object object : jsonArray){
            JSONObject jsonObject = (JSONObject) object;;
            String type = (String)jsonObject.get("type");
            if(type.equals("DigitalVideoDisc")){
                String title = (String)jsonObject.get("title");
                String category = (String)jsonObject.get("category");
                float cost = Float.parseFloat((String)jsonObject.get("cost"));
                String director = (String)jsonObject.get("director");
                int length = Integer.parseInt((String)jsonObject.get("length"));
                Media m = new DigitalVideoDisc(title, category, director, length, cost);
                addMedia(m);
            }
            else if(type.equals("CompactDisc")){
                String title = (String)jsonObject.get("title");
                String category = (String)jsonObject.get("category");
                float cost = Float.parseFloat((String)jsonObject.get("cost"));
                String director = (String)jsonObject.get("director");
                int length = Integer.parseInt((String)jsonObject.get("length"));
                String artist = (String)jsonObject.get("artist");
                Media m = new CompactDisc(title, category, cost, director, length, artist);
                addMedia(m);
            }
            else if(type.equals("Book")){
                String title = (String)jsonObject.get("title");
                String category = (String)jsonObject.get("category");
                float cost = Float.parseFloat((String)jsonObject.get("cost"));
                Media m = new Book(title, category, cost);
                addMedia(m);
            }
        }
    }
    public void saveItemsToJSON() throws IOException {
        JSONArray jsonArray = new JSONArray();
        for(Media m : itemsOrdered) {
            JSONObject jsonObject = new JSONObject();
            if(m instanceof DigitalVideoDisc){
                DigitalVideoDisc d = (DigitalVideoDisc)m;
                jsonObject.put("type", "DigitalVideoDisc");
                jsonObject.put("title", d.getTitle());
                jsonObject.put("category", d.getCategory());
                jsonObject.put("cost", Float.toString(d.getCost()));
                jsonObject.put("director", d.getDirector());
                jsonObject.put("length", Integer.toString(d.getLength()));
            }
            else if(m instanceof CompactDisc){
                CompactDisc c = (CompactDisc) m;
                jsonObject.put("type", "CompactDisc");
                jsonObject.put("title", c.getTitle());
                jsonObject.put("category", c.getCategory());
                jsonObject.put("cost", Float.toString(c.getCost()));
                jsonObject.put("director", c.getDirector());
                jsonObject.put("length", Integer.toString(c.getLength()));
                jsonObject.put("artist", c.getArtist());
            }
            else if(m instanceof Book){
                Book b = (Book) m;
                jsonObject.put("type", "Book");
                jsonObject.put("title", b.getTitle());
                jsonObject.put("category", b.getCategory());
                jsonObject.put("cost", Float.toString(b.getCost()));
            }
            jsonArray.add(jsonObject);
        }
        // Save jsonArray to file
        try (FileWriter file = new FileWriter("data/cart.json")) {
            file.write(jsonArray.toString());
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void printCart() {
        System.out.println("""
                ***************CART***************
                Ordered Items:""");
        for (Media i: itemsOrdered) {
            System.out.println(i.toString());
        }
        System.out.println("Total cost: " + totalCost() +
                "\n" + "*********************************");
    }
//
//    public DigitalVideoDisc searchByTitle(String keyword) {
//        for (int i = 0; i < qtyOrdered; i++) {
//            if (itemsOrdered[i].isMatch(keyword)) {
//                System.out.println("Found: " + itemsOrdered[i].toString());
//                return itemsOrdered[i];
//            }
//        }
//        System.out.println("Item not found.");
//        return null;
//    }
}
