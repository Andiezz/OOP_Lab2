package aims.media;

import java.util.Scanner;

public class Track implements Playable {
    private String title;
    private int length;

    public Track() {

    }

    public Track(String title, int length) {
        this.title = title;
        this.length = length;
    }

    public String getTitle() {
        return title;
    }

    public int getLength() {
        return length;
    }

    @Override
    public String play() {
        String str = "Playing track: " + this.getTitle() +
                "Track length: " + this.getLength();
        return str;
    }

    public static Track createTrack() {
        Scanner keyboard;
        System.out.println("Enter information:");
        keyboard = new Scanner(System.in);
        System.out.println("Title: ");
        String s1 = keyboard.nextLine();
        keyboard = new Scanner(System.in);
        System.out.println("Length: ");
        int s2 = keyboard.nextInt();
        Track track = new Track(s1, s2);
        return track;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Track) {
            Track product = (Track) obj;
            return product.getTitle().equals(this.getTitle())
                    && product.getLength() == this.getLength();
        }
        return false;
    }
}
