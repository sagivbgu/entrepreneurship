package rateFit;

import java.time.Duration;
import java.time.Instant;
import java.util.Timer;
import java.util.TimerTask;

public class Exercise {
    private ExerciseType type;
    private Duration duration;
    private Instant startTime;
    private User user;
    private SongsManager songsManager;

    public Exercise(ExerciseType type, Duration duration, User user, SongsManager songsManager) {
        this.type = type;
        this.duration = duration;
        this.songsManager = songsManager;
        this.user = user;
    }

    public void start() {
        this.startTime = Instant.now();
        Timer timer = new Timer(true);

        TimerTask exerciseLoop = new TimerTask() {
            @Override
            public void run() {
                System.out.println("The current time is" + Instant.now());
                if (Duration.between(Instant.now(), startTime).toMinutes() > duration.toMinutes()) {
                    System.out.println("Stopping exercise");
                    this.cancel();
                } else {
                    int desiredHeartrate = type.getDesiredHeartrate(Duration.between(startTime, Instant.now()).getSeconds());
                    songsManager.applySongAction(user.getHeartrate(), desiredHeartrate, user.getGenre());
                    Song currentSong = songsManager.getCurrentSong();
                    user.updateHeartrate(currentSong.getBpm());

                    System.out.println("HR: " + user.getHeartrate() + " Song BPM: " + currentSong.getBpm() + " Song: " + currentSong.getName());
                }
            }
        };

        timer.schedule(exerciseLoop, 0, 2000);
    }

    @Override
    public String toString() {
        return "type=" + type.getName() + ", duration in minutes=" + duration.toMinutes();
    }

    public Instant getStartTime() {
        return startTime;
    }

    public Duration getDuration() {
        return duration;
    }

    public ExerciseType getExerciseType() {
        return type;
    }

    public SongsManager getSongsManager() {
        return songsManager;
    }
}
