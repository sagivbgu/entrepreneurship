package rateFit;


import javafx.collections.MapChangeListener;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.util.Duration;

import java.time.Instant;

public class Song {
    final static String POP_GENRE_NAME = "Pop";
    final static String ROCK_GENRE_NAME = "Rock";
    final static String BPM_TAG_NAME = "track count";

    private String songFilePath;
    private AudioClip audioClip;

    private Genre genre;
    Duration duration;
    int bpm;
    Instant startedAt;
    int playbackSpeed;

    public Song(String songFilePath) {
        this.songFilePath = songFilePath;
        setSongMetadata(new Media(songFilePath));
        audioClip = new AudioClip(songFilePath);
        startedAt = null;
    }

    public String getName() {
        return songFilePath;
    }

    public Genre getGenre() {
        return genre;
    }

    public Duration getDuration() {
        return duration;
    }

    public Instant getStartedAt() {
        return startedAt;
    }

    public int getBpm() {
        return bpm;
    }

    public int getPlaybackSpeed() {
        return playbackSpeed;
    }

    private void setSongMetadata(Media media) {
        media.getMetadata().addListener((MapChangeListener<String, Object>) change -> {
            if (change.wasAdded()) {
                if ("duration".equals(change.getKey())) {
                    duration = (Duration) change.getValueAdded();
                } else if (BPM_TAG_NAME.equals(change.getKey())) {
                    bpm = (Integer) change.getValueAdded();
                } else if ("genre".equals(change.getKey())) {
                    String genreName = change.getValueAdded().toString();
                    if (genreName.equals(POP_GENRE_NAME))
                        genre = Genre.POP;
                    else if (genreName.equals(ROCK_GENRE_NAME))
                        genre = Genre.ROCK;
                    else
                        genre = null;
                    }
            }
        });
    }
}
