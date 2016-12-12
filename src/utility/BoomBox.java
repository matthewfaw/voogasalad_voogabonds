package utility;

import java.io.File;
import java.nio.file.Paths;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public class BoomBox {
	
	private static MediaPlayer mp;
	
	public static void playMusic(String mp3Name){
		String initialPath = new File("").getAbsolutePath().replace('\\', '/');
		
		String music = Paths.get(initialPath+"/src/resources/background_music/"+mp3Name).toUri().toString();
		Media hit = new Media(music);
		mp = new MediaPlayer(hit);
		
		mp.setOnEndOfMedia(new Runnable() {
		       public void run() {
		         mp.seek(Duration.ZERO);
		       }
		   });
		  mp.play();
	}

}
