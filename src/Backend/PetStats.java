package Backend;

import java.util.ArrayList;
import java.util.List;

public class PetStats {
    public static List<Boolean> checkStats(PetWellbeing pet) {
        List<Boolean> stats = new ArrayList<>();

        stats.add(tired(pet));
        stats.add(hungry(pet));
        stats.add(thirsty(pet));
        stats.add(restless(pet));

        return stats;
    }

    private static boolean tired (PetWellbeing pet) {
        // Check if 7h have passed
        //BAndom kazka cia prihimicinti
        return false;
    }

    private static boolean hungry (PetWellbeing pet) {
        // Check if eaten before 12h, 15h, 20h

        return false;
    }

    private static boolean thirsty(PetWellbeing pet) {
        // Check if had something to drink every 8h

        return false;
    }

    private static boolean restless (PetWellbeing pet) {
        // Check if had something to drink every 8h

        return false;
    }


}