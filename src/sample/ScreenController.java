package sample;

import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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

public class ScreenController {
    private static ScreenController instance = null;
    private HashMap<String, Parent> screenMap = new HashMap<>();
    private Scene main;
    private User user;
    private Exercise exercise;
    private ArrayList<int[]> list;
    private SongsManager songsManager;

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
                        exercise = new Exercise(new ExerciseType("FAT LOSS", list), Duration.ofMinutes(5), user, songsManager);
                    }
                    else {
                        exercise = new Exercise(new ExerciseType("FAT LOSS", list), Duration.ofMinutes(10), user, songsManager);
                    }
                }
                else {
                    if (button3.isSelected()) {
                        exercise = new Exercise(new ExerciseType("ENDURANCE", list), Duration.ofMinutes(5), user, songsManager);
                    }
                    else {
                        exercise = new Exercise(new ExerciseType("ENDURANCE", list), Duration.ofMinutes(10), user, songsManager);
                    }
                }
                setExersiceScreen();
            }
        } );
        return root;
    }

    private void setExersiceScreen() {
        GridPane root = new GridPane();
        root.setAlignment(Pos.CENTER);
        root.setVgap(10);
        Label msg = new Label("Exercise is underway");
        root.addRow(0, msg);
        Task task = new Task<Void>() {
            @Override public Void call() {
                final long max = exercise.getDuration().toSeconds();
                long currTime = 0;
                while (currTime < max) {
                    if (isCancelled()) {
                        break;
                    }
                    System.out.println(currTime);
                    updateProgress(currTime, max);
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    currTime = currTime + 5;
                }
                return null;
            }
        };
        ProgressBar bar = new ProgressBar();
        bar.progressProperty().bind(task.progressProperty());
        root.addRow(1, bar);
        main.setRoot(root);
//        Label song = new Label("Current song: " + exercise.getSongsManager().getCurrentSong().getName());
//        root.addRow(2, song);
        new Thread(task).start();
    }
}