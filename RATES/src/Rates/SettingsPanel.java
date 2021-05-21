package Rates;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SettingsPanel extends JPanel implements ActionListener {

    public static final String NAME = "Settings Panel";
    public static RatesDriver ratesDriver;
    JButton profileBtn;

    SettingsPanel (RatesDriver ratesDriver) {

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

    }
}
