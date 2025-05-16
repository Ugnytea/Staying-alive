package Backend;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * This is the main back-end class that gets called by front-end
 *
 */

public class ButtonAction {
    /**
     * Updates the time user woke up
     *
     * @param time String of time at which user woke up
     * @throws IOException If an error occurs during file reading or writing to it
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
     * @throws IOException If an error occurs during file reading or writing to it
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
     * @throws IOException If an error occurs during file reading or writing to it
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
     * @throws IOException If an error occurs during file reading or writing to it
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
     * @throws IOException If an error occurs during file reading or writing to it
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
     * @throws IOException If an error occurs during file reading or writing to it
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
     * @throws IOException If an error occurs during file reading
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
