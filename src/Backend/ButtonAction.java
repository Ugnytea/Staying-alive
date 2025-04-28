package Backend;

import java.io.IOException;

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
    public static void updateSleep(String time) throws IOException {
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
    public static void updateNutrition(String time) throws IOException {
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
    public static void updateHydration(int ml,String time) throws IOException {
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
    public static void updateExercise() throws IOException {
        PetWellbeing pet = new PetWellbeing();
        prepToLogAction(pet);

        int totalExercise = 1 + pet.getExercise().getLogsToday();
        pet.getExercise().setLogsToday(totalExercise);

        PetDataManager.savePetData(pet);
    }

    /**
     * Updates to-do list
     *
     * @param finished Boolean of if the passed task is finished (to be deleted) or new (to be added)
     * @param task String of specific task to add to or remove from list
     * @throws IOException If an error occurs during file reading or writing to it
     */
    public static void updateTask(boolean finished, String task) throws IOException {
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
     * Gets all pet's statistics
     *
     * @throws IOException If an error occurs during file reading
     */
    public static void checkOnPet () throws IOException {
        PetWellbeing pet = new PetWellbeing();

        prepToLogAction(pet);

        //Gives back the statuses of: if hungry, thirsty, tired, restless.
    }

    private static void prepToLogAction (PetWellbeing pet) throws IOException {
        PetDataManager.readPetData(pet);
        Tools.checkIfNewDay(pet);
    }
}
