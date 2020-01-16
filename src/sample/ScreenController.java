package sample;

import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.converter.NumberStringConverter;
import rateFit.Exercise;
import rateFit.ExerciseType;
import rateFit.SongsManager;
import rateFit.User;

import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

public class ScreenController {
    private static ScreenController instance = null;
    private HashMap<String, Parent> screenMap = new HashMap<>();
    private Scene main;
    private User user;
    private Exercise exercise;
    private ExerciseType exerciseType;
    private ArrayList<int[]> list;
    private SongsManager songsManager;
    private Stage primaryStage;

    private ScreenController() {

    }

    public static ScreenController getInstance()
    {
        if (instance == null)
            instance = new ScreenController();

        return instance;
    }

    public void addScene(Scene scene) {
        main = scene;
    }

    public void setSongsManager(SongsManager songsManager) {
        this.songsManager = songsManager;
    }

    public void setPrimaryStage (Stage stage) {
        primaryStage = stage;
    }

    protected void addScreen(String name, String fxml){
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
        Parent pane = null;
        try {
            pane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Controller controller = loader.getController();
        controller.setUser(user);
        if (name.equals("typeScreen")) {
            pane = setTypeScreen();
        }
        screenMap.put(name, pane);
    }

    protected void removeScreen(String name){
        screenMap.remove(name);
    }

    protected void activate(String name){
        main.setRoot( screenMap.get(name) );
    }

    public void setUser (User user) {
        this.user = user;
    }

    private Parent setTypeScreen() {
        GridPane root = new GridPane();
        root.setAlignment(Pos.CENTER);
        root.setVgap(10);
        Label type = new Label("Choose workout type:");
        root.addRow(0, type);
        ToggleGroup group1 = new ToggleGroup();
        RadioButton button1 = new RadioButton("FAT LOSS");
        button1.setToggleGroup(group1);
        button1.setSelected(true);
        RadioButton button2 = new RadioButton("ENDURANCE");
        button2.setToggleGroup(group1);
        root.addRow(1, button1);
        root.addRow(1, button2);
        Label duration = new Label("Choose workout duration:");
        root.addRow(5, duration);
        ToggleGroup group2 = new ToggleGroup();
        RadioButton button3 = new RadioButton("5 MINUTES");
        button3.setToggleGroup(group2);
        button3.setSelected(true);
        RadioButton button4 = new RadioButton("10 MINUTES");
        button4.setToggleGroup(group2);
        root.addRow(6, button3);
        root.addRow(6, button4);
        Button btn = new Button("Start!");
        root.add(btn, 0, 8);
        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {
                if (button1.isSelected()) {
                    if (button3.isSelected()) {
                        exerciseType = ExerciseType.FatLoss5min();
                        exercise = new Exercise(exerciseType, Duration.ofMinutes(5), user, songsManager);
                    }
                    else {
                        exerciseType = ExerciseType.FatLoss10min();
                        exercise = new Exercise(exerciseType, Duration.ofMinutes(10), user, songsManager);
                    }
                }
                else {
                    if (button3.isSelected()) {
                        exerciseType = ExerciseType.Endurance5min();
                        exercise = new Exercise(exerciseType, Duration.ofMinutes(5), user, songsManager);
                    }
                    else {
                        exerciseType = ExerciseType.Endurance10min();
                        exercise = new Exercise(exerciseType, Duration.ofMinutes(10), user, songsManager);
                    }
                }
                setExerciseScreen();
            }
        } );
        return root;
    }

    private void setExerciseScreen() {
        exercise.start();
        GridPane root = new GridPane();
        root.setAlignment(Pos.CENTER);
        root.setVgap(5);
        root.getStylesheets().add("stylesheet.css");
        Task progressbar = new Task<Void>() {
            @Override public Void call() {
                final long max = exercise.getDuration().toSeconds();
                long currTime = 0;
                while (currTime < max) {
                    if (isCancelled()) {
                        break;
                    }
                    updateProgress(currTime, max);
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                    }
                    currTime = currTime + 5;
                }
                return null;
            }
        };
        Task timertask = new Task<Void>() {
            @Override public Void call() {
                while (!isCancelled()) {
                    if (Duration.between(exercise.getStartTime(), Instant.now()).toMinutes() >= exercise.getDuration().toMinutes()) {
                        System.out.println("EXERCISE IS OVER!!!!!");
                        this.cancel();
                    }
                    updateMessage(formatDuration(Duration.between(exercise.getStartTime(), Instant.now())));
                }
                return null;
            }
        };
        Task songsname = new Task<Void>() {
            @Override public Void call() {
                while (!isCancelled()) {
                    if (exercise.getSongsManager().getCurrentSong() != null)
                    updateMessage(exercise.getSongsManager().getCurrentSong().getName());
                }
                return null;
            }
        };
        Task songsbpm = new Task<Void>() {
            @Override public Void call() {
                while (!isCancelled()) {
                    if (exercise.getSongsManager().getCurrentSong() != null)
                        updateMessage("BPM: " + exercise.getSongsManager().getCurrentSong().getBpm());
                }
                return null;
            }
        };
        Task heartrateProgress = new Task<Void>() {
            @Override public Void call() {
                while (!isCancelled()) {
                    updateMessage("Current HR: " + user.getHeartrate() +"\nDesired HR: " + exercise.getExerciseType().getDesiredHeartrate(Duration.between(exercise.getStartTime(), Instant.now()).getSeconds()));
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                    }

                }
                return null;
            }
        };
        ProgressBar bar = new ProgressBar();
        bar.progressProperty().bind(progressbar.progressProperty());
        root.addRow(12, bar);
        main.setRoot(root);
        Label song = new Label(" ");
        song.setStyle("-fx-font-weight: bold");
        song.textProperty().bind(songsname.messageProperty());
        Label bpm = new Label(" ");
        bpm.textProperty().bind(songsbpm.messageProperty());
        root.addRow(1, song);
        root.addRow(2, bpm);
        Label heartrate = new Label("Current heartrate: 0\nDesired heartrate: 0");
        heartrate.textProperty().bind(heartrateProgress.messageProperty());
        root.addRow(5, heartrate);
        Label duration = new Label ("");
        duration.textProperty().bind(timertask.messageProperty());
        root.addRow(13, duration);
        new Thread(progressbar).start();
        new Thread(songsname).start();
        new Thread(songsbpm).start();
        new Thread(heartrateProgress).start();
        new Thread(timertask).start();
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent t) {
                progressbar.cancel();
                songsname.cancel();
                songsbpm.cancel();
                heartrateProgress.cancel();
                timertask.cancel();
            }
        });
    }

    public static String formatDuration(Duration duration) {
        long seconds = duration.getSeconds();
        long absSeconds = Math.abs(seconds);
        String positive = String.format(
                "%d:%02d:%02d",
                absSeconds / 3600,
                (absSeconds % 3600) / 60,
                absSeconds % 60);
        return seconds < 0 ? "-" + positive : positive;
    }
}