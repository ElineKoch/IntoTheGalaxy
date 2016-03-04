package nl.han.ica.OOPDProcessingEngineHAN.Sound;

import ddf.minim.AudioPlayer;
import nl.han.ica.OOPDProcessingEngineHAN.Engine.GameEngine;

/**
 * Used to play music files.
 */
public class Sound {

    private AudioPlayer audio;

    /**
     * Create a new Sound object to play music files.
     * @param engine
     * @param soundFileLocation
     */
    public Sound(GameEngine engine, String soundFileLocation) {
        audio = engine.soundLibrary.loadFile(soundFileLocation);
    }

    /**
     * Starts playback from the current position.
     */
    public void play() {
        audio.play();
    }

    /**
     * Starts playback millis from the beginning.
     *
     * @param skip number of millis from the start
     */
    public void play(int skip) {
        audio.play(skip);
    }

    /**
     * Pauses playback.
     */
    public void pause() {
        audio.pause();
    }


    /**
     * Set this to loop times, set a negative value to loop continuous
     *
     * @param num amount of loops
     */
    public void loop(int num) {
        if (num < 0) {
            audio.loop();
        } else {
            audio.loop(num);
        }
    }

    /**
     * Returns true if this is currently playing and has more than one loop left to play.
     *
     * @return boolean
     */
    public boolean isLooping() {
        return audio.isLooping();
    }

    /**
     * Returns true if this currently playing.
     *
     * @return boolean, true if playing
     */
    public boolean isPlaying() {
        return audio.isPlaying();
    }

    /**
     * Sets the position to milliseconds from the beginning.
     *
     * @param millis
     */
    public void cue(int millis) {
        audio.cue(millis);
    }

    /**
     * Skips millis from the current position.
     *
     * @param millis
     */
    public void skip(int millis) {
        audio.skip(millis);
    }

    /**
     * Rewinds to the beginning.
     */
    public void rewind() {
        audio.rewind();
    }

    /**
     * Returns the number of loops left to do.
     *
     * @return number of loops left.
     */
    public int loopCount() {
        return audio.loopCount();
    }

    /**
     * Returns the length of the sound in milliseconds.
     *
     * @return length in milliseconds
     */
    public int length() {
        return audio.length();
    }

    /**
     * Returns how many milliseconds the song has played.
     *
     * @return milliseconds that the song has played.
     */
    public int position() {
        return audio.position();
    }

    /**
     * Returns the audioPlayer. Only use this function if you want to use audio analyses.
     *
     * @return AudioPlayer, this is a class in the minim library that this gameEngine uses to play sound.
     */
    public AudioPlayer getAudio() {
        return audio;
    }

}
