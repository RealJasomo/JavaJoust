package audio;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;

public class AudioManager {
	private Clip clip;
	private AudioInputStream bgm;
	public AudioManager() {
		clip = null;
		bgm = null;
		try {
			File background = new File(getClass().getResource("/bgm.wav").getFile());
			bgm = AudioSystem.getAudioInputStream(background);
			clip = AudioSystem.getClip();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void playBGM() {
		try {
			clip.open(bgm);
			clip.start();
			clip.loop(Clip.LOOP_CONTINUOUSLY);
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}
	public void stop() {
		clip.stop();
	}
}
