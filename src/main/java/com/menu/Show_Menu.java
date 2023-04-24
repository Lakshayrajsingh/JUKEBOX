package com.menu;
import com.playsongs.StartSong;
import com.user_sign_in.ConnectionToDatabase;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class Show_Menu
{
    Scanner sc=new Scanner(System.in);

    public void displayMenu()
    {
        System.out.println("Press 1 to Display the Song List");
        System.out.println("Press 2 to Search a Song ");
        System.out.println("Press 3 to Create your Playlist");
        System.out.println("Press 4 to play songs from existing Playlists");
        System.out.println("Press 5 to Exit");
    }

    public void displayList() throws SQLException
    {
        Statement st= ConnectionToDatabase.connection();
        boolean bool=false;
            ResultSet rs = st.executeQuery("select SongID,SongName,Duration,ArtistName,Genre from songs");
            System.out.format("%-10s %-20s %-20s %-20s %-20s","SongID","SongName","Duration","ArtistName","Genre");
            System.out.println();
            while (rs.next())
            {
                System.out.format("%-10d %-20s %-20s %-20s %-20s", rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
                System.out.println();
            }
    }
    public void displaySongList() throws SQLException, UnsupportedAudioFileException, LineUnavailableException, IOException
    {
        Statement st= ConnectionToDatabase.connection();
        boolean bool=false;
        while (bool!=true)
        {
            ResultSet rs = st.executeQuery("select SongID,SongName,Duration,ArtistName,Genre from songs");
            System.out.format("%-10s %-20s %-20s %-20s %-20s","SongID","SongName","Duration","ArtistName","Genre");
            System.out.println();
            while (rs.next())
            {
                System.out.format("%-10d %-20s %-20s %-20s %-20s", rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
                System.out.println();
            }

            System.out.println("Please enter 1 to play a song Or enter 0 for main menu ");
            int value=sc.nextInt();

            if(value==1)
            {
                System.out.println("Please enter Song ID to play the song from above list");
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
                bool=true;
            }
        }
    }
}
