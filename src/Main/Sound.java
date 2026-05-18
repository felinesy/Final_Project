package Main;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public class Sound {

    private static Sound attackSound;
    private static Sound correctSound;
    private static Sound buzzerSound;

    Clip clip;

    public Sound(String resourcePath) {
        try {
            var audioStream = AudioSystem.getAudioInputStream(getClass().getResourceAsStream(resourcePath));
            clip = AudioSystem.getClip();
            clip.open(audioStream);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException | NullPointerException e) {
            System.err.println("Failed to load sound: " + resourcePath);
            e.printStackTrace();
        }
    }

    public void playLoop() {
        if (clip != null) {
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            clip.start();
        }
    }

    public void play() {
        if (clip != null) {
            clip.setFramePosition(0);
            clip.start();
        }
    }

    public void stop() {
        if (clip != null) {
            clip.stop();
        }
    }

    public static Sound getAttackSound() {
        if (attackSound == null) {
            attackSound = new Sound("/music/attack2.wav");
        }
        return attackSound;
    }

    public static Sound getCorrectSound() {
        if (correctSound == null) {
            correctSound = new Sound("/music/correct.wav");
        }
        return correctSound;
    }

    public static Sound getBuzzerSound() {
        if (buzzerSound == null) {
            buzzerSound = new Sound("/music/buzzer1.wav");
        }
        return buzzerSound;
    }
}
