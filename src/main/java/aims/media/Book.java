package aims.media;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Book extends Media {
    private List<String> authors;

    public Book() {

    }

    @Override
    public String toString() {
        String authorsArray;
        StringBuilder sb = new StringBuilder();
        for (String i: authors) {
            sb.append(" ").append(i);
        }
        authorsArray = sb.toString();
        return "Id: " + getId() +
                "\nTitle: " + getTitle() + "\nCategory: " + getCategory() +
                "\nCost: " + getCost() + "\nAuthor: " + authorsArray +"\n";
    }

    public List<String> getAuthors() {
        return authors;
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }

    public boolean addAuthor(String authorName) {
        if (!this.authors.contains(authorName)) {
            this.authors.add(authorName);
            System.out.println("Book added successfully!");
            return true;
        }
        System.out.println("Book already existed!");
        return false;
    }

    public boolean removeAuthor(String authorName) {
        if (this.authors.contains(authorName)) {
            this.authors.remove(authorName);
            System.out.println("Book removed successfully!");
            return true;
        }
        System.out.println("Can't find book!");
        return false;
    }

    public void sortAuthor() {
        Collections.sort(this.authors);
    }

    public static Book createBook() {
        // Input book information
        Scanner keyboard;
        System.out.println("Enter information:");
        keyboard = new Scanner(System.in);
        System.out.println("Title: ");
        String s1 = keyboard.nextLine();
        keyboard = new Scanner(System.in);
        System.out.println("Category: ");
        String s2 = keyboard.nextLine();
        keyboard = new Scanner(System.in);
        System.out.println("Cost: ");
        float s3 = keyboard.nextFloat();
        keyboard = new Scanner(System.in);
        System.out.println("How many authors who wrote the book?");
        int s4 = keyboard.nextInt();
        List<String> authorList = new ArrayList<String>();
        for (int i = 1; i <= s4; i++) {
            keyboard = new Scanner(System.in);
            System.out.println("Author " + i + ": ");
            String s5 = keyboard.nextLine();
            authorList.add(s5);
        }
        Book book = new Book(s1, s2, s3, authorList);
        return book;
    }

    public Book(String title, String category, float cost) {
        super(title, category, cost);
    }

    public Book(String title, String category, float cost, List<String> authors) {
        super(title, category, cost);
        this.authors = authors;
    }
}
