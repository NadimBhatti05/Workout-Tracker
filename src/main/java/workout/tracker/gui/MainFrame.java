package workout.tracker.gui;

import workout.tracker.data.DataHandler;
import workout.tracker.model.Exercise;
import workout.tracker.model.Workout;
import workout.tracker.user.User;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.awt.print.PrinterException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.UUID;
import java.util.Vector;

public class MainFrame extends JFrame {
    private JList<JPanel> workouts;
    private User user;
    private JPanel panel1;
    private JPanel panel2;
    private JPanel panel3;
    private JPanel panel4;
    private JButton button1;
    private JButton button2;
    private JButton button3;

    public MainFrame(User user) {
        this.user = user;
        setTitle("My Workouts");
        setResizable(false);
        setSize(400, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        components();
        setVisible(true);
    }

    public void components() {
        this.setLayout(new BorderLayout());
        panel1 = new JPanel();
        panel1.setLayout(new BoxLayout(panel1,  BoxLayout.Y_AXIS));
        panel2 = new JPanel(new BorderLayout());
        button1 = new JButton("New Workout");
        button2 = new JButton("Reload");
        this.workouts = new JList<>();
        for (int i = 0; i < user.getWorkouts().size(); i++) {
            panel1.add(createWorkoutPanel(user.getWorkouts().get(i)));
        }
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new InsertWorkout(user);
            }
        });
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MainFrame(user);
                dispose();
            }
        });
        panel2.add(button1, BorderLayout.EAST);
        panel2.add(button2, BorderLayout.WEST);
        JScrollPane scrollPane = new JScrollPane(panel1);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        this.add(scrollPane, BorderLayout.CENTER);
        this.add(panel2, BorderLayout.SOUTH);
    }

    public JPanel createWorkoutPanel(Workout workout) {
        JPanel panel1 = new JPanel(new BorderLayout());
        JPanel panel2 = new JPanel(new FlowLayout());
        JPanel panel3 = new JPanel(new GridLayout(1, 2));
        JPanel panel4 = new JPanel(new BorderLayout());
        JButton delete = new JButton("Delete");
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new DeleteConfirmation(workout, user);
                dispose();
            }
        });
        JLabel label = new JLabel(workout.getName() + " (" + workout.getDate() + ")");
        panel3.add(label);
        panel4.add(delete, BorderLayout.EAST);
        panel4.setBorder(new EmptyBorder(0, 0, 0, 30));
        panel3.add(panel4);
        label.setFont(new Font("Courier", Font.BOLD, 16));
        for (int i = 0; i < workout.getExercises().size(); i++) {
            panel2.add(createExercisePanel(workout.getExercises().get(i)));
        }
        panel1.add(panel3, BorderLayout.NORTH);
        panel1.add(panel2, BorderLayout.CENTER);
        panel1.setBorder(new EmptyBorder(10, 10 , 10, 10));
        panel1.setPreferredSize(new Dimension(400, workout.getExercises().size()*120 + 20));
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

    private Vector<JTextField> names;
    private Vector<JTable> tables;
    private JTextField title;
    private JLabel titleLable;
    private JButton submit;
    private JTextField amount;
    private JLabel amountLable;
    private JButton generate;
    private JPanel panel1;
    private JPanel panel2;
    private JPanel panel3;
    private JPanel panel4;
    private JPanel panel5;
    private User user;

    public InsertWorkout(User user){
        this.user = user;
        setTitle("New Workout");
        setResizable(false);
        setSize(400, 500);
        setLocation(500, 0);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        components();
        setVisible(true);
    }

    public void components(){
        names = new Vector<>();
        tables = new Vector<>();
        setLayout(new BorderLayout());
        title = new JTextField();
        titleLable = new JLabel("Title: ");
        submit = new JButton("Submit");
        submit.setEnabled(false);
        amount = new JTextField();
        amountLable = new JLabel("Amount of Exercises: ");
        generate = new JButton("Generate");
        generate.addActionListener(this);
        panel1 = new JPanel(new GridLayout(2, 2));
        panel2 = new JPanel(new BorderLayout());
        panel3 = new JPanel(new BorderLayout());
        panel4 = new JPanel(new BorderLayout());
        panel5 = new JPanel();
        panel5.setLayout(new BoxLayout(panel5, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(panel5);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        panel1.add(titleLable);
        panel1.add(title);
        panel1.add(amountLable);
        panel1.add(amount);
        panel2.add(submit, BorderLayout.EAST);
        panel3.add(generate, BorderLayout.EAST);
        panel1.setBorder(new EmptyBorder(0, 0, 5, 0));
        panel4.add(panel1, BorderLayout.CENTER);
        panel4.add(panel3, BorderLayout.SOUTH);
        panel4.setBorder(new EmptyBorder(10, 10, 10, 10));
        this.add(panel4, BorderLayout.NORTH);
        this.add(scrollPane, BorderLayout.CENTER);
        this.add(panel2, BorderLayout.SOUTH);
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Workout workout = new Workout();
                Vector<Exercise> exercises = new Vector<>();
                for (int i = 0; i < tables.size(); i++) {
                    Exercise exercise = new Exercise();
                    double[] weights = new double[tables.get(i).getRowCount()];
                    int [] reps = new int[tables.get(i).getRowCount()];
                    for (int j = 0; j < tables.get(i).getRowCount(); j++) {
                        weights[j] = Double.parseDouble(String.valueOf(tables.get(i).getModel().getValueAt(j, 1)));
                        reps[j] = Integer.parseInt(String.valueOf(tables.get(i).getModel().getValueAt(j, 2)));
                    }
                    exercise.setName(names.get(i).getText());
                    exercise.setExerciseUUID(UUID.randomUUID().toString());
                    exercise.setReps(reps);
                    exercise.setWeights(weights);
                    exercises.add(exercise);
                    DataHandler.insertExercise(exercise);
                }
                LocalDate today = LocalDate.now();
                workout.setDate(today.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT)));
                workout.setName(title.getText());
                workout.setExercises(exercises);
                workout.setUuid(UUID.randomUUID().toString());
                DataHandler.insertWorkout(user, workout);
                dispose();
            }
        });
    }

    public JPanel createExercisePanel(){
        JPanel tablePanel = new JPanel(new BorderLayout());
        JPanel tableHeadingPanel = new JPanel(new BorderLayout());
        JPanel buttonPanel = new JPanel(new BorderLayout());
        JTextField titleField = new JTextField("Exercise name");
        titleField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                titleField.setText("");
            }

            @Override
            public void focusLost(FocusEvent e) {
                if(titleField.getText().equals("")){
                    titleField.setText("Exercise name");
                }
            }
        });
        names.add(titleField);
        JButton button = new JButton("Add Set");
        tablePanel.add(button, BorderLayout.SOUTH);
        JTable table;
        String [] colHeadings = {"Set", "Weight", "Reps"};
        int numRows = 0;
        DefaultTableModel model = new DefaultTableModel(numRows, colHeadings.length);
        model.setColumnIdentifiers (colHeadings);
        table = new JTable (model);
        table.putClientProperty("terminateEditOnFocusLost", true);
        tableHeadingPanel.add(table, BorderLayout.CENTER);
        tableHeadingPanel.add(table.getTableHeader(), BorderLayout.NORTH);
        tablePanel.add(tableHeadingPanel, BorderLayout.CENTER);
        tablePanel.add(titleField, BorderLayout.NORTH);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.addRow(
                    new Object[]{
                        table.getRowCount()+1,
                        "",
                        ""
                    }
                );
            }
        });
        tablePanel.setBorder(new EmptyBorder(15, 10, 15, 10));
        tables.add(table);
        return tablePanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Component[] components = panel1.getComponents();
        String titleValue = title.getText();
        String amountValue = amount.getText();
        if(!isNumeric(amount.getText())){
            JOptionPane.showMessageDialog(
                null,
                "Amount of Exercises is not an integer",
                "Error",
                JOptionPane.ERROR_MESSAGE
            );
            generate.setEnabled(true);
        }else if(title.getText().equals("")){
            JOptionPane.showMessageDialog(
                null,
                "Title can't be empty",
                "Error",
                JOptionPane.ERROR_MESSAGE
            );
        }
        else{
            submit.setEnabled(true);
            for (int i = 0; i < Integer.parseInt(amount.getText()); i++) {
                panel5.add(createExercisePanel());
            }
            generate.setEnabled(false);

            for (Component c : components){
                panel1.remove(c);
            }
            panel1.add(titleLable);
            panel1.add(new JLabel(titleValue));
            panel1.add(amountLable);
            panel1.add(new JLabel(amountValue));
        }
        panel1.revalidate();
        panel5.revalidate();
        validate();
    }

    public boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            int i = Integer.parseInt(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}

