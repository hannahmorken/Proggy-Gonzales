package sound;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 * Sound class
 * 
 * Class for playing audio
 *
 */
public class Sound {

	Clip clip;
	final URL[] soundURL = new URL[12];
	
	/**
	 * Sets the audio file
	 */
	public Sound() {
		soundURL[0] = getClass().getResource("/audio/menuSelectionClick.wav");
		soundURL[1] = getClass().getResource("/audio/sfx_coin_single6.wav");
		soundURL[2] = getClass().getResource("/audio/sfx_coin_single1.wav");
		soundURL[3] = getClass().getResource("/audio/sfx_movement_jump2.wav");
		soundURL[4] = getClass().getResource("/audio/sfx_sounds_fanfare1.wav");
		soundURL[5] = getClass().getResource("/audio/sfx_sound_shutdown2.wav");
		soundURL[6] = getClass().getResource("/audio/sfx_sounds_fanfare1_reverse.wav");
		soundURL[7] = getClass().getResource("/audio/juhani_junkala_retro_bg_lvl1_music.wav");
		soundURL[8] = getClass().getResource("/audio/juhani_junkala_retro_bg_lvl2_music.wav");
		soundURL[9] = getClass().getResource("/audio/juhani_junkala_retro_bg_lvl3_music.wav");
		soundURL[10] = getClass().getResource("/audio/juhani_junkala_title_screen_music.wav");
		soundURL[11] = getClass().getResource("/audio/juhani_junkala_game_over.wav");
	}
	
	/**
	 * Sets the audio file
	 * @param i
	 */
	public void setFile(int i) {
		try {
			
			AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
			clip = AudioSystem.getClip();
			clip.open(ais);
		}
		catch(Exception e){
			
		}
		
	}
	
	/**
	 * Starts audio file
	 */
	public void play() {
		clip.start();
	}
	
	/**
	 * Loops audio file continuously
	 */
	public void loop() {
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}
	
	/**
	 * Stops the audio file
	 */
	public void stop() {
		clip.stop();
		
	}
}
