package com.playsongs;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public interface PlaySong
{
    public void play() throws UnsupportedAudioFileException, IOException, LineUnavailableException;
    public void pause();
    public void resume();
    public void restart();
    public void forward();
    public void backward();
}
