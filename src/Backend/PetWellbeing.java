package Backend;

import java.util.ArrayList;
import java.util.List;

public class PetWellbeing {

    private String lastSavedDate = "0000-00-00";
    private Sleep sleep = new Sleep();
    private Meals meals = new Meals();
    private Hydration hydration = new Hydration();
    private Exercise exercise = new Exercise();
    private List<String> tasks = new ArrayList<>();

    //Getters and Setters


    public String getLastSavedDate() {
        return lastSavedDate;
    }

    public void setLastSavedDate(String lastSavedDate) {
        this.lastSavedDate = lastSavedDate;
    }

    public Sleep getSleep() {
        return sleep;
    }

    public void setSleep(Sleep sleep) {
        this.sleep = sleep;
    }

    public Meals getMeals() {
        return meals;
    }

    public void setMeals(Meals meals) {
        this.meals = meals;
    }

    public Hydration getHydration() {
        return hydration;
    }

    public void setHydration(Hydration hydration) {
        this.hydration = hydration;
    }

    public Exercise getExercise() {
        return exercise;
    }

    public void setExercise(Exercise exercise) {
        this.exercise = exercise;
    }

    public List<String> getTasks() {
        return tasks;
    }

    public void setTasks(List<String> tasks) {
        this.tasks = tasks;
    }

    //=== Inner Classes ===

    public static class Sleep {
        private String lastActivity = "00:00";
        private String wakeTime = "00:00";

        public String getLastActivity() {
            return lastActivity;
        }

        public void setLastActivity(String lastSleepTime) {
            this.lastActivity = lastSleepTime;
        }

        public String getWakeTime() {
            return wakeTime;
        }

        public void setWakeTime(String wakeTime) {
            this.wakeTime = wakeTime;
        }
    }

    public static class Meals {
        private String lastMealTime = "00:00";

        public String getLastMealTime() {
            return lastMealTime;
        }

        public void setLastMealTime(String lastMealTime) {
            this.lastMealTime = lastMealTime;
        }
    }

    public static class Hydration {
        private int totalMl = 0;
        private String lastDrinkTime = "00:00";

        public int getTotalMl() {
            return totalMl;
        }

        public void setTotalMl(int totalMl) {
            this.totalMl = totalMl;
        }

        public String getLastDrinkTime() {
            return lastDrinkTime;
        }

        public void setLastDrinkTime(String lastDrinkTime) {
            this.lastDrinkTime = lastDrinkTime;
        }
    }

    public static class Exercise {
        private int logsToday = 0;

        public int getLogsToday() {
            return logsToday;
        }

        public void setLogsToday(int logsToday) {
            this.logsToday = logsToday;
        }
    }
}
