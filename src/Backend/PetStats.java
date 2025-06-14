package Backend;

import java.time.Duration;
import java.time.LocalTime;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Author: ugne.stankeviciute@mif.stud.vu.lt
 * Project: Staying-alive
 *
 * ------------------------------------------------------------------------
 * Evaluates the wellness status of the pet based on activity data.
 * Contains logic to check if the pet is tired, hungry, thirsty, or restless.
 * Provides status data to be used in the frontend.
 */

public class PetStats {
    /**
     * Evaluates and returns the current status of the pet.
     * Each entry in the returned map indicates whether a specific condition (e.g., tired) is currently true.
     *
     * @param pet PetWellbeing object which content will be set
     * @return Map of status flags (e.g., "tired" → true)
     */
    public static Map<String, Boolean> checkStats(PetWellbeing pet) {
        Map<String, Boolean> stats = new LinkedHashMap<>();

        stats.put("tired", isTired(pet));
        stats.put("hungry", isHungry(pet));
        stats.put("thirsty", isThirsty(pet));
        stats.put("restless", isRestless(pet));

        return stats;
    }

    private static boolean isTired(PetWellbeing pet) {
        LocalTime fellAsleep = Tools.parseTime(pet.getSleep().getLastActivity());
        LocalTime wakeUp = Tools.parseTime(pet.getSleep().getWakeUpTime());
        LocalTime now = LocalTime.now();

        if (fellAsleep == wakeUp) return true;

        long sleepTime = Duration.between(fellAsleep, wakeUp).toHours();
        if (sleepTime < 0) sleepTime += 24;

        long awakeTime = Duration.between(wakeUp, now).toHours();
        if (awakeTime < 0) awakeTime += 24;

        return sleepTime < 7 || awakeTime >= 14;
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
        LocalTime wakeUp = Tools.parseTime(pet.getSleep().getWakeUpTime());
        LocalTime now = LocalTime.now();

        int mlDrunk = pet.getHydration().getTotalMl();

        long hoursAwake = Duration.between(wakeUp, now).toHours();
        if (hoursAwake < 0) hoursAwake += 24;
        int requiredMl = (int) (hoursAwake * 50);

        long hoursPassed = Duration.between(lastDrink, now).toHours();
        if (hoursPassed < 0) hoursPassed += 24;

        return hoursPassed >= 4 || mlDrunk < requiredMl;
    }

    private static boolean isRestless(PetWellbeing pet) {
        LocalTime lastExercise = Tools.parseTime(pet.getExercise().getLastExerciseTime());
        LocalTime now = LocalTime.now();

        long hoursPassed = Duration.between(lastExercise, now).toHours();
        if (hoursPassed < 0) hoursPassed += 24;

        return pet.getExercise().getLogsToday() == 0 || hoursPassed >= 5;
    }
}