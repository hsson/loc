package edu.chl.loc.view.music;

import com.badlogic.gdx.audio.Music;

import java.util.Arrays;
import java.util.List;

/**
 * @author Alexander Håkansson
 * @version 1.0.0
 * @since 2015-05-07
 */
public class Playlist implements Music.OnCompletionListener {
    List<Music> songs;
    private boolean looping;
    private int amountOfSongs;
    private int currentSong = 0;

    public Playlist(boolean looping, Music... songs) {
        this.looping = looping;
        this.songs = Arrays.asList(songs);
        amountOfSongs = this.songs.size();
    }

    public void setLooping(boolean looping) {
        this.looping = looping;
    }

    public void play() {
        Music song = songs.get(currentSong);
        song.setOnCompletionListener(this);
        song.play();
    }

    @Override
    public void onCompletion(Music music) {
        currentSong++;

        if (currentSong == amountOfSongs && looping) {
            currentSong = 0;
        }

        play();
    }
}
