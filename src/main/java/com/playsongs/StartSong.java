package com.playsongs;
import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ListIterator;
import java.util.Scanner;


public class StartSong implements PlaySong
{
    Scanner sc = new Scanner(System.in);
    AudioInputStream audioStream;
    String file;
    Clip clip;
    long currentFrame;

    public StartSong(String file) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        this.file = file;
        audioStream=AudioSystem.getAudioInputStream(new File(file));
        clip=AudioSystem.getClip();
    }

    @Override
    public void play() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        audioStream=AudioSystem.getAudioInputStream(new File(file));
        clip.start();
        clip.loop(Clip.LOOP_CONTINUOUSLY);
        System.out.println("Playing Song");
    }

    @Override
    public void pause()
    {
        this.currentFrame=this.clip.getMicrosecondPosition();
        clip.stop();
        System.out.println("Song Paused");
    }

    @Override
    public void resume()
    {
        clip.setMicrosecondPosition(currentFrame);
        clip.start();
        System.out.println("Song Resumed");
    }

    @Override
    public void restart()
    {
        clip.setMicrosecondPosition(0);
        clip.start();
        System.out.println("Song Restarted");
    }

    @Override
    public void forward()
    {
        System.out.println("Forwarding song by 40s");
        clip.setMicrosecondPosition(clip.getMicrosecondPosition() + 4000000);
    }

    @Override
    public void backward()
    {
        System.out.println("Backward song by 40s");
        clip.setMicrosecondPosition(clip.getMicrosecondPosition() - 4000000);
    }
    public void playMusic(String filepath) throws IOException, LineUnavailableException, UnsupportedAudioFileException,NullPointerException
    {
        audioStream = AudioSystem.getAudioInputStream(new File(file));
        clip.open(audioStream);
        int i;
        int count = 0;
        currentFrame = 0;

        while (count == 0)
        {
            System.out.println("\nEnter Your Choice\n~~~~~~~~~~~~~~~~~~~~~~~~~\n 1. Play\n 2. Pause\n 3. Resume\n 4. Restart\n 5. Forward\n 6. Backwards\n 7. Return to main menu\n~~~~~~~~~~~~~~~~~~~~~~~~~");

            i = sc.nextInt();
            switch (i) {
                case 1:
                    play();
                    break;
                case 2:
                    pause();
                    break;
                case 3:
                    resume();
                    break;
                case 4:
                    restart();
                    break;
                case 5:
                    forward();
                    break;
                case 6:
                    backward();
                    break;

                case 7:
                    clip.close();
                    count = 1;
                    break;
                default:
                    System.out.println("Not a valid Input");
            }
        }
    }
}
