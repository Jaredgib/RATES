package Rates;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Structure of homepage of RATES app - Shows after login
 */
public class HomePanel extends JPanel implements ActionListener {

    public static final String NAME = "Rates Panel";
    public static RatesDriver ratesDriver;
    JButton reviewBtn, historyBtn, backBtn, profileBtn;
    JButton settingsBtn, logoutBtn;
    JLabel title;
    Insets i;
    private final int WIDTH = 400, HEIGHT = 500;


    public HomePanel(final RatesDriver ratesDriver) {
        HomePanel.ratesDriver = ratesDriver;


        GridBagConstraints gbc = new GridBagConstraints();
        setLayout(new GridBagLayout());


        // -----------------------------------
        // ---------- Top Buttons ------------
        // -----------------------------------
        i = new Insets(10, 0, 0, 10);
        gbc.anchor = GridBagConstraints.NORTHEAST;
        profileBtn = new JButton("Profile");
        profileBtn.addActionListener(this);
        addComp(profileBtn, 10, 0, 1, 0, i, gbc);




        // -----------------------------------
        // ------- Center Buttons ------------
        // -----------------------------------
        i = new Insets(20, 0, 0, 0);
        gbc.anchor = GridBagConstraints.NORTH;
        title = new JLabel("<html><H1>RATES HOME</h1></html>");
        addComp(title, 5, 1, 1, 0, i, gbc);
        addComp(Box.createHorizontalStrut(1), 5, 3, 2, 1, i, gbc);

        Dimension d = new Dimension(160,80);
        reviewBtn = new JButton("Create Review");
        reviewBtn.addActionListener(this);
        setBtnSize(reviewBtn, d);
        addComp(reviewBtn, 5, 4, 2, 2, i, gbc);

        historyBtn = new JButton("Review History");
        historyBtn.addActionListener(this);
        setBtnSize(historyBtn, d);
        addComp(historyBtn, 5, 6, 2, 2, i, gbc);


        // -----------------------------------
        // ------- Bottom Buttons ------------
        // -----------------------------------
        gbc.anchor = GridBagConstraints.SOUTHWEST;
        i = new Insets(0, 10, 10, 0);
        settingsBtn = new JButton("Settings");
        settingsBtn.addActionListener(this);
        addComp(settingsBtn, 0, 8, 1, 1, i, gbc);

        gbc.anchor = GridBagConstraints.SOUTHEAST;
        i = new Insets(0, 0, 10, 10);
        logoutBtn = new JButton("Logout");
        logoutBtn.addActionListener(this);
        addComp(logoutBtn, 10, 8, 1, 1, i, gbc);


        setPreferredSize(new Dimension (WIDTH, HEIGHT));
    }

    private void setBtnSize(JButton b, Dimension d) {
        b.setSize(d);
        b.setMinimumSize(d);
        b.setMaximumSize(d);
        b.setPreferredSize(d);
    }

    private void addComp(Component comp, int x, int y, int w, int h, Insets i, GridBagConstraints gbc) {
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.gridwidth = w;
        gbc.gridheight = h;
        gbc.insets = i;
        add(comp, gbc);
    }

    /**
     * Updates specific panel objects that use data from database
     */
    public void refreshPanel() {
        profileBtn.setText(ratesDriver.getCurrentUser());
    }

    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == reviewBtn) {
            ratesDriver.reviewPanel.refreshPanel();
            ratesDriver.showCard(ReviewPanel.NAME);
        }
        else if (e.getSource() == historyBtn) {
            ratesDriver.reviewHistoryPanel.refreshPanel();
            ratesDriver.showCard(ReviewHistoryPanel.NAME);
        }
        else if (e.getSource() == backBtn) {
            ratesDriver.showCard(LoginPanel.NAME);
        }
        else if (e.getSource() == profileBtn) {
            ratesDriver.showCard(ProfilePanel.NAME);
        }
        else if (e.getSource() == logoutBtn) {
            ratesDriver.setCurrentUser(null);
            ratesDriver.showCard(LoginPanel.NAME);
        }
    }
}
