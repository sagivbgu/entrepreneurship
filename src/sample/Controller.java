package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.text.Text;
import rateFit.Genre;
import rateFit.User;

public class Controller {
    @FXML private Text actiontarget;
    private ScreenController screenController = ScreenController.getInstance();
    private User user;

    public void setUser (User user) {
        this.user = user;
    }

    @FXML
    protected void handleHomeButtonAction(ActionEvent event) {
        screenController.activate("genreScreen");
    }

    @FXML
    protected void handlePopButtonAction(ActionEvent event) {
        user.setGenre(Genre.POP);
        screenController.activate("typeScreen");
    }

    @FXML
    protected void handleRockButtonAction(ActionEvent event) {
        user.setGenre(Genre.ROCK);
        screenController.activate("typeScreen");
    }
}
