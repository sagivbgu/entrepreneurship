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
        while (i < secondsToDesiredHeartrate.size() && secondsToDesiredHeartrate.get(i)[0] <= timeSinceStart) {
            i++;
        }

        return secondsToDesiredHeartrate.get(i - 1)[1];
    }

    public static ExerciseType Endurance5min() {
        var secondsToDesiredHeartrate = new ArrayList<int[]>();
        secondsToDesiredHeartrate.add(new int[]{0, 80});
        secondsToDesiredHeartrate.add(new int[]{10, 100});
        secondsToDesiredHeartrate.add(new int[]{30, 170});
        secondsToDesiredHeartrate.add(new int[]{60 + 10, 120});
        secondsToDesiredHeartrate.add(new int[]{60 * 2 + 15, 170});
        secondsToDesiredHeartrate.add(new int[]{60 * 3, 120});
        secondsToDesiredHeartrate.add(new int[]{60 * 3 + 30, 170});
        secondsToDesiredHeartrate.add(new int[]{60 * 4, 120});
        secondsToDesiredHeartrate.add(new int[]{60 * 4 + 30, 80});
        return new ExerciseType("Endurance 5 minutes", secondsToDesiredHeartrate);
    }

    public static ExerciseType FatLoss5min() {
        var secondsToDesiredHeartrate = new ArrayList<int[]>();
        secondsToDesiredHeartrate.add(new int[]{0, 80});
        secondsToDesiredHeartrate.add(new int[]{10, 100});
        secondsToDesiredHeartrate.add(new int[]{30, 120});
        secondsToDesiredHeartrate.add(new int[]{60, 140});
        secondsToDesiredHeartrate.add(new int[]{60 * 4, 120});
        secondsToDesiredHeartrate.add(new int[]{60 * 4 + 30, 80});
        return new ExerciseType("Fat loss 5 minutes", secondsToDesiredHeartrate);
    }

    public static ExerciseType Endurance10min() {
        var secondsToDesiredHeartrate = new ArrayList<int[]>();
        secondsToDesiredHeartrate.add(new int[]{0, 80});
        secondsToDesiredHeartrate.add(new int[]{10, 100});
        secondsToDesiredHeartrate.add(new int[]{30, 170});
        secondsToDesiredHeartrate.add(new int[]{60 + 10, 120});
        secondsToDesiredHeartrate.add(new int[]{60 * 2 + 15, 170});
        for (int s = 3; s <= 9; s++) {
            secondsToDesiredHeartrate.add(new int[]{60 * s, s % 2 == 1 ? 120 : 170});
        }
        secondsToDesiredHeartrate.add(new int[]{60 * 9 + 30, 80});
        return new ExerciseType("Endurance 10 minutes", secondsToDesiredHeartrate);
    }

    public static ExerciseType FatLoss10min() {
        var secondsToDesiredHeartrate = new ArrayList<int[]>();
        secondsToDesiredHeartrate.add(new int[]{0, 80});
        secondsToDesiredHeartrate.add(new int[]{10, 100});
        secondsToDesiredHeartrate.add(new int[]{30, 120});
        secondsToDesiredHeartrate.add(new int[]{60, 140});
        secondsToDesiredHeartrate.add(new int[]{60 * 9, 120});
        secondsToDesiredHeartrate.add(new int[]{60 * 9 + 30, 80});
        return new ExerciseType("Fat loss 10 minutes", secondsToDesiredHeartrate);
    }
}