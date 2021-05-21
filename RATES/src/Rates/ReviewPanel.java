package Rates;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

public class ReviewPanel extends JPanel implements ActionListener {

    public static final String NAME = "Review Panel";
    public static RatesDriver ratesDriver;
    JButton backBtn, profileBtn, continueBtn;
    JLabel title, topicTitle, typeTitle;
    JComboBox<String> topicsBox, typeBox;
    String topicsBoxContents = "";
    String typeBoxContents = "";
    Insets i;

    ReviewPanel (RatesDriver ratesDriver) {
        // Ratesdrive object and layout setup
        ReviewPanel.ratesDriver = ratesDriver;
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
        profileBtn = new JButton("Profile");
        profileBtn.addActionListener(this);
        addComp(profileBtn, 10, 0, 1, 0, i, gbc);

        // -----------------------------------
        // ------- Center Buttons ------------
        // -----------------------------------
        i = new Insets(20, 0, 0, 0);
        gbc.anchor = GridBagConstraints.NORTH;
        title = new JLabel("<html><H1>Create Review</h1></html>");
        addComp(title, 5, 1, 1, 0, i, gbc);
        addComp(Box.createHorizontalStrut(1), 5, 3, 2, 1, i, gbc);

        topicTitle = new JLabel("Topic ID:");
        typeTitle = new JLabel("Review Type:");

        i = new Insets(100, 0, 0, 0);
        addComp(topicTitle, 5, 1, 2, 0, i, gbc);

        i = new Insets(120, 0, 0, 0);
        Dimension d = new Dimension(200, 60);
        topicsBox = new JComboBox<>();
        topicsBox.addActionListener(this);
        topicsBox.setSize(d);
        topicsBox.setMaximumSize(d);
        topicsBox.setMinimumSize(d);
        topicsBox.setPreferredSize(d);
        addComp(topicsBox, 5, 2, 2, 0, i, gbc);

        i = new Insets(210, 0, 0, 0);
        addComp(typeTitle, 5, 3, 2, 0, i, gbc);

        i = new Insets(140, 0, 0, 0);
        String[] types = {"", "Lecture", "Tutorial", "Practical", "Workshop", "Other"};
        typeBox = new JComboBox<>(types);
        typeBox.addActionListener(this);
        typeBox.setSize(d);
        typeBox.setMaximumSize(d);
        typeBox.setMinimumSize(d);
        typeBox.setPreferredSize(d);
        addComp(typeBox, 5, 4, 2, 0, i, gbc);

        i = new Insets(280, 10, 0, 10);
        continueBtn = new JButton("Continue");
        continueBtn.addActionListener(this);
        setBtnSize(continueBtn, d);
        addComp(continueBtn, 5, 5, 1, 0, i, gbc);

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
        ArrayList<String> topicList = Database.getTopics(ratesDriver.getCurrentUser());
        topicsBox.removeAllItems();
        topicsBox.addItem("");

        if (topicList != null) {
            for (String s : topicList) {
                topicsBox.addItem(s);
            }
        }

        typeBox.setSelectedIndex(0);
    }

    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        // Button Press
        if (e.getSource() == backBtn) {
            ratesDriver.showCard(HomePanel.NAME);
        }
        else if (e.getSource() == profileBtn) {
            ratesDriver.showCard(ProfilePanel.NAME);
        }
        else if (e.getSource() == continueBtn) {
            if (topicsBox.getSelectedIndex() == 0 || typeBox.getSelectedIndex() == 0) {
                JOptionPane.showMessageDialog(this, "ComboBox Not Selected!!",
                        "Empty ComboBox", JOptionPane.ERROR_MESSAGE);
            } else {
                ratesDriver.reviewAddPanel.refreshPanel(topicsBoxContents, typeBoxContents);
                ratesDriver.showCard(ReviewAddPanel.NAME);
            }
        }

        // ComboBox Selected
        if (e.getSource() == topicsBox) {
            JComboBox cb = (JComboBox) e.getSource();
            topicsBoxContents = (String) cb.getSelectedItem();
        }
        else if (e.getSource() == typeBox) {
            JComboBox cb = (JComboBox) e.getSource();
            typeBoxContents = (String) cb.getSelectedItem();
        }
    }
}
