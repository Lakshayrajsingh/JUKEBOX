package com.search_song;

import com.menu.Show_Menu;
import com.user_sign_in.ConnectionToDatabase;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class SearchMethods
{
    int songID;
    String songName;
    String duration;
    String artistName;
    String genre;
    String filePath;
    Scanner sc=new Scanner(System.in);
    boolean bool = false;
    public SearchMethods(int songID, String songName, String duration, String artistName, String genre, String filePath)
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
        return "SearchMethods{" +
                "songID=" + songID +
                ", songName='" + songName + '\'' +
                ", duration='" + duration + '\'' +
                ", artistName='" + artistName + '\'' +
                ", genre='" + genre + '\'' +
//                ", filePath='" + filePath + '\'' +
                '}';
    }

    public List creatingList() throws SQLException
    {
        Statement st = ConnectionToDatabase.connection();
        List<SearchMethods> li = new ArrayList<>();
        Show_Menu menu = new Show_Menu();
        boolean bool = false;
        ResultSet rs = st.executeQuery("select * from songs");
        while (rs.next()) {
            SearchMethods s = new SearchMethods(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6));
            li.add(s);
        }
        return li;
    }
    public void searchSongByGenre() throws SQLException {
        Statement st = ConnectionToDatabase.connection();
        List<SearchMethods> li=creatingList();
        while (bool != true)
        {
            System.out.println("Please enter the Genre through which you like to search(first letter of the genre should be capital)");
            String genre = sc.next();
            ResultSet rs = st.executeQuery("select * from songs where Genre like'" + genre + "'");

            if (rs.next())
            {
                Iterator<SearchMethods> is = li.iterator();
                while (is.hasNext())
                {
                    SearchMethods s2 = is.next();
                    if (s2.genre.contains(genre)) {
                        System.out.format("%-10d %-20s %-20s %-20s", s2.songID, s2.songName, s2.artistName, s2.genre);
                        System.out.println();
                    }
                }
                bool=true;
            }

            else
            {
                System.out.println("*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*");
                System.out.println(" Invalid genre!! Please enter correct Genre");
                System.out.println("*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*");
            }
        }
    }
    public void searchSongBySongName() throws SQLException
    {
        Statement st = ConnectionToDatabase.connection();
        List<SearchMethods> li=creatingList();
        while (bool != true)
        {
            System.out.println("Please enter the name of song which you like to search(first letter of the song should be capital)");
            String song = sc.next();
            ResultSet rs = st.executeQuery("select * from songs where songName like'" + song + "'");
            if (rs.next()) {
                Iterator<SearchMethods> is = li.iterator();
                while (is.hasNext()) {
                    SearchMethods s2 = is.next();

                    if (s2.songName.contains(song)) {
                        System.out.format("%-10d %-20s %-20s %-20s", s2.songID, s2.songName, s2.artistName, s2.genre);
                        System.out.println();
                    }
                }
                bool = true;
            }

            else {
                System.out.println("*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*");
                System.out.println("Invalid Song Name!! Please enter correct song name");
                System.out.println("*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*");
            }
        }
    }
    public void searchSongByArtistName() throws SQLException {
        Statement st = ConnectionToDatabase.connection();
        List<SearchMethods>li=creatingList();
        while (bool != true) {
            System.out.println("Please enter the Name of Artist whose song you like to search\n(first letter of the Artist should be capital and there should be no spaces)");
            String artist = sc.next();
            ResultSet rs = st.executeQuery("select * from songs where ArtistName like'" + artist + "'");
            if (rs.next())
            {
                Iterator<SearchMethods> is = li.iterator();
                while (is.hasNext())
                {
                    SearchMethods s2 = is.next();
                    if (s2.artistName.contains(artist))
                    {
                        System.out.format("%-10d %-20s %-20s %-20s", s2.songID, s2.songName, s2.artistName, s2.genre);
                        System.out.println();
                    }

                }
                bool = true;
            }

            else {
                System.out.println("*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*");
                System.out.println(" Invalid Artist Name!!Please enter correct Artist Name");
                System.out.println("*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*");
            }
        }

    }
    public void searchSongByAlphabet() throws SQLException {
        Statement st = ConnectionToDatabase.connection();
        List<SearchMethods> li=creatingList();

        while (bool != true)
        {
            System.out.println("Please enter the First letter of the song which you like to search(first letter of the song should be capital)");
            String alpha = sc.next();
            ResultSet rs = st.executeQuery("select * from songs where songName like '" + alpha + "%'");

            if (rs.next())
            {
                Iterator<SearchMethods> is = li.iterator();
                while (is.hasNext()) {
                    SearchMethods s2 = is.next();

                    if (s2.songName.contains(alpha)) {
                        System.out.format("%-10d %-20s %-20s %-20s", s2.songID, s2.songName, s2.artistName, s2.genre);
                        System.out.println();
                    }
                }
                bool = true;
            }

            else
            {
                System.out.println("*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*");
                System.out.println(" Invalid Alphabet!! Please enter a capital Alphabet");
                System.out.println("*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*");
            }
        }
    }
}

