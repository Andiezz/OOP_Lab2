package aims.media;

import exception.PlayerException;

import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

public class DigitalVideoDisc extends Disc implements Playable {
    public DigitalVideoDisc() {
        super();
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Id: " + getId() +
                "\nTitle: " + getTitle() + "\nCategory: " + getCategory() +
                "\nCost: " + getCost() + "\nDirector: " + getDirector() +
                "\nLength: " + getLength() +"\n";
    }

    public boolean isMatch(String title) {
        return this.title.equals(title);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (!(obj instanceof DigitalVideoDisc)) return false;
        DigitalVideoDisc disc = (DigitalVideoDisc) obj;
        return this.title.equals(disc.title) && this.category.equals(disc.category)
                && this.director.equals(disc.director) && this.length == disc.length
                && this.cost == disc.cost;
    }

    @Override
    public String play() throws PlayerException {
        if(this.getLength() > 0) {
            String str = "Playing DVD: " + this.getTitle() + "\n" + "   DVD length: " + this.getLength();
            return str;
        } else {
            throw new PlayerException("ERROR: DVD length is non-positive!");
        }
    }

    public static DigitalVideoDisc createDigitalVideoDisc () {
        Scanner keyboard;
        System.out.println("Enter information:");
        keyboard = new Scanner(System.in);
        System.out.println("Title: ");
        String s1 = keyboard.nextLine();
        keyboard = new Scanner(System.in);
        System.out.println("Category: ");
        String s2 = keyboard.nextLine();
        keyboard = new Scanner(System.in);
        System.out.println("Director: ");
        String s3 = keyboard.nextLine();
        keyboard = new Scanner(System.in);
        System.out.println("Length: ");
        int s4 = keyboard.nextInt();
        keyboard = new Scanner(System.in);
        System.out.println("Cost: ");
        float s5 = keyboard.nextFloat();
        DigitalVideoDisc dvd = new DigitalVideoDisc(s1, s2, s3, s4, s5);
        return dvd;
    }

    public DigitalVideoDisc(String title, String category, String director, int length, float cost) {
        super(title, category, director, length, cost);
    }
}
