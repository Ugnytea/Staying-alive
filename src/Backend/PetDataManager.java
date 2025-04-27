package Backend;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

public class PetDataManager {
    static final String PET_FILENAME = "PetsWellbeingTracker";

    //Gets properties (key=values) from data file and turns it into HashMap, then converts it to PetWellbeing object for easier usage in the program
    public static PetWellbeing readPetData() throws IOException {
        Map<String, String> petHM = new HashMap<>();
        Properties props = new Properties();

        try (FileInputStream in = new FileInputStream(PET_FILENAME)) {
            props.load(in);
        }

        for (String key : props.stringPropertyNames()) {
            petHM.put(key, props.getProperty(key));
        }

        return fromHashMap(petHM);
    }

    //Converts hashMap into PetWellbeing object
    private static PetWellbeing fromHashMap(Map<String, String> hm) {
        PetWellbeing pet = new PetWellbeing();

        pet.getSleep().setLastActivity(hm.getOrDefault("sleep.lastActivity", "00:00"));
        pet.getSleep().setWakeTime(hm.getOrDefault("sleep.wakeTime", "00:00"));
        pet.getMeals().setLastMealTime(hm.getOrDefault("meals.lastMealTime", "00:00"));
        pet.getHydration().setTotalMl(Integer.parseInt(hm.getOrDefault("hydration.totalMl", "0")));
        pet.getHydration().setLastDrinkTime(hm.getOrDefault("hydration.lastDrinkTime", "00:00"));
        pet.getExercise().setLogsToday(Integer.parseInt(hm.getOrDefault("exercise.logsToday", "0")));

        String tasks = hm.getOrDefault("tasks", "");
        if (!tasks.isEmpty()) {
            pet.setTasks(new ArrayList<>(List.of(tasks.split(","))));
        }

        return pet;
    }

    //Converts PetWellbeing object to HashMap and saves it in data file
    public static void savePetData(PetWellbeing pet) throws IOException {
        Properties props = new Properties();
        props.putAll(toHashMap(pet));

        try (FileOutputStream out = new FileOutputStream(PET_FILENAME)) {
            props.store(out, "Pet Wellbeing Data (Auto Saved)");
        }

    }

    //Converts PetWellbeing object into HashMap
    private static Map<String, String> toHashMap(PetWellbeing pet) {
        Map<String, String> hm = new HashMap<>();

        hm.put("sleep.lastActivity", pet.getSleep().getLastActivity());
        hm.put("sleep.wakeTime", pet.getSleep().getWakeTime());
        hm.put("meals.lastMealTime", pet.getMeals().getLastMealTime());
        hm.put("hydration.totalMl", String.valueOf(pet.getHydration().getTotalMl()));
        hm.put("hydration.lastDrinkTime", pet.getHydration().getLastDrinkTime());
        hm.put("exercise.logsToday", String.valueOf(pet.getExercise().getLogsToday()));
        hm.put("tasks", String.join(",", pet.getTasks()));

        return hm;
    }



}
