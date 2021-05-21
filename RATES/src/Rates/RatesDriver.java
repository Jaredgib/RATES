package Rates;

import java.awt.*;
import javax.swing.*;

public class RatesDriver extends JPanel {
    // Create the cardLout and all the panels used by the app
    public final CardLayout cardLayout = new CardLayout();
    public final HomePanel homePanel = new HomePanel(this);
    public final ReviewPanel reviewPanel = new ReviewPanel(this);
    public final ReviewAddPanel reviewAddPanel = new ReviewAddPanel(this);
    public final ReviewHistoryPanel reviewHistoryPanel = new ReviewHistoryPanel(this);
    public final ReviewHistoryViewPanel reviewHistoryViewPanel = new ReviewHistoryViewPanel(this);
    public final ProfilePanel profilePanel = new ProfilePanel(this);
    public final ProfileEditPanel profileEditPanel = new ProfileEditPanel(this);
    public final SettingsPanel settingsPanel = new SettingsPanel(this);
    public final AboutPanel aboutPanel = new AboutPanel(this);

    // Keeps track of the current user logged in so that data can be obtained from database
    public String currentUser = null;

    public String getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(String currentUser) {
        this.currentUser = currentUser;
    }


    /**
     * Constructor for the app driver, creates the card layout and adds all panels to it
     */
    public RatesDriver() {
        setLayout(cardLayout);
        // Login/Home Panels
        LoginPanel loginPanel = new LoginPanel(this);
        add(loginPanel, LoginPanel.NAME);
        add(homePanel, HomePanel.NAME);

        // Review/AddReview Panels
        add(reviewPanel, ReviewPanel.NAME);
        add(reviewAddPanel, ReviewAddPanel.NAME);

        // History Panels
        add(reviewHistoryPanel, ReviewHistoryPanel.NAME);
        add(reviewHistoryViewPanel, ReviewHistoryViewPanel.NAME);

        // Profile/Settings Panels
        add(profilePanel, ProfilePanel.NAME);
        add(profileEditPanel, ProfileEditPanel.NAME);
        add(settingsPanel, SettingsPanel.NAME);
        add(aboutPanel, AboutPanel.NAME);
    }

    /**
     * Method for finding getting a specific panel from the card layout
     *
     * @param name Name associated with a particular panel
     */
    public void showCard(String name) {
        cardLayout.show(this, name);
    }

    /**
     * Main program entry, creates the object containing the panels/cards
     * and adds them to a JFrame and displays frame
     *
     * @param args N/A
     */
    public static void main (String[] args){
        RatesDriver cards = new RatesDriver();

        JFrame frame = new JFrame("RATES");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        frame.add(cards);

        frame.pack();
        //frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }
}
