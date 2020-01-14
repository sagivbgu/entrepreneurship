package rateFit;

public class User {
    private Genre faveGenre;
    private double heartrate;

    public User(Genre favouriteGenre) {
        faveGenre = favouriteGenre;
        heartrate = 60.0;
    }

    public double getHeartrate() {
        return heartrate;
    }

    public void setHeartrate(int currentSongBpm) {
        heartrate = (heartrate + currentSongBpm) / 2;
    }
}
