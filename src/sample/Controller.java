package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Controller {
    @FXML private Text actiontarget;
    public Stage thisStage;

    public Controller(Stage scene){
        thisStage = scene;
    }

    @FXML
    protected void handleSubmitButtonAction(ActionEvent event) {
        actiontarget.setText("Let's go!");
    }

    public void setStage (Stage stage){
        thisStage = stage;
    }

    public void showStage(String fxml){
        thisStage.setTitle("Title");
        thisStage.show();
    }

}
