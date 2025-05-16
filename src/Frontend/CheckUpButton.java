package Frontend;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import Backend.ButtonAction;

/**
 * Author: adrija.ignataviciute@mf.stud.vu.lt
 * Project: Staying-alive
 * ----------------------------------------------------------------------------------
 * CheckUpButton is a custom JButton that, when clicked,
 * displays the current health and wellbeing status of the user's virtual pet.
 * ----------------------------------------------------------------------------------
 * It retrieves pet status data from the backend (via ButtonAction)
 * and shows a message dialog summarizing the pet's condition (e.g., tired, hungry).
 */


public class CheckUpButton extends JButton {
    /**
     * Constructs a CheckUpButton with the label "How is Your pet doing?".
     * Adds an action listener that fetches and displays the pet's status.
     */
    public CheckUpButton() {
        super("How is Your pet doing?");
        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Map<String, Boolean> stats = ButtonAction.checkOnPet();

                    StringBuilder status = new StringBuilder("Status:\n\n");

                    status.append(stats.getOrDefault("tired", false) ? "Pet is tired!\n" : "Pet is rested!\n");
                    status.append(stats.getOrDefault("hungry", false) ? "Pet is hungry!\n" : "Pet is well fed!\n");
                    status.append(stats.getOrDefault("thirsty", false) ? "Pet is thirsty!\n" : "Pet is hydrated!\n");
                    status.append(stats.getOrDefault("restless", false) ? "Pet is restless!\n" : "Pet got enough exercise!\n");

                    JOptionPane.showMessageDialog(null, status.toString());

                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Error checking pet status:\n" + ex.getMessage());
                }
            }
        });
    }
}
