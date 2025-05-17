package Frontend;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

import Backend.ButtonAction;
import Exceptions.DataNotSavedException;

/**
 * Author: adrija.ignataviciute@mf.stud.vu.lt
 * Project: Staying-alive
 * ---------------------------------------------------------------------------------
 * TaskButton represents a button that allows the user to manage tasks.
 * The user can add new tasks, view existing tasks or mark tasks as complete.
 */
public class TaskButton extends JButton {
    private final Color baseColor = new Color(195, 177, 225);    // pastel purple
    private final Color hoverColor = new Color(180, 165, 210);   // slightly darker
    private final Color pressColor = new Color(160, 150, 190);   // even darker

    /**
     * Constructs the TaskButton and adds an action listener that presents the user
     * with options to manage tasks. Also adds mouse listeners for pumpiness effect.
     */
    public TaskButton() {
        super("Tasks");
        setFont(new Font("SansSerif", Font.BOLD, 16));
        setBackground(baseColor);
        setOpaque(true);
        setBorder(BorderFactory.createBevelBorder(1));
        setFocusPainted(false);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setBackground(hoverColor);
                setBorder(BorderFactory.createBevelBorder(1, Color.WHITE, Color.GRAY));
                setBounds(getX() - 1, getY() - 1, getWidth() + 2, getHeight() + 2); // pop out effect
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setBackground(baseColor);
                setBorder(BorderFactory.createBevelBorder(1));
                setBounds(getX() + 1, getY() + 1, getWidth() - 2, getHeight() - 2); // reset size
            }

            @Override
            public void mousePressed(MouseEvent e) {
                setBackground(pressColor);
                setBounds(getX() + 1, getY() + 1, getWidth() - 2, getHeight() - 2); // press in effect
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (contains(e.getPoint())) {
                    setBackground(hoverColor);
                    setBounds(getX() - 1, getY() - 1, getWidth() + 2, getHeight() + 2); // back to pop out
                } else {
                    setBackground(baseColor);
                    setBorder(BorderFactory.createBevelBorder(1));
                    setBounds(getX() + 1, getY() + 1, getWidth() - 2, getHeight() - 2); // reset size
                }
            }
        });

        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] options = {"Add Task", "View Tasks", "Complete Task"};
                int choice = JOptionPane.showOptionDialog(
                        null,
                        "What do you want to do?",
                        "Tasks",
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        options,
                        options[0]
                );

                switch (choice) {
                    case 0:
                        addTask();
                        break;
                    case 1:
                        viewTasks();
                        break;
                    case 2:
                        completeTask();
                        break;
                    default:
                        break;
                }
            }
        });
    }

    private void addTask() {
        String task = JOptionPane.showInputDialog(null, "Enter a new task:");
        if (task != null && !task.trim().isEmpty()) {
            try {
                ButtonAction.updateTask(false, task.trim());
                JOptionPane.showMessageDialog(null, "Task added!");
            } catch (DataNotSavedException ex) {
                JOptionPane.showMessageDialog(null, "Error: saving data.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Task cannot be empty.");
        }
    }

    private void viewTasks() {
        try {
            List<String> tasks = ButtonAction.getTasks();
            if (tasks.isEmpty()) {
                JOptionPane.showMessageDialog(null, "No tasks found!");
            } else {
                StringBuilder sb = new StringBuilder("Current Tasks:\n");
                for (String task : tasks) {
                    sb.append("- ").append(task).append("\n");
                }
                JOptionPane.showMessageDialog(null, sb.toString());
            }
        } catch (DataNotSavedException ex) {
            JOptionPane.showMessageDialog(null, "Error: saving pet's data.");
        }
    }

    private void completeTask() {
        try {
            List<String> tasks = ButtonAction.getTasks();
            if (tasks.isEmpty()) {
                JOptionPane.showMessageDialog(null, "No tasks to complete!");
                return;
            }

            String taskToFinish = (String) JOptionPane.showInputDialog(
                    null,
                    "Select a task to mark as done:",
                    "Complete Task",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    tasks.toArray(),
                    tasks.get(0)
            );

            if (taskToFinish != null) {
                ButtonAction.updateTask(true, taskToFinish);
                JOptionPane.showMessageDialog(null, "Completed!");
            }

        } catch (DataNotSavedException ex) {
            JOptionPane.showMessageDialog(null, "Error: saving data.");
        }
    }

    private void showError(Exception ex) {
        JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
    }
}