class DeleteConfirmation extends JFrame{

    private User user;
    private Workout workout;
    private JLabel label;
    private JPanel panel1;
    private JPanel panel2;
    private JPanel panel3;
    private JPanel panel4;
    private JButton cancel;
    private JButton delete;


    public DeleteConfirmation(Workout workout, User user){
        this.user = user;
        this.workout = workout;
        setTitle("Warning");
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        components();
        pack();
        setVisible(true);
    }

    public void components(){
        setLayout(new BorderLayout());
        label = new JLabel("Would you like to delete this Workout?");
        panel1 = new JPanel(new GridLayout(1, 2));
        panel2 = new JPanel(new BorderLayout());
        panel3 = new JPanel(new BorderLayout());
        panel4 = new JPanel(new BorderLayout());
        cancel = new JButton("Cancel");
        delete = new JButton("Delete");
        panel4.add(cancel, BorderLayout.WEST);
        panel1.add(panel4);
        panel3.add(delete, BorderLayout.EAST);
        panel1.add(panel3);
        panel2.add(label, BorderLayout.CENTER);
        panel2.setBorder(new EmptyBorder(30, 30, 30, 30));
        panel1.setBorder(new EmptyBorder(10, 20, 5, 20));
        this.add(panel1, BorderLayout.SOUTH);
        this.add(panel2, BorderLayout.CENTER);
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new MainFrame(user);
            }
        });
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(DataHandler.deleteWorkout(user, workout)){
                    System.out.println("deleted " + workout.getUuid());
                }else{
                    JOptionPane.showMessageDialog(
                        null,
                        "Ooops, something went wrong",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                    );
                }
                dispose();
                new MainFrame(user);
            }
        });
    }
}
