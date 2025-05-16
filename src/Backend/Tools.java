package Backend;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Collections;
import java.util.List;

/**
 * Author: ugne.stankeviciute@mif.stud.vu.lt
 * Project: Staying-alive
 *
 * ------------------------------------------------------------------------
 * Utility class for common helper functions used across the backend.
 * Provides methods for safely parsing time strings and resetting pet data for a new day.
 */
public class Tools {
    private static final DateTimeFormatter TIME_FORMAT = DateTimeFormatter.ofPattern("H:mm");

    /**
     * Parses a time string in the format "HH:mm" or "H:mm" into an object.
     * If the input string is invalid or cannot be parsed, it safely returns LocalTime.MIDNIGHT as a fallback.
     *
     * @param timeStr String of the time to parse
     * @return Parsed object, or LocalTime.MIDNIGHT if parsing fails
     */
    public static LocalTime parseTime(String timeStr) {
        try {
            return LocalTime.parse(timeStr, TIME_FORMAT);
        } catch (DateTimeParseException e) {
            return LocalTime.MIDNIGHT;
        }
    }

    /**
     * Checks if a new day has started since the last saved date.
     * If a new day is detected, resets relevant pet data.
     *
     * @param pet PetWellbeing object which content will be reset
     */
    public static void checkIfNewDay(PetWellbeing pet) {
        LocalDate now = LocalDate.now();

        String lastSavedDate = pet.getLastSavedDate();
        if (now.isAfter(LocalDate.parse(lastSavedDate))) {
            resetPetStats(pet);
            PetDataManager.savePetData(pet);
        }
    }

    private static void resetPetStats(PetWellbeing pet) {
        // Updates pet's lastSavedDate to current date
        pet.setLastSavedDate(LocalDate.now().toString());
        // Updates pet's lastSavedDate to current date
        pet.getSleep().setLastActivity(findLastActivity(pet));

        pet.getNutrition().setLastNutritionTime("00:00");
        pet.getHydration().setTotalMl(0);
        pet.getHydration().setLastDrinkTime("00:00");
        pet.getExercise().setLogsToday(0);
        pet.getExercise().setLastExerciseTime("00:00");
    }

    private static String findLastActivity(PetWellbeing pet) {
        LocalTime drink = parseTime(pet.getHydration().getLastDrinkTime());
        LocalTime meal = parseTime(pet.getNutrition().getLastNutritionTime());
        LocalTime exercise = parseTime(pet.getExercise().getLastExerciseTime());

        List<LocalTime> times = List.of(drink, meal, exercise);
        LocalTime latest = Collections.max(times);

        return latest.toString();
    }

}
