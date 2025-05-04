package Backend;

import java.time.LocalDate;

public class Tools {
    /**
     * Checks if it's a new day and resets data if necessary
     */


    public static void checkIfNewDay(PetWellbeing pet) {
        LocalDate now = LocalDate.now();

        String lastSavedDate = pet.getLastSavedDate();

        if (now.isBefore(LocalDate.parse(lastSavedDate))) {
            resetPetStats(pet);
        }
    }

    private static void resetPetStats(PetWellbeing pet) {
        // Updates pet's lastSavedDate to current date
        pet.setLastSavedDate(LocalDate.now().toString());

        pet.getNutrition().setLastNutritionTime("00:00");
        pet.getHydration().setTotalMl(0);
        pet.getHydration().setLastDrinkTime("00:00");
        pet.getExercise().setLogsToday(0);
        pet.getExercise().setLastExerciseTime("00:00");
    }
}
