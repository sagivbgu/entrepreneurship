package rateFit;

import java.time.Duration;
import java.time.Instant;

public class Exercise {
    private ExerciseType type;
    private Duration duration;
    private Instant startTime;
    private User user;
    private SongsManager songsManager;

    public Exercise(ExerciseType type, Duration duration, User user, SongsManager songsManager) {
        this.type = type;
        this.duration = duration;
        songsManager = songsManager;
        this.user = user;
    }

    public void start() {

    }
}
