package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import rateFit.SongsManager;
import rateFit.User;

public class Main extends Application {
    private ScreenController screenController = ScreenController.getInstance();

    @Override
    public void start(Stage primaryStage) throws Exception{
        User user = new User();
        SongsManager songsManager = new SongsManager("C:\\Users\\liaru\\Downloads\\Songs");
        screenController.setUser(user);
        screenController.setSongsManager(songsManager);
        screenController.setPrimaryStage(primaryStage);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("homeScreen.fxml"));
        Parent root = loader.load();
        Controller controller = loader.getController();
        controller.setUser(user);
        primaryStage.setTitle("RateFit");
        Scene scene = new Scene(root, 300, 275);
        screenController.addScene(scene);
        screenController.addScreen("home","homeScreen.fxml");
        screenController.addScreen("genreScreen","genreScreen.fxml");
        screenController.addScreen("typeScreen","typeScreen.fxml");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
