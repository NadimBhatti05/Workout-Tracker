package workout.tracker.gui;

import workout.tracker.model.Exercise;
import workout.tracker.model.Workout;
import workout.tracker.user.User;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.Vector;

public class MainFrame extends JFrame {
    private JList<JPanel> workouts;
    private User user;

    public MainFrame(User user) {
        this.user = user;
        setTitle("My Workouts");
        setLocationRelativeTo(null);
        setResizable(false);
        setSize(400, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        components();
        setVisible(true);
    }

    public void components() {
        this.setLayout(new BorderLayout());
        JPanel panel1 = new JPanel(new GridLayout(user.getWorkouts().size(), 1));
        JPanel panel2 = new JPanel(new BorderLayout());
        JButton button = new JButton("New Workout");
        workouts = new JList<>();
        for (int i = 0; i < user.getWorkouts().size(); i++) {
            panel1.add(createWorkoutPanel(user.getWorkouts().get(i)));
        }
        panel2.add(button, BorderLayout.EAST);
        JScrollPane scrollPane = new JScrollPane(panel1);
        this.add(scrollPane, BorderLayout.CENTER);
        this.add(panel2, BorderLayout.SOUTH);
    }

    public JPanel createWorkoutPanel(Workout workout) {
        JPanel panel1 = new JPanel(new BorderLayout());
        JPanel panel2 = new JPanel(new GridLayout(workout.getExercises().size(), 1));
        JLabel label = new JLabel(workout.getName() + " (" + workout.getDate() + ")");
        label.setFont(new Font("Courier", Font.BOLD, 16));
        for (int i = 0; i < workout.getExercises().size(); i++) {
            panel2.add(createExercisePanel(workout.getExercises().get(i)), BorderLayout.CENTER);
        }
        panel1.add(label, BorderLayout.NORTH);
        panel1.add(panel2, BorderLayout.CENTER);
        panel1.setBorder(new EmptyBorder(10, 10 , 20, 10));
        return panel1;
    }

    public JPanel createExercisePanel(Exercise exercise) {
        JPanel panel1 = new JPanel(new BorderLayout());
        JPanel panel2 = new JPanel(new BorderLayout());
        JLabel label = new JLabel(exercise.getName());
        JTable table;
        Object[] columnNames = {"Set", "Weight", "Reps"};
        Object[][] rowsData = new Object[exercise.getReps().length][3];
        for (int i = 0; i < exercise.getReps().length; i++) {
            rowsData[i][0] = i+1;
            rowsData[i][1] = exercise.getWeights()[i];
            rowsData[i][2] = exercise.getReps()[i];
        }
        table = new JTable(rowsData, columnNames);
        table.setDefaultEditor(Object.class, null);
        panel1.add(table.getTableHeader(), BorderLayout.NORTH);
        panel1.add(table, BorderLayout.CENTER);
        panel2.add(panel1, BorderLayout.CENTER);
        panel2.add(label, BorderLayout.NORTH);
        panel2.setBorder(new EmptyBorder(5, 0, 10, 0));
        System.out.println(table.getRowCount());
        return panel2;
    }
}
