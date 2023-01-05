package aims.store;

import aims.media.*;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;

import java.io.FileReader;

public class Store {
    private ArrayList<Media> itemsInStore = new ArrayList<Media>();

    public boolean addMedia(Media item) {
        if (itemsInStore.contains(item)) {
            System.out.println("Item already existed. Try again!");
            return false;
        }
        itemsInStore.add(item);
        System.out.println("Item added successfully!");
        return true;
    }

    public void removeMedia(Media item) {
        for (Media i: itemsInStore) {
            if (i.getTitle().equals(item.getTitle())) {
                itemsInStore.remove(i);
                System.out.println("Item removed successfully!");
                return;
            }
        }
        System.out.println("Can't find item!");
    }

    public void removeMediaByTitle(String title) {
        for (Media i: itemsInStore) {
            if (i.getTitle().equals(title)) {
                itemsInStore.remove(i);
                System.out.println("Item removed successfully!");
                return;
            }
        }
        System.out.println("Can't find item!");
    }

    public ArrayList<Media> getItemsInStore() {
        return itemsInStore;
    }

    public void loadItemsFromJSON() throws IOException, ParseException {
        FileReader reader = new FileReader("data/store.json");
        JSONParser parser = new JSONParser();
        Object obj  = parser.parse(reader);
        JSONArray jsonArray = (JSONArray) obj;
        for(Object object : jsonArray){
            JSONObject jsonObject = (JSONObject) object;
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
        for(Media m : itemsInStore) {
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
        try (FileWriter file = new FileWriter("data/store.json")) {
            file.write(jsonArray.toString());
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void printStore() {
        System.out.println("""
                ***************Store***************
                Store Items:""");
        for (Media i: itemsInStore) {
            System.out.println(i.toString());
        }
    }
}
