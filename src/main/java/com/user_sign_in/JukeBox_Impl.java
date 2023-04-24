package com.user_sign_in;

import com.menu.Show_Menu;
import com.playlist.CreateNewPlaylist;
import com.playlist.ExistingPlaylist;
import com.search_song.SearchSong;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public class JukeBox_Impl
{
    public static void main(String[] args) throws SQLException, ClassNotFoundException, UnsupportedAudioFileException, LineUnavailableException, IOException, UserNotFound {
        Class.forName("com.mysql.jdbc.Driver");
        Scanner sc=new Scanner(System.in);

//        The below method is for getting user login credentials.

        UserDetails user=new UserDetails();

        user.display();

        int a=sc.nextInt();
        switch (a)
        {
            case 1:
                user.newUser();
                break;
            case 2:
                user.existingUser();
                break;
        }

//        The below method is for displaying the menu and give user a choice and according to choice the output is acquired

        Show_Menu menu=new Show_Menu();
        CreateNewPlaylist playlist=new CreateNewPlaylist();
        ExistingPlaylist eplaylist=new ExistingPlaylist();
        SearchSong search=new SearchSong(0,null,null,null,null,null);
        int count=0;
        while (count==0)
        {
            System.out.println("");
            menu.displayMenu();
            System.out.println("*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*");
            int b= sc.nextInt();
            switch (b)
            {
                case 1:
                    menu.displaySongList();
                    break;
                case 2:
                    search.searchFromSongs();
                    break;
                case 3:
                    playlist.createPlaylist();
                    break;
                case 4:
                    eplaylist.existingPlaylist();
                    break;
                case 5:
                    count=1;
                    break;
            }
        }
    }
}
