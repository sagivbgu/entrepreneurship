package rateFit;

import java.time.Duration;
import java.time.Instant;

public class Excersice {
    private ExcerciseType type;
    private Duration duration;
    private Instant startTime;
    private User user;
    private SongsManager songsManager;

    public Excersice(ExcerciseType type, Duration duration, User user) {
        this.type = type;
        this.duration = duration;
        songsManager = new SongsManager();
        this.user = user;
    }
//git
}
