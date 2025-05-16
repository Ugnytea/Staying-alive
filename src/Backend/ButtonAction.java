package Backend;

import Exceptions.DataNotSavedException;
import Exceptions.FileNotReadException;
import java.util.List;
import java.util.Map;

/**
 * Author: ugne.stankeviciute@mif.stud.vu.lt
 * Project: Staying-alive
 *
 * ------------------------------------------------------------------------
 * Connects the frontend and backend.
 * Triggers updates to a specific wellness stat or returns the pet's current statuses.
 */

public class ButtonAction {
    /**
     * Updates the time user woke up
     *
     * @param time String of time at which user woke up
     * @throws FileNotReadException If an error occurs during file reading
     * @throws DataNotSavedException If an error occurs while writing to file
     */
    public static void updateSleep(String time) {
        PetWellbeing pet = new PetWellbeing();
        prepToLogAction(pet);

        pet.getSleep().setWakeUpTime(time);

        PetDataManager.savePetData(pet);
    }

    /**
     * Updates the time user last ate
     *
     * @param time String of time at which user ate
     * @throws FileNotReadException If an error occurs during file reading
     * @throws DataNotSavedException If an error occurs while writing to file
     */
    public static void updateNutrition(String time) {
        PetWellbeing pet = new PetWellbeing();
        prepToLogAction(pet);

        pet.getNutrition().setLastNutritionTime(time);

        PetDataManager.savePetData(pet);
    }

    /**
     * Updates ml of drinks user had to drink in a day and last had a drink
     *
     * @param ml Integer of how much ml user had to drink
     * @param time String of time at which user had a drink
     * @throws FileNotReadException If an error occurs during file reading
     * @throws DataNotSavedException If an error occurs while writing to file
     */
    public static void updateHydration(int ml,String time) {
        PetWellbeing pet = new PetWellbeing();
        prepToLogAction(pet);

        int totalMl = ml+ pet.getHydration().getTotalMl();
        pet.getHydration().setTotalMl(totalMl);

        pet.getHydration().setLastDrinkTime(time);

        PetDataManager.savePetData(pet);
    }

    /**
     * Updates how many times user had exercised during the day
     *
     * @throws FileNotReadException If an error occurs during file reading
     * @throws DataNotSavedException If an error occurs while writing to file
     */
    public static void updateExercise(String time) {
        PetWellbeing pet = new PetWellbeing();
        prepToLogAction(pet);

        int totalExercise = 1 + pet.getExercise().getLogsToday();
        pet.getExercise().setLogsToday(totalExercise);

        pet.getExercise().setLastExerciseTime(time);

        PetDataManager.savePetData(pet);
    }

    /**
     * Updates to-do list
     *
     * @param finished Boolean of if the passed task is finished (to be deleted) or new (to be added)
     * @param task String of specific task to add to or remove from list
     * @throws FileNotReadException If an error occurs during file reading
     * @throws DataNotSavedException If an error occurs while writing to file
     */
    public static void updateTask(boolean finished, String task) {
        PetWellbeing pet = new PetWellbeing();
        prepToLogAction(pet);

        if (finished) {
            // Deletes finished task from list
            pet.getTasks().remove(task);
        } else {
            // Adds task to list if new (not finished)
            pet.getTasks().add(task);
        }

        PetDataManager.savePetData(pet);
    }

    /**
     * Gives to-do list
     *
     * @return A list of tasks
     * @throws FileNotReadException If an error occurs during file reading
     * @throws DataNotSavedException If an error occurs while writing to file
     */
    public static List<String> getTasks() {
        PetWellbeing pet = new PetWellbeing();
        prepToLogAction(pet);

        return pet.getTasks();
    }

    /**
     * Gets all pet's statistics in a list
     *
     * @return A list of pet's statistics in order of: if hungry, thirsty, tired, restless
     * @throws FileNotReadException If an error occurs during file reading
     */
    public static Map<String, Boolean> checkOnPet () {
        PetWellbeing pet = new PetWellbeing();

        prepToLogAction(pet);

        return PetStats.checkStats(pet);
    }

    private static void prepToLogAction (PetWellbeing pet) {
        PetDataManager.readPetData(pet);
        Tools.checkIfNewDay(pet);
    }
}
