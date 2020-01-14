package rateFit;

import java.util.ArrayList;

public class ExerciseType {
    private String name;
    ArrayList<int[]> secondsToDesiredHeartrate;

    public ExerciseType(String name, ArrayList<int[]> secondsToDesiredHeartrate) {
        this.name = name;
        this.secondsToDesiredHeartrate = secondsToDesiredHeartrate;
    }

    public String getName() {
        return name;
    }

    public int getDesiredHeartrate(long timeSinceStart) {
        int i = 0;
        while (i < secondsToDesiredHeartrate.size() && secondsToDesiredHeartrate.get(i)[0] < timeSinceStart) {
            i++;
        }

        return secondsToDesiredHeartrate.get(i - 1)[1];
    }
}