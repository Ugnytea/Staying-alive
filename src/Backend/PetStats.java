package Backend;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class PetStats {
    final String PetFilename = "PetsWellbeingTracker";

    public static void checkStats() {
        PetWellbeing pet = new PetWellbeing();

    }

    private static void getHashMap() {
        
    }

    //Checks if it's a new day
    private static void checkTime(PetWellbeing pet) {
        LocalTime now = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");

        String lastActivityStr = pet.getSleep().getLastActivity();
        LocalTime lastActivityTime = LocalTime.parse(lastActivityStr, formatter);

        if (now.isBefore(lastActivityTime)) {
            resetPetStats(pet);
        }
    }

    //Sets pets statistics to for a new day
    private static void resetPetStats(PetWellbeing pet) {
        pet.getMeals().setLastMealTime("00:00");
        pet.getHydration().setTotalMl(0);
        pet.getHydration().setLastDrinkTime("00:00");
        pet.getExercise().setLogsToday(0);
        pet.getTasks().clear();

    }
}