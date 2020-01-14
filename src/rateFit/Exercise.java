package rateFit;

import java.time.Duration;
import java.time.Instant;
import java.util.Timer;

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
        /*Timer timer = new Timer();
        timer.schedule(new SayHello(), 0, 5000);*/
        exerciseLoop();
    }

    private void exerciseLoop() {
        int desiredHeartrate = type.getDesiredHeartrate(Duration.between(Instant.now(), startTime).getSeconds());
        songsManager.applySongAction(user.getHeartrate(), desiredHeartrate, user.getGenre());
    }
}
