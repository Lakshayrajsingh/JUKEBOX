package com.search_song;

import com.menu.Show_Menu;
import com.playsongs.StartSong;
import com.user_sign_in.ConnectionToDatabase;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class SearchSong
{
    int songID;
    String songName;
    String duration;
    String artistName;
    String genre;
    String filePath;
    Scanner sc=new Scanner(System.in);

    public SearchSong(int songID, String songName, String duration, String artistName, String genre, String filePath)
    {
        this.songID = songID;
        this.songName = songName;
        this.duration = duration;
        this.artistName = artistName;
        this.genre = genre;
        this.filePath = filePath;
    }

    @Override
    public String toString() {
        return "SearchSong{" +
                "songID=" + songID +
                ", songName='" + songName + '\'' +
                ", duration='" + duration + '\'' +
                ", artistName='" + artistName + '\'' +
                ", genre='" + genre + '\'' +
//                ", filePath='" + filePath + '\'' +
                '}';
    }

    public void searchFromSongs() throws SQLException {
        Statement st = ConnectionToDatabase.connection();
        Show_Menu menu=new Show_Menu();
        SearchMethods search=new SearchMethods(0,null,null,null,null,null);
        List li=search.creatingList();
        ResultSet rs = null;
        try
        {
            boolean bool = false;
            while (bool != true) {
                System.out.println("*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*");
                System.out.println("Press 1 to search song through genre(English,Hindi,Punjabi)");
                System.out.println("Press 2 to search song through Song Name");
                System.out.println("Press 3 to search song through Artist Name");
                System.out.println("Press 4 to search song through Alphabetical Search");
                System.out.println("Press 5 for the main menu");
                System.out.println("*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*");

                int b = sc.nextInt();
                if (b == 1) {
                    System.out.println("$~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~$");
                    menu.displayList();
                    System.out.println("$~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~$");
                    search.searchSongByGenre();

                    System.out.println("Please enter 1 to play songs from the search result or 0 to return on main menu");
                    int choice = sc.nextInt();
                    if (choice == 1) {
                        System.out.println("Please enter Song ID to play the song");
                        int ID = sc.nextInt();
                        ResultSet r = st.executeQuery("select songName from songs where songID ='" + ID + "'");
                        r.next();
                        String songName = r.getString(1);
                        String playSong = "C:\\Users\\laksh\\Desktop\\clone projects\\NIIT_Project_JukeBox\\src\\main\\resources\\".concat(songName.concat(".wav"));
                        StartSong play = new StartSong(playSong);
                        play.playMusic(playSong);
                    }

                    else {
                        bool = true;
                    }
                }

                else if (b == 2) {
                    System.out.println("$~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~$");
                    menu.displayList();
                    System.out.println("$~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~$");
                    search.searchSongBySongName();

                    System.out.println("Please enter 1 to play songs from the search result or 0 to return on main menu");
                    int choice = sc.nextInt();
                    if (choice == 1) {
                        System.out.println();
                        System.out.println("Please enter Song ID to play the song");
                        int ID = sc.nextInt();
                        ResultSet r = st.executeQuery("select songName from songs where songID ='" + ID + "'");
                        r.next();
                        String songName = r.getString(1);
                        String playSong = "C:\\Users\\laksh\\Desktop\\clone projects\\NIIT_Project_JukeBox\\src\\main\\resources\\".concat(songName.concat(".wav"));
                        StartSong play = new StartSong(playSong);
                        play.playMusic(playSong);
                    }

                    else {
                        bool = true;
                    }
                }

                else if (b == 3) {
                    System.out.println("$~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~$");
                    menu.displayList();
                    System.out.println("$~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~$");
                    search.searchSongByArtistName();

                    System.out.println("Please enter 1 to play songs from the search result or 0 to return on main menu");
                    int choice = sc.nextInt();
                    if (choice == 1) {
                        System.out.println();
                        System.out.println("Please enter Song ID to play the song");
                        int ID = sc.nextInt();
                        ResultSet r = st.executeQuery("select songName from songs where songID ='" + ID + "'");
                        r.next();
                        String songName = r.getString(1);
                        String playSong = "C:\\Users\\laksh\\Desktop\\clone projects\\NIIT_Project_JukeBox\\src\\main\\resources\\".concat(songName.concat(".wav"));
                        StartSong play = new StartSong(playSong);
                        play.playMusic(playSong);
                    }

                    else {
                        bool = true;
                    }
                }

                else if (b == 4) {
                    System.out.println("$~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~$");
                    menu.displayList();
                    System.out.println("$~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~$");

                    search.searchSongByAlphabet();
                    System.out.println("Please enter 1 to play songs from the search result or 0 to return on main menu");
                    int choice = sc.nextInt();
                    if (choice == 1) {
                        System.out.println();
                        System.out.println("Please enter Song ID to play the song");
                        int ID = sc.nextInt();
                        ResultSet r = st.executeQuery("select songName from songs where songID ='" + ID + "'");
                        r.next();
                        String songName = r.getString(1);
                        String playSong = "C:\\Users\\laksh\\Desktop\\clone projects\\NIIT_Project_JukeBox\\src\\main\\resources\\".concat(songName.concat(".wav"));
                        StartSong play = new StartSong(playSong);
                        play.playMusic(playSong);
                    }

                    else
                    {
                        bool = true;
                    }
                }

                else if(b==5)
                {
                    bool=true;
                }
                else
                {
                    System.out.println("Please enter a valid entry");
                }
            }
        }
        catch(SQLException e)
            {
                throw new RuntimeException(e);
            }

        catch(UnsupportedAudioFileException e)
            {
                throw new RuntimeException(e);
            }

        catch(LineUnavailableException e)
            {
                throw new RuntimeException(e);
            }

        catch(IOException e)
            {
                throw new RuntimeException(e);
            }
    }
}
