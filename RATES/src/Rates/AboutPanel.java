package Rates;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class AboutPanel extends JPanel implements ActionListener {

    public static final String NAME = "About Panel";
    public static RatesDriver ratesDriver;
    JButton backBtn, profileBtn;
    JTextArea reviewText;
    JScrollPane reviewTextScroll;
    JLabel title;
    Insets i;
    private final int WIDTH = 400, HEIGHT = 500;

    AboutPanel (RatesDriver ratesDriver) {
        // Ratesdrive object and layout setup
        AboutPanel.ratesDriver = ratesDriver;
        GridBagConstraints gbc = new GridBagConstraints();
        setLayout(new GridBagLayout());

        // -----------------------------------
        // ---------- Top Buttons ------------
        // -----------------------------------
        i = new Insets(10, 10, 0, 0);
        gbc.anchor = GridBagConstraints.NORTHWEST;
        backBtn = new JButton("Back");
        backBtn.addActionListener(this);
        addComp(backBtn, 0, 0, 1, 0, i, gbc);

        i = new Insets(10, 0, 0, 10);
        gbc.anchor = GridBagConstraints.NORTHEAST;
        profileBtn = new JButton(ratesDriver.getCurrentUser());
        profileBtn.addActionListener(this);
        addComp(profileBtn, 10, 0, 1, 0, i, gbc);

        // -----------------------------------
        // ------- Center Buttons ------------
        // -----------------------------------
        i = new Insets(20, 0, 0, 0);
        gbc.anchor = GridBagConstraints.NORTH;
        title = new JLabel("<html><H1>About</h1></html>");
        addComp(title, 5, 1, 1, 0, i, gbc);
        addComp(Box.createHorizontalStrut(1), 5, 2, 2, 1, i, gbc);


        i = new Insets(300, 0, 0, 0);
        reviewText = new JTextArea(8, 45);
        reviewText.setEditable(false);
        reviewText.setText("RATES - Real Time Student Evaluation System - Made in Java Swing");
        reviewTextScroll = new JScrollPane(reviewText);
        i = new Insets(220, 0, 0, 0);
        addComp(reviewTextScroll, 5, 2, 2, 0, i, gbc);

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
        if (e.getSource() == backBtn) {
            ratesDriver.showCard(SettingsPanel.NAME);
        }
        else if (e.getSource() == profileBtn) {
            ratesDriver.profilePanel.refreshPanel();
            ratesDriver.showCard(ProfilePanel.NAME);
        }

    }
}
