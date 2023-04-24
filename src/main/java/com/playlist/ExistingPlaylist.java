package com.playlist;

import com.playsongs.StartSong;
import com.user_sign_in.ConnectionToDatabase;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class ExistingPlaylist
{
    Scanner sc=new Scanner(System.in);
    public void existingPlaylist() throws SQLException, UnsupportedAudioFileException, LineUnavailableException, IOException {
        Statement st = ConnectionToDatabase.connection();
        boolean bool=false;
        while(bool!=true)
        {
            System.out.println("Please enter the playlist name from which you want to play song");
            String playlistname = sc.next();
            ResultSet rs = st.executeQuery("select * from AvailablePlaylist where PlayListName='" + playlistname + "'");
            if (rs.next())
            {
                ResultSet rs1 = st.executeQuery("select * from " + playlistname + ";");
                System.out.println("$~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~$");
                System.out.format("%-20s %-20s %-20s", "PlaylistID", "SongID", "SongName");
                System.out.println();

                while (rs1.next()) {
                    System.out.format("%-20d %-20d %-20s", rs1.getInt(1), rs1.getInt(2), rs1.getString(3));
                    System.out.println();
                }

                System.out.println("$~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~$");
                System.out.println("Please enter playlistID to play the song from above playlist");
                int ID = sc.nextInt();
                ResultSet r = st.executeQuery("select songName from " + playlistname + " where playlistID ='" + ID + "'");
                r.next();
                String songName = r.getString(1);
                String playSong = "C:\\Users\\laksh\\Desktop\\clone projects\\NIIT_Project_JukeBox\\src\\main\\resources\\".concat(songName.concat(".wav"));
                StartSong play = new StartSong(playSong);
                play.playMusic(playSong);
                bool = true;
            }
            else
            {
                System.out.println("Please enter correct playlist Name");
            }
        }
    }
}
