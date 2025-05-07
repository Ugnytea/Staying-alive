package Backend;

import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.LinkedHashMap;
import java.util.Map;

public class PetStats {
    public static Map<String, Boolean> checkStats(PetWellbeing pet) {
        Map<String, Boolean> stats = new LinkedHashMap<>();

        stats.put("tired", tired(pet));
        stats.put("hungry", hungry(pet));
        stats.put("thirsty", thirsty(pet));
        stats.put("restless", restless(pet));

        return stats;
    }

    private static boolean tired (PetWellbeing pet) {
        String lastActivity = pet.getSleep().getLastActivity();
        String wakeUpTime = pet.getSleep().getWakeUpTime();
        LocalTime fellAsleep = parseTime(lastActivity);
        LocalTime wakeUp = parseTime(wakeUpTime);

        long hoursPassed = Duration.between(fellAsleep, wakeUp).toHours();
        if (hoursPassed < 0) hoursPassed += 24;

        return hoursPassed >= 7;
    }

    private static boolean hungry (PetWellbeing pet) {
        LocalTime lastMeal = parseTime(pet.getNutrition().getLastNutritionTime());
        LocalTime now = LocalTime.now();

        long hoursPassed = Duration.between(lastMeal, now).toHours();
        if (hoursPassed < 0) hoursPassed += 24;

        return hoursPassed >= 5;
    }

    private static boolean thirsty(PetWellbeing pet) {
        LocalTime lastDrink = parseTime(pet.getHydration().getLastDrinkTime());
        LocalTime now = LocalTime.now();

        long hoursPassed = Duration.between(lastDrink, now).toHours();
        if (hoursPassed < 0) hoursPassed += 24;

        return hoursPassed >= 8;
    }

    private static boolean restless(PetWellbeing pet) {
        LocalTime lastExercise = parseTime(pet.getExercise().getLastExerciseTime());
        LocalTime now = LocalTime.now();

        long hoursPassed = Duration.between(lastExercise, now).toHours();
        if (hoursPassed < 0) hoursPassed += 24;

        return pet.getExercise().getLogsToday() == 0 || hoursPassed >= 5;
    }

    private static LocalTime parseTime(String timeStr) {
        try {
            return LocalTime.parse(timeStr);
        } catch (DateTimeParseException e) {
            return LocalTime.MIDNIGHT;
        }
    }

}