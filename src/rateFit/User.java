package rateFit;

public class User {
    private Genre faveGenre;
    private double heartrate;

    public User(Genre genre) {
        faveGenre = genre;
        heartrate = 60.0;
    }

    public void setHeartrate(int currentSongBpm) {
        heartrate = (heartrate + currentSongBpm)/2;
    }
}
