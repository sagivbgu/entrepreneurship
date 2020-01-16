package rateFit;

public class User {
    private Genre faveGenre;
    private double heartrate;

    public User() {
        heartrate = 60.0;
    }

    public void setGenre (Genre genre) {
        faveGenre = genre;
    }

    public Genre getGenre() {
        return faveGenre;
    }

    public int getHeartrate() {
        return (int) heartrate;
    }

    public void updateHeartrate(int currentSongBpm) {
        heartrate = (heartrate + currentSongBpm) / 2;
    }
}
