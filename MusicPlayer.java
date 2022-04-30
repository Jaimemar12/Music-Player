import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Scanner;

public class MusicPlayer {

    public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException {
        Scanner scanner = new Scanner(System.in);
        Playlist playlist = Playlist.getInstance();
        String nameOfSong;
        int option;

        System.out.println("Welcome to Jaime's Music Player!\n");
        System.out.println("Select one of the options to start:");

        while(true){

            System.out.println("1. View Playlist");
            System.out.println("2. Add a song next to the playlist");
            System.out.println("3. Add a song at the end of the playlist");
            System.out.println("4. Skip next song");
            System.out.println("5. Skip last song");
            System.out.println("6. Play next song");
            System.out.println("7. Exit");
            option = scanner.nextInt();
            System.out.println();
            scanner = new Scanner(System.in);

            switch (option) {
                case 1 -> System.out.println(playlist);

                case 2 -> {
                    System.out.println("Enter the name of the Song: ");
                    nameOfSong = scanner.nextLine();
                    System.out.println("Adding...");
                    Song song = new Song(nameOfSong);
                    if (!song.getTittle().isEmpty())
                        playlist.addNext(song);
                    else
                        System.out.println("Song not found");
                }

                case 3 -> {
                    System.out.println("Enter the name of the Song: ");
                    nameOfSong = scanner.nextLine();
                    System.out.println("Adding...");
                    Song song2 = new Song(nameOfSong);
                    if (!song2.getTittle().isEmpty())
                        playlist.addAtTheEnd(song2);
                    else
                        System.out.println("Song not found");
                }

                case 4 -> playlist.skipNext();

                case 5 -> playlist.skipTheLast();

                case 6 -> playlist.playNextSong();

                case 7 -> System.exit(0);

                default -> System.out.println("Invalid Input");
            }
            System.out.println();
        }
    }
}
