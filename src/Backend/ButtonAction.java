package Backend;

import java.io.IOException;

/**
 * This is the main back-end class, that gets called by front-end depending on different buttons user presses
 *
 */

enum UpdateAction {
    SLEEP,
    NUTRITION,
    HYDRATION,
    EXERCISE,
    TASKS;
}

public class ButtonAction {
    // Function that receives a button type from front end and provides needed info
    public static void handleButton(String buttonType) throws IOException {


        if (action.isPresent()) {
            log_action(buttonType);
        } else if (buttonType.equalsIgnoreCase("CHECKUP")) {
            checkOnPet();
        } else {
            throw new FileNotReadException("Invalid button pressed: " + buttonType);
        }
    }



    //When user want to input an action
    private static void log_action (String action) throws IOException {
        PetWellbeing pet = new PetWellbeing();
        PetDataManager.savePetData(pet);

        PetDataManager.savePetData(pet);

    }

    //When user want to check-up on their pet
    private static void checkOnPet () throws IOException {
        PetWellbeing pet = new PetWellbeing();
        PetDataManager.savePetData(pet);

    }

}
