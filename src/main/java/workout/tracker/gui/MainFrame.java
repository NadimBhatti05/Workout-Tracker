package workout.tracker.gui;

import workout.tracker.model.Exercise;
import workout.tracker.model.Workout;
import workout.tracker.user.User;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class MainFrame extends JFrame {
    private JList<JPanel> workouts;
    private User user;

    public MainFrame(User user) {
        this.user = user;
        setTitle("My Workouts");
        setLocationRelativeTo(null);
        setResizable(false);
        setSize(400, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        components();
        setVisible(true);
    }

    public void components() {
        this.setLayout(new BorderLayout());
        JPanel panel1 = new JPanel(new FlowLayout());
        JPanel panel2 = new JPanel(new BorderLayout());
        JButton button = new JButton("New Workout");
        this.workouts = new JList<>();
        int v = 0;
        for (int i = 0; i < user.getWorkouts().size(); i++) {
            panel1.add(createWorkoutPanel(user.getWorkouts().get(i)));
            v += user.getWorkouts().get(i).getExercises().size()*120;
        }
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new InsertWorkout();
            }
        });
        panel2.add(button, BorderLayout.EAST);
        panel1.setPreferredSize(new Dimension(400, v));
        JScrollPane scrollPane = new JScrollPane(panel1);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        this.add(scrollPane, BorderLayout.CENTER);
        this.add(panel2, BorderLayout.SOUTH);
    }

    public JPanel createWorkoutPanel(Workout workout) {
        JPanel panel1 = new JPanel(new BorderLayout());
        JPanel panel2 = new JPanel(new FlowLayout());
        JLabel label = new JLabel(workout.getName() + " (" + workout.getDate() + ")");
        label.setFont(new Font("Courier", Font.BOLD, 16));
        for (int i = 0; i < workout.getExercises().size(); i++) {
            panel2.add(createExercisePanel(workout.getExercises().get(i)));
        }
        panel1.add(label, BorderLayout.NORTH);
        panel1.add(panel2, BorderLayout.CENTER);
        panel1.setBorder(new EmptyBorder(10, 10 , 10, 10));
        panel1.setPreferredSize(new Dimension(400, workout.getExercises().size()*120));
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
        table.setCellSelectionEnabled(false);
        table.getTableHeader().setResizingAllowed(false);
        table.setPreferredSize(new Dimension(355, exercise.getReps().length*16));
        panel1.add(table.getTableHeader(), BorderLayout.NORTH);
        panel1.add(table, BorderLayout.CENTER);
        panel2.add(panel1, BorderLayout.CENTER);
        panel2.add(label, BorderLayout.NORTH);
        panel2.setBorder(new EmptyBorder(5, 0, 10, 25));
        return panel2;
    }
}
class InsertWorkout extends JDialog implements ActionListener{

    private JTable table;
    private JTextField title;
    private JLabel titleLable;
    private JButton submit;
    private JPanel panel1;
    private JPanel panel2;
    private JPanel panel3;
    private JPanel panel4;
    private JPanel panel5;
    private JPanel panel6;

    public InsertWorkout(){
        setTitle("New Workout");
        setLocationRelativeTo(null);
        setResizable(false);
        //setSize(300, 400);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        components();
        pack();
        setVisible(true);
    }

    public void components(){
        setLayout(new BorderLayout());
        title = new JTextField();
        titleLable = new JLabel("Title: ");
        submit = new JButton("Submit");
        panel1 = new JPanel(new GridLayout(1, 2));
        panel2 = new JPanel(new BorderLayout());
        panel1.add(titleLable);
        panel1.add(title);
        panel2.add(submit, BorderLayout.EAST);
        this.add(panel1, BorderLayout.NORTH);
        this.add(panel2, BorderLayout.SOUTH);
        /*
        String [] colHeadings = {"Set", "Weight", "Reps"};
        int numRows = 5 ;
        DefaultTableModel model = new DefaultTableModel(numRows, colHeadings.length);
        model.setColumnIdentifiers (colHeadings);
        table = new JTable (model);
         */
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
