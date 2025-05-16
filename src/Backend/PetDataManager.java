package Backend;

import Exceptions.DataNotSavedException;
import Exceptions.FileNotReadException;

import java.io.*;
import java.time.LocalDate;
import java.util.*;

/**
 * Author: ugne.stankeviciute@mif.stud.vu.lt
 * Project: Staying-alive
 *
 * ------------------------------------------------------------------------
 * This class handles reading from and writing to the pet's data file.
 * It manages all wellness activity logs for the current day,
 * including sleep, meals, hydration, exercise, and tasks.
 *
 * Data is loaded from a properties file and mapped into a PetWellbeing object.
 * Updates to user activities are saved back to the file.
 */

public class PetDataManager {
    static final String PET_FILENAME = "Staying-alive/src/Backend/PetsWellbeingTracker.properties";

    /**
     * Gets properties (key=values) from data file and turns it into HashMap
     *
     * @param pet PetWellbeing object which content will be set
     * @return PetWellbeing object converted from HashMap
     * @throws FileNotFoundException If an error occurs during file reading
     */
    public static PetWellbeing readPetData(PetWellbeing pet) {
        Map<String, String> petHM = new HashMap<>();
        Properties props = new Properties();

        try (FileInputStream in = new FileInputStream(PET_FILENAME)) {
            props.load(in);
        } catch (IOException e) {
            throw new FileNotReadException();
        }

        for (String key : props.stringPropertyNames()) {
            petHM.put(key, props.getProperty(key));
        }

        return fromHashMap(petHM, pet);
    }

    private static PetWellbeing fromHashMap(Map<String, String> hm, PetWellbeing pet) {
        pet.setLastSavedDate(hm.getOrDefault("date.lastSavedDate", "0000-00-00"));
        pet.getSleep().setLastActivity(hm.getOrDefault("sleep.lastActivity", "00:00"));
        pet.getSleep().setWakeUpTime(hm.getOrDefault("sleep.wakeUpTime", "00:00"));
        pet.getNutrition().setLastNutritionTime(hm.getOrDefault("nutrition.lastNutritionTime", "00:00"));
        pet.getHydration().setTotalMl(Integer.parseInt(hm.getOrDefault("hydration.totalMl", "0")));
        pet.getHydration().setLastDrinkTime(hm.getOrDefault("hydration.lastDrinkTime", "00:00"));
        pet.getExercise().setLogsToday(Integer.parseInt(hm.getOrDefault("exercise.logsToday", "0")));
        pet.getExercise().setLastExerciseTime(hm.getOrDefault("exercise.lastExerciseTime", "00:00"));

        String tasks = hm.getOrDefault("tasks", "");
        if (!tasks.isEmpty()) {
            pet.setTasks(new ArrayList<>(List.of(tasks.split(","))));
        }

        return pet;
    }

    /**
     * Saves PetWellbeing object converted to HashMap in data file
     *
     * @param pet PetWellbeing object which content will be saved
     * @throws DataNotSavedException If an error occurs during writing to file
     */
    public static void savePetData(PetWellbeing pet) {
        Properties props = new Properties();
        props.putAll(toHashMap(pet));

        List<String> orderedKeys = List.of(
                "date.lastSavedDate",
                "sleep.lastActivity",
                "sleep.wakeTime",
                "nutrition.lastNutritionTime",
                "hydration.totalMl",
                "hydration.lastDrinkTime",
                "exercise.logsToday",
                "exercise.lastExerciseTime",
                "tasks"
        );

        try (PrintWriter writer = new PrintWriter(new FileWriter(PET_FILENAME))) {
            writer.println("# Pet Wellbeing Data (Auto Saved)");
            for (String key : orderedKeys) {
                writer.println(key + "=" + props.getProperty(key));
            }
        } catch (IOException e) {
            throw new DataNotSavedException();
        }
    }

    private static Map<String, String> toHashMap(PetWellbeing pet) {
        // Updates pet's lastSavedDate to current date
        pet.setLastSavedDate(LocalDate.now().toString());

        Map<String, String> hm = new HashMap<>();

        hm.put("date.lastSavedDate", pet.getLastSavedDate());
        hm.put("sleep.lastActivity", pet.getSleep().getLastActivity());
        hm.put("sleep.wakeTime", pet.getSleep().getWakeUpTime());
        hm.put("nutrition.lastNutritionTime", pet.getNutrition().getLastNutritionTime());
        hm.put("hydration.totalMl", String.valueOf(pet.getHydration().getTotalMl()));
        hm.put("hydration.lastDrinkTime", pet.getHydration().getLastDrinkTime());
        hm.put("exercise.logsToday", String.valueOf(pet.getExercise().getLogsToday()));
        hm.put("exercise.lastExerciseTime", pet.getExercise().getLastExerciseTime());
        hm.put("tasks", String.join(",", pet.getTasks()));

        return hm;
    }
}
