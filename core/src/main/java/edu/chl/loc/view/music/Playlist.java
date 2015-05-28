package edu.chl.loc.view.music;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author Alexander Håkansson
 * @version 1.0.1
 * @since 2015-05-07
 * Revised by Alexander Karlsson
 */
public class Playlist implements Music.OnCompletionListener {
    List<Music> songs = new ArrayList<Music>();
    private boolean looping;
    private boolean random;
    private int currentSong = 0;
    private Random randomizer = new Random();
    private boolean fistTime = true;

    private static Playlist instance = null;

    // Music tracks
    private static final Music musicNyan = Gdx.audio.newMusic(Gdx.files.internal("music/nyan.mp3"));
    private static final Music musicRickroll = Gdx.audio.newMusic(Gdx.files.internal("music/rickroll.mp3"));
    private static final Music musicSax = Gdx.audio.newMusic(Gdx.files.internal("music/sax.mp3"));
    private static final Music musicTrololo = Gdx.audio.newMusic(Gdx.files.internal("music/trololo.mp3"));
    private static final Music marioLevels = Gdx.audio.newMusic(Gdx.files.internal("music/marioLevels.mp3"));

    public Playlist() {
        // Singleton
    }

    public synchronized static Playlist getInstance() {
        if (instance == null) {
            instance = new Playlist();
            instance.songs.add(musicNyan);
            instance.songs.add(musicRickroll);
            instance.songs.add(musicSax);
            instance.songs.add(musicTrololo);
            instance.songs.add(marioLevels);
        }

        return instance;
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
        if (fistTime && random) {
            currentSong = randomizer.nextInt(this.songs.size());
            fistTime = false;
        }

        Music song = songs.get(currentSong);
        song.setOnCompletionListener(this);
        song.play();
    }

    /**
     * Skips to next song.
     */
    public void next() {
        if (songs.get(currentSong).isPlaying()) {
            songs.get(currentSong).stop();
        }
        onCompletion(songs.get(currentSong));
    }

    /**
     * Stops the music
     */
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

        if (currentSong == songs.size()) {
            currentSong = 0;

            if (!looping) {
                return;
            }
        }

        play();
    }
}
