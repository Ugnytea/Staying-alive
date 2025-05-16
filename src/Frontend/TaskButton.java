package Frontend;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

import Backend.ButtonAction;

/**
 * Author: adrija.ignataviciute@mf.stud.vu.lt
 * Project: Staying-alive
 * ---------------------------------------------------------------------------------
 * TaskButton represents a button that allows the user to manage tasks.
 * The user can add new tasks, view existing tasks or mark tasks as complete.
 */
public class TaskButton extends JButton {
    /**
     * Constructs the TaskButton and adds an action listener that presents the user
     * with options to manage tasks.
     */
    public TaskButton() {
        super("Tasks");

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
            } catch (IOException ex) {
                showError(ex);
            }
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
        } catch (IOException ex) {
            showError(ex);
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

        } catch (IOException ex) {
            showError(ex);
        }
    }

    private void showError(Exception ex) {
        JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
    }
}
