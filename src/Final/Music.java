package Final;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Music extends Thread{
	
    @Override
    public void run(){
            try {
            	File file = new File("music\\musicc.wav");
            	AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
            	Clip clip = AudioSystem.getClip();
            	clip.open(audioStream);
            	clip.start();
            	clip.loop(Clip.LOOP_CONTINUOUSLY);
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
            	e1.printStackTrace();
            }
    }
}
