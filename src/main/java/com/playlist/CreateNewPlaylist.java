package com.playlist;

import com.menu.Show_Menu;
import com.playsongs.StartSong;
import com.user_sign_in.ConnectionToDatabase;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class CreateNewPlaylist
{
    Scanner sc=new Scanner(System.in);
    public void createPlaylist() throws SQLException, UnsupportedAudioFileException, LineUnavailableException, IOException {
        Show_Menu sm = new Show_Menu();
        Statement st = ConnectionToDatabase.connection();

        boolean bool = false;
        while (bool != true) {
            System.out.println("Please enter your UserName");
            String userName = sc.next();

            ResultSet r1 = st.executeQuery("select userID from userdata where userID='" + userName + "'");
            if (r1.next())
            {
                System.out.println("$~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~$");
                System.out.println("Thank you! your UserName is correct.\nBelow you can see the list of the song from which you can choose.");
                System.out.println("$~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~$");
                sm.displayList();
                System.out.println("$~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~$");
                int i = 0;
                String playlist;
                do
                {
                    System.out.println();
                    System.out.println("Please enter the name of your playlist");
                    System.out.println();
                    playlist = sc.next();

                    System.out.println("Please enter the songID of the song which you want to add in your playlist");
                    int choice = sc.nextInt();

                    ResultSet r2 = st.executeQuery("select * from songs where songID='" + choice + "'");

                    if (r2.next())
                    {
                        boolean booleanRes = st.execute("create table if not exists " + playlist + "(playlistID int auto_increment primary key, songID int,songName varchar(40), constraint foreign key (songID) references songs(songID));");
                        ResultSet r = st.executeQuery("select songName from songs where songID ='" + choice + "'");
                        r.next();
                        String songName = r.getString(1);
                        st.executeUpdate("insert into " + playlist + "(songID,songName) values('" + choice + "','" + songName + "')");
                        ResultSet rs1 = st.executeQuery("select * from " + playlist + ";");
                        System.out.println("$~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~$");
                        System.out.format("%-20s %-20s %-20s", "PlaylistID", "SongID", "SongName");
                        System.out.println();

                        while (rs1.next()) {
                            System.out.format("%-20d %-20d %-20s", rs1.getInt(1), rs1.getInt(2), rs1.getString(3));
                            System.out.println();
                        }
                        System.out.println("$~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~$");
                        System.out.println("Please enter 1 to add more songs/create new playlist\nElse press 0");
                        i = sc.nextInt();
                    }

                    else
                    {
                        System.out.println("Invalid SongID");
                    }

                } while (i != 0);

                boolean res=st.execute("create table if not exists AvailablePlaylist(PlayListName varchar(50));");
                st.executeUpdate("insert into AvailablePlaylist values('"+playlist+"')");

                System.out.println("Please enter 1 to play song from playlists Or enter 0 for main menu");
                int value = sc.nextInt();

                if (value == 1)
                {

                    while (bool != true)
                    {
                        System.out.println("Please enter the playlist name from which you want to play song");
                        String playlistname = sc.next();
                        ResultSet rs=st.executeQuery("select * from AvailablePlaylist where PlayListName='"+playlistname+"'");
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
                            System.out.println("Please enter correct playlist name");
                        }
                    }
                }

                else
                {
                    bool = true;
                }

            }

            else
            {
                System.out.println("Please enter valid username");
            }
        }
    }
}


