package workout.tracker.gui;

import workout.tracker.data.DataHandler;
import workout.tracker.user.User;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.UUID;
import java.util.Vector;

public class Login extends JFrame{

    private JPanel signIn;
    private JPanel signUp;
    private JButton signInButton = new JButton("Sign in");
    private JButton signUpButton = new JButton("Sign up");

    private JTextField usernameTextSI = new JTextField();
    private JTextField passwordTextSI = new JTextField();
    private JLabel usernameSI = new JLabel("Username: ");
    private JLabel passwordSI = new JLabel("Password: ");

    private JTextField confirmTextSU = new JTextField();
    private JTextField usernameTextSU = new JTextField();
    private JTextField passwordTextSU = new JTextField();
    private JLabel usernameSU = new JLabel("Username: ");
    private JLabel passwordSU = new JLabel("Password: ");
    private JLabel confirmSU = new JLabel("Confirm password: ");

    private JTabbedPane tabbedPane;

    private JPanel panel1SI;
    private JPanel panel2SI;
    private JPanel panel3SI;

    private JPanel panel1SU;
    private JPanel panel2SU;
    private JPanel panel3SU;


    public Login(){
        setTitle("Login");
        setSize(350, 200);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        components();
        setVisible(true);
    }

    public void components(){
        tabbedPane = new JTabbedPane();

        panel1SI = new JPanel(new BorderLayout());
        panel2SI = new JPanel(new BorderLayout());
        signIn = new JPanel();
        signIn.setLayout(new GridLayout(2, 2));
        signIn.add(usernameSI);
        signIn.add(usernameTextSI);
        signIn.add(passwordSI);
        signIn.add(passwordTextSI);
        panel2SI.add(signIn, BorderLayout.NORTH);
        panel1SI.add(panel2SI, BorderLayout.CENTER);
        panel3SI = new JPanel(new BorderLayout());
        panel3SI.add(signInButton, BorderLayout.EAST);
        panel1SI.add(panel3SI, BorderLayout.SOUTH);
        panel1SI.setBorder(new EmptyBorder(40, 10, 0, 10));

        panel1SU = new JPanel(new BorderLayout());
        panel2SU = new JPanel(new BorderLayout());
        signUp = new JPanel(new GridLayout(3, 2));
        signUp.add(usernameSU);
        signUp.add(usernameTextSU);
        signUp.add(passwordSU);
        signUp.add(passwordTextSU);
        signUp.add(confirmSU);
        signUp.add(confirmTextSU);
        panel2SU.add(signUp, BorderLayout.NORTH);
        panel1SU.add(panel2SU, BorderLayout.CENTER);
        panel3SU = new JPanel(new BorderLayout());
        panel3SU.add(signUpButton, BorderLayout.EAST);
        panel1SU.add(panel3SU, BorderLayout.SOUTH);
        panel1SU.setBorder(new EmptyBorder(30, 10, 0, 10));

        tabbedPane.addTab("Sign in", panel1SI);
        tabbedPane.addTab("Sign up", panel1SU);
        add(tabbedPane);

        signInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                User user = DataHandler.readUser(usernameTextSI.getText(), passwordTextSI.getText());
                if(user == null){
                    JOptionPane.showMessageDialog(
                            null,
                            "Wrong username or password, please try again",
                            "Error",
                            JOptionPane.ERROR_MESSAGE

                    );
                }else {
                    dispose();
                }
            }
        });

        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(passwordTextSU.getText().equals(confirmTextSU.getText())){
                    User user = new User();
                    user.setUsername(usernameTextSU.getText());
                    user.setPassword(passwordTextSU.getText());
                    user.setUuid(UUID.randomUUID().toString());
                    user.setWorkouts(new Vector<>());
                    DataHandler.insertUser(user);
                    dispose();
                }else{
                    JOptionPane.showMessageDialog(
                            null,
                            "Passwords don't match, please try again",
                            "Error",
                            JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        });
    }

    public static void main(String[] args) {
        new Login();
    }
}
