import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import static java.lang.Thread.sleep;

public class Playlist {

    private static Playlist playlist;
    private Song firstSong;
    private Song lastSong;


    // Force Singleton of class Playlist
    public static Playlist getInstance() {
        if (playlist == null)
            playlist = new Playlist();
        return playlist;
    }

    // plays the next available song and removes it from the linked list
    public void playNextSong() throws URISyntaxException, IOException, InterruptedException {
        System.out.println("Now Playing...");
        System.out.println(firstSong.getTittle());
        sleep(3000);
        Desktop desk = Desktop.getDesktop();
        desk.browse(new URI(firstSong.getUrl()));
        skipNext();
    }

    // adds the song at the beginning of the linked list
    public void addNext(Song song) {
        System.out.println(song.getTittle() + " has been added");
        if (isEmpty()) {
            firstSong = song; //This makes song the head of the list
            lastSong = song;
        } else {
            song.next = firstSong; //The song will refer to previous firstSong. song --> firstSong
            firstSong = song; //This makes song the head of the list
        }
    }

    // adds the song at the end of the linked list
    public void addAtTheEnd(Song song) {
        if (isEmpty()){
            addNext(song);
        } else {
            System.out.println(song.getTittle() + " has been added");
            lastSong.next = song;
            lastSong = song;
        }
    }

    // removes the first song in the linked list
    public void skipNext() {
        System.out.println("Skipping...");
        if(isEmpty()) {
            System.out.println("Empty LinkedList"); //if the isEmpty method returns true this will be printed
        } else {
            System.out.println(firstSong.getTittle() + " has been skipped");
            firstSong = firstSong.next; //if the isEmpty method returns false the firstSong will now refer to the next.
        }
    }

    // removes the last song in the linked list
    public void skipTheLast() {
        System.out.println("Skipping...");
        if(isEmpty()) {
            System.out.println("Empty LinkedList");
        } else if (firstSong.next == null) {
            System.out.println(firstSong.getTittle() + " has been skipped");
            firstSong = null;
            lastSong = null;
        } else {
            Song temp = firstSong; //temp Song that is as if it was the head to traverse through the LinkedList
            while(temp.next.next != null) {
                temp = temp.next; //temp or the head traverses through the LinkList
            }
            System.out.println(lastSong.getTittle() + " has been skipped");
            lastSong = temp;
            lastSong.next = null;
        }
    }

    // returns the information about the linked list
    public String toString() {
        Song temp = firstSong;
        StringBuilder linkedList = new StringBuilder();
        while (temp != null) {
            linkedList.append(temp.getTittle()).append(" -> ");
            temp = temp.next;
        }
        return linkedList.isEmpty() ? "Empty Playlist" : linkedList + "null";
    }

    // checks if the linked list is empty
    private boolean isEmpty() {
        return firstSong == null;
    }
}
