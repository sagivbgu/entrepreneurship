package rateFit;

import java.time.Duration;
import java.time.Instant;

public class Exercise {
    private ExerciseType type;
    private Duration duration;
    private Instant startTime;
    private User user;
    private SongsManager songsManager;

    public Exercise(ExerciseType type, Duration duration, User user) {
        this.type = type;
        this.duration = duration;
        songsManager = new SongsManager();
        this.user = user;
    }
}
