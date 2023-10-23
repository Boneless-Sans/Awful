package src.java.utils;

import javax.sound.sampled.*;
import java.io.File;

public class AudioPlayer{
    private String fileName;
    private AudioPlayer player;
    public void play(String fileName){
        try{
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(new File(fileName)));
            clip.start();
        }catch(Exception exc){
            exc.printStackTrace(System.out);
        }

    }
}