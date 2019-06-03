import java.net.URL;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Music{
	private Clip clip1;
	private Clip clip2;
	private Clip clip3;
	private Clip clip4;
	private Clip clip5;
	public void play(){
		try {
	        URL url = this.getClass().getClassLoader().getResource("./sounds/makeemwaitforit.wav");
	        clip1 = AudioSystem.getClip();
	        clip1.open(AudioSystem.getAudioInputStream(url));
	        clip1.start();
	    } catch (Exception exc) {
	        exc.printStackTrace(System.out);
	    }
	}

	public void shoot(){
		try {
	        URL url = this.getClass().getClassLoader().getResource("./sounds/shoot.wav");
	        clip2 = AudioSystem.getClip();
	        clip2.open(AudioSystem.getAudioInputStream(url));
	        clip2.start();
	    } catch (Exception exc) {
	        exc.printStackTrace(System.out);
	    }
	}

	public void boom(){
		try {
	        URL url = this.getClass().getClassLoader().getResource("./sounds/boom.wav");
	        clip3 = AudioSystem.getClip();
	        clip3.open(AudioSystem.getAudioInputStream(url));
	        clip3.start();
	    } catch (Exception exc) {
	        exc.printStackTrace(System.out);
	    }
	}

	public void rickRoll(){
		try {
	        URL url = this.getClass().getClassLoader().getResource("./sounds/rickroll.wav");
	        clip4 = AudioSystem.getClip();
	        clip4.open(AudioSystem.getAudioInputStream(url));
	        clip4.start();
	    } catch (Exception exc) {
	        exc.printStackTrace(System.out);
	    }
	}
	public void damage(){
		try {
	        URL url = this.getClass().getClassLoader().getResource("./sounds/bomb2.wav");
	        clip5 = AudioSystem.getClip();
	        clip5.open(AudioSystem.getAudioInputStream(url));
	        clip5.start();
	    } catch (Exception exc) {
	        exc.printStackTrace(System.out);
	    }
	}
	public void stopMusic(){
		System.out.print("Stopped");

		if(clip1 != null){
			clip1.stop();
		}
		if(clip2 != null){
			clip2.stop();
		}
		if(clip3 != null){
			clip3.stop();
		}

		if(clip4 != null){
			clip4.stop();
		}

		if(clip5 != null){
			clip5.stop();
		}

	}
}
