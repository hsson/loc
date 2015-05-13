package edu.chl.loc.view.music;

import com.badlogic.gdx.audio.Music;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * @author Alexander Håkansson
 * @version 1.0.0
 * @since 2015-05-07
 */
public class Playlist implements Music.OnCompletionListener {
    List<Music> songs;
    private boolean looping;
    private boolean random;
    private int currentSong = 0;
    private Random randomizer = new Random();

    public Playlist(boolean looping, boolean random, Music... songs) {
        this.looping = looping;
        this.random = random;
        this.songs = Arrays.asList(songs);

        if (random) {
            currentSong = randomizer.nextInt(this.songs.size());
        }
    }

    /**
     * Set if the playlist should start over once all
     * songs have been played.
     *
     * @param looping Determines if the playlist should loop
     */
    public void setLooping(boolean looping) {
        this.looping = looping;
    }

    /**
     * Set if the playlist should play its songs in a random
     * order or not.
     *
     * @param random Determines if the playlist should be random
     */
    public void setRandom(boolean random) {
        this.random = random;
    }

    public void play() {
        Music song = songs.get(currentSong);
        song.setOnCompletionListener(this);
        song.play();
    }

    public void stop(){
        songs.get(currentSong).stop();
    }

    @Override
    public void onCompletion(Music music) {
        if (random) {
            int lastSong = currentSong;
            do {
                currentSong = randomizer.nextInt(songs.size());
            } while (currentSong == lastSong);
        } else {
            currentSong++;
        }

        if (currentSong == songs.size() && looping) {
            currentSong = 0;
        }

        play();
    }
}
