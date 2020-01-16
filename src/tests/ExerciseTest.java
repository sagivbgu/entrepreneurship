package tests;

import javafx.application.Application;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import rateFit.*;

import java.time.Duration;

public class ExerciseTest extends Application {
    static String SONGS_DIRECTORY = "C:\\Users\\SLap\\Desktop\\Songs\\All";

    @Test
    public void run() {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        ExerciseType exerciseType = ExerciseType.Endurance5min();
        Duration duration = Duration.ofMinutes(5);
        User user = new User();
        user.setGenre(Genre.POP);
        SongsManager songsManager = new SongsManager(SONGS_DIRECTORY);
        Exercise exercise = new Exercise(exerciseType, duration, user, songsManager);
        exercise.start();
        try {
            Thread.sleep(1000 * 60 * 5 + 10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
