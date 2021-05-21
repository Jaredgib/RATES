package Rates;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class ReviewHistoryPanel extends JPanel implements ActionListener, ListSelectionListener {

    public static final String NAME = "Review History Panel";
    public static RatesDriver ratesDriver;
    JButton backBtn, profileBtn, submitBtn;
    JLabel title;
    Insets i;
    JList historyList;
    String selectedList = null;
    ArrayList<Review> userReviews;
    DefaultListModel<Review> l1 = new DefaultListModel<>();

    ReviewHistoryPanel (RatesDriver ratesDriver) {
        // Ratesdrive object and layout setup
        ReviewHistoryPanel.ratesDriver = ratesDriver;
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
        i = new Insets(10, 0, 0, 0);
        gbc.anchor = GridBagConstraints.NORTH;
        title = new JLabel("<html><H1>Review History</h1></html>");
        addComp(title, 5, 1, 1, 0, i, gbc);
        addComp(Box.createHorizontalStrut(1), 5, 3, 2, 1, i, gbc);

        i = new Insets(80, 0, 0, 0);
        historyList = new JList(l1);
        historyList.setBounds(100, 100, 50, 200);
        historyList.setFixedCellHeight(30);
        historyList.setFixedCellWidth(50);
        JScrollPane sp = new JScrollPane(historyList);
        historyList.setPreferredSize(new Dimension(200,200));
        sp.setPreferredSize(new Dimension(500,300));

        //historyList.setSize(400,400);

        addComp(sp, 5, 2, 1, 0, i, gbc);

        i = new Insets(450, 0, 0, 0);
        submitBtn = new JButton("View Review");
        submitBtn.addActionListener(this);
        addComp(submitBtn, 5, 3, 1, 0, i, gbc);

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

        userReviews = Database.getReviews(ratesDriver.getCurrentUser());
        l1.removeAllElements();

        if (userReviews != null) {
            for (Review r : userReviews) {
                //String temp = "Review ID : " + r.getReview_id() + " || Review Type : " + r.getReview_type() + " || Review Date : " + r.getDate();
                l1.addElement(r);
            }
        }

        selectedList = null;
        historyList.clearSelection();
    }

    /**
     * Called whenever the value of the selection changes.
     *
     * @param e the event that characterizes the change.
     */
    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (!historyList.isSelectionEmpty()) {
            // Get the selected review from list
            selectedList = (String)historyList.getSelectedValue();
        }
    }

    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backBtn) {
            ratesDriver.showCard(HomePanel.NAME);
        }
        else if (e.getSource() == profileBtn) {
            ratesDriver.profilePanel.refreshPanel();
            ratesDriver.showCard(ProfilePanel.NAME);
        }
        else if (e.getSource() == submitBtn) {
            if (historyList.getSelectedValue() == null) {
                JOptionPane.showMessageDialog(this,"Error: NULL entry Selected", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                ratesDriver.reviewHistoryViewPanel.refreshPanel((Review) historyList.getSelectedValue());
                ratesDriver.showCard(ReviewHistoryViewPanel.NAME);
            }
        }
    }
}
