package aims.media;

import exception.PlayerException;

import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

public class CompactDisc extends Disc implements Playable {
    private String artist;
    private ArrayList<Track> tracks = new ArrayList<>();

    public CompactDisc() {
        super();
    }

    public CompactDisc(String title, String category, float cost, String director,
            int length, String artist) {
        super(title, category, director, 0, cost);
        this.artist = artist;
        this.length = length;
    }

    public CompactDisc(String title, String category, float cost, String director
            , String artist, ArrayList<Track> tracks) {
        super(title, category, director, 0, cost);
        this.artist = artist;
        this.tracks = tracks;
        this.length = getLength();
    }

    public String getArtist() {
        return artist;
    }

    public boolean addTrack(Track track) {
        for (Track i: tracks) {
            if (i.getTitle().equals(track.getTitle())) {
                System.out.println("Track already existed!");
                return false;
            }
        }
        tracks.add(track);
        System.out.println("Track added successfully!");
        return true;
    }

    public boolean removeTrack(Track track) {
        for (Track i: tracks) {
            if (i.getTitle().equals(track.getTitle())) {
                tracks.remove(i);
                System.out.println("Track removed successfully!");
                return true;
            }
        }
        System.out.println("Can't find track!");
        return false;
    }

    @Override
    public int getLength() {
        length = 0;
        for (Track i: tracks) {
            length += i.getLength();
        }
        return length;
    }

    @Override
    public String play() throws PlayerException {
        if(this.getLength() > 0) {
            String str = "Playing CD:" +
                    "\nTitle: " + this.title +
                    "\nArtist: " + this.artist;

            java.util.Iterator iterator = tracks.iterator();
            Track nextTrack;
            while(iterator.hasNext()) {
                nextTrack = (Track) iterator.next();
                try {
                    nextTrack.play();
                    str = str.concat(nextTrack.play()).concat("\n");
                } catch (PlayerException e) {
                    throw e;
                }
            }
            return str;
        } else {
            throw new PlayerException("ERROR: CD length is non-positive!");
        }
    }

    @Override
    public String toString() {
        String trackArray;
        StringBuilder sb = new StringBuilder();
        for (Track i: tracks) {
            sb.append(i.getTitle()).append(" - ");
        }
        trackArray = sb.toString();
        return "Id: " + getId() +
                "\nTitle: " + getTitle() + "\nCategory: " + getCategory() +
                "\nCost: " + getCost() + "\nDirector: " + getDirector() +
                "\nLength: " + getLength() + "\nArtist: " + getArtist() +
                "\nTracks: " + trackArray+ "\n";
    }

    public static CompactDisc createCompactDisc () {
        // Input Compact Disc information
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
        System.out.println("Director: ");
        String s4 = keyboard.nextLine();
        keyboard = new Scanner(System.in);
        System.out.println("Artist: ");
        String s5 = keyboard.nextLine();
        System.out.println("How many tracks that the CD has?");
        int s6 = keyboard.nextInt();
        ArrayList<Track> trackList = new ArrayList<Track>();
        for (int i = 1; i <= s6; i++) {
            while (true) {
                Track track = Track.createTrack();
                if (trackList.contains(track)) {
                    System.out.println("Track already existed. Try again!");
                    continue;
                } else {
                    trackList.add(track);
                    break;
                }
            }
        }
        CompactDisc cd = new CompactDisc(s1, s2, s3, s4, s5, trackList);
        return cd;
    }
}
