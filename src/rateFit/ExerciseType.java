package rateFit;

public class ExerciseType {
    private String name;
    int[][] secondsToDesiredHeartrate;

    public ExerciseType(String name, int[][] secondsToDesiredHeartrate) {
        this.name = name;
        this.secondsToDesiredHeartrate = secondsToDesiredHeartrate;
    }

    public String getName() {
        return name;
    }

    public int[][] getSecondsToDesiredHeartrate() {
        return secondsToDesiredHeartrate;
    }
}