package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class Main extends Application {
    private Parent root;
    private Controller controller;

    @Override
    public void start(Stage primaryStage) throws Exception{
//        Stage stage;
//        root = FXMLLoader.load(getClass().getResource("sample.fxml"));
//
//        Scene scene = new Scene(root, 300, 275);
//        primaryStage.setTitle("RateFit");
//        primaryStage.setScene(scene);
//        controller = new Controller(primaryStage);
//        primaryStage.show();
//        stage = primaryStage;
        Label welcomeMsg = new Label("Hello Shlomi, welcome to RateFit");
        Button btn = new Button("Start a workout");
        GridPane root = new GridPane();
        Scene scene = new Scene(root,300,300);
        root.addRow(0, welcomeMsg);
        root.addRow(1, btn);
        primaryStage.setScene(scene);
        primaryStage.setTitle("RateFit");
        primaryStage.show();

        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {
                // TODO Auto-generated method stub

            }
        } );
    }


    public static void main(String[] args) {
        launch(args);
    }
}
