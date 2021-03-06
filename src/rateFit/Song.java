package rateFit;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.io.File;
import java.time.Instant;
import java.util.Map;

public class Song {
    final static String POP_GENRE_NAME = "Pop";
    final static String ROCK_GENRE_NAME = "Rock";
    final static String BPM_TAG_NAME = "year";

    private MediaPlayer mediaPlayer;

    private String name;
    private Genre genre;
    private Duration duration;
    private int bpm;
    private Instant startedAt;
    private int playbackSpeed;

    public Song(String songFilePath) {
        String fullPath = new File(songFilePath).toURI().toString();
        mediaPlayer = new MediaPlayer(new Media(fullPath));
        setSongMetadata(mediaPlayer);
        startedAt = null;
    }

    public String getName() {
        return name;
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

    public void play() {
        mediaPlayer.play();
        startedAt = Instant.now();
    }

    public void stop() {
        mediaPlayer.stop();
    }

    private void setSongMetadata(MediaPlayer mediaPlayer) {
        mediaPlayer.setOnReady(new Runnable() {
            @Override
            public void run() {
                Media media = mediaPlayer.getMedia();
                duration = media.getDuration();
                for (Map.Entry<String, Object> entry : media.getMetadata().entrySet()) {
                    if (entry.getKey().equals("genre")) {
                        String genreName = entry.getValue().toString();
                        if (genreName.equals(POP_GENRE_NAME))
                            genre = Genre.POP;
                        else if (genreName.equals(ROCK_GENRE_NAME))
                            genre = Genre.ROCK;
                        else
                            genre = null;
                    } else if (entry.getKey().equals(BPM_TAG_NAME)) {
                        bpm = (Integer) entry.getValue();
                    } else if (entry.getKey().equals("title")) {
                        name = entry.getValue().toString();
                    }
                }
            }
        });
    }
}
