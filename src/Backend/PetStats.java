package Backend;

import java.time.Duration;
import java.time.LocalTime;
import java.util.LinkedHashMap;
import java.util.Map;

public class PetStats {
    public static Map<String, Boolean> checkStats(PetWellbeing pet) {
        Map<String, Boolean> stats = new LinkedHashMap<>();

        stats.put("tired", isTired(pet));
        stats.put("hungry", isHungry(pet));
        stats.put("thirsty", isThirsty(pet));
        stats.put("restless", isRestless(pet));

        return stats;
    }

    private static boolean isTired(PetWellbeing pet) {
        String lastActivity = pet.getSleep().getLastActivity();
        String wakeUpTime = pet.getSleep().getWakeUpTime();

        LocalTime fellAsleep = Tools.parseTime(lastActivity);
        LocalTime wakeUp = Tools.parseTime(wakeUpTime);
        LocalTime now = LocalTime.now();

        long sleepTime = Duration.between(fellAsleep, wakeUp).toHours();
        if (sleepTime < 0) sleepTime += 24;

        long awakeTime = Duration.between(wakeUp, now).toHours();
        if (awakeTime < 0) awakeTime += 24;

        return sleepTime < 7 && awakeTime >= 14;
    }

    private static boolean isHungry(PetWellbeing pet) {
        LocalTime lastMeal = Tools.parseTime(pet.getNutrition().getLastNutritionTime());
        LocalTime now = LocalTime.now();

        long hoursPassed = Duration.between(lastMeal, now).toHours();
        if (hoursPassed < 0) hoursPassed += 24;

        return hoursPassed >= 5;
    }

    private static boolean isThirsty(PetWellbeing pet) {
        LocalTime lastDrink = Tools.parseTime(pet.getHydration().getLastDrinkTime());
        LocalTime now = LocalTime.now();

        int ml = pet.getHydration().getTotalMl();

        long hoursPassed = Duration.between(lastDrink, now).toHours();
        if (hoursPassed < 0) hoursPassed += 24;

        return hoursPassed >= 4 || ml == 0;
    }

    private static boolean isRestless(PetWellbeing pet) {
        LocalTime lastExercise = Tools.parseTime(pet.getExercise().getLastExerciseTime());
        LocalTime now = LocalTime.now();

        long hoursPassed = Duration.between(lastExercise, now).toHours();
        if (hoursPassed < 0) hoursPassed += 24;

        return pet.getExercise().getLogsToday() == 0 || hoursPassed >= 5;
    }
}