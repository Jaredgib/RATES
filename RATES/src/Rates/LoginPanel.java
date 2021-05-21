package Rates;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


/**
 * Structure of login page
 */
public class LoginPanel extends JPanel implements ActionListener {

    public static final String NAME = "Login Panel";
    public static RatesDriver ratesDriver;

    JButton loginBtn, createUserBtn;
    JTextField loginF, passwordF;
    private final int WIDTH = 400, HEIGHT = 500;

    public LoginPanel(final RatesDriver ratesDriver) {
        LoginPanel.ratesDriver = ratesDriver;

        // Structure of the login page layout is done using boxlayout
        // with outside and inside panels
        //
        // Idea is to setup the outside area with empty white space through struts
        // to center the main login content in the center of the frame
        // inside panel then contains all the widgets and separates them with struts
        JPanel outside = new JPanel();
        JPanel inside = new JPanel();


        outside.setLayout(new BoxLayout(outside, BoxLayout.LINE_AXIS));
        inside.setLayout(new BoxLayout(inside, BoxLayout.PAGE_AXIS));

        // Create whitespace surrounding inside panel
        outside.add(Box.createHorizontalStrut(20));
        outside.add(inside);
        outside.add(Box.createHorizontalStrut(20));

        // Create title/labels/fields/button and separate with struts
        JLabel title1 = new JLabel("<html><h1>Flinders University<br>RATES</h1></html>");
        JLabel title2 = new JLabel("<html><p>Real-Time Activity<br>and Teaching Evaluation System</p></html>");
        inside.add(title1);
        inside.add(title2);
        inside.add(Box.createVerticalStrut(20));

        Dimension d = new Dimension(220,30);

        loginBtn = new JButton("Login");
        loginBtn.addActionListener(this);
        loginBtn.setSize(d);
        loginBtn.setMinimumSize(d);
        loginBtn.setMaximumSize(d);
        loginBtn.setPreferredSize(d);

        createUserBtn = new JButton("Create User");
        createUserBtn.addActionListener(this);
        createUserBtn.setSize(d);
        createUserBtn.setMinimumSize(d);
        createUserBtn.setMaximumSize(d);
        createUserBtn.setPreferredSize(d);

        loginBtn.setToolTipText("default login U:admin, P:admin");
        createUserBtn.setToolTipText("Create a new user account");


        loginF = new JTextField();
        passwordF = new JPasswordField();

        inside.add(new JLabel("Login:"));
        inside.add(loginF);
        inside.add(Box.createVerticalStrut(10));
        inside.add(new JLabel("Password:"));
        inside.add(passwordF);
        inside.add(Box.createVerticalStrut(10));
        inside.add(loginBtn);
        inside.add(Box.createVerticalStrut(10));
        inside.add(createUserBtn);


        inside.add(Box.createVerticalStrut(20));

        add(outside);

        setPreferredSize (new Dimension (WIDTH, HEIGHT));
    }


    public void actionPerformed(ActionEvent e) {

        // Login Button Pressed
        if (e.getSource() == loginBtn) {
            // Run authLogin method that checks database accounts VS login/password entered
            // If successful, clear text fields and progress into app home
            if (Database.authLogin(loginF.getText(), passwordF.getText())) {
                // Set current user in driver object to loginF.getText()
                ratesDriver.setCurrentUser(loginF.getText());
                loginF.setText("");
                passwordF.setText("");
                ratesDriver.homePanel.refreshPanel();
                ratesDriver.showCard(HomePanel.NAME);
            } else {
                JOptionPane.showMessageDialog(this, """
                        Incorrect Login Details
                        Default login details
                        Username: admin, Password: admin""", "Incorrect Login", JOptionPane.ERROR_MESSAGE);
            }

            // Create User Button Pressed
        } else if (e.getSource() == createUserBtn) {
            String username = JOptionPane.showInputDialog("Enter username");
            String password = JOptionPane.showInputDialog("Enter password");

            // Pretty messy, could not think of better way to execute
            // Checks if username/password entered is valid, if so attempt to add to database
            // Show dialog box if successful, if not successful at any point, show error dialog
            if ((username != null) && (username.length() > 0)) {
                if ((password != null) && (password.length() > 0)) {
                    if (Database.addUser(username, password)) {
                        JOptionPane.showMessageDialog(this, "Account Created Successfully");
                    } else {
                        JOptionPane.showMessageDialog(this, "Account Not Created!!",
                                "Database Entry Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Account Not Created!!",
                            "Password Null or to small", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Account Not Created!!",
                        "Username Null or to small", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
