package Rates;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class ReviewHistoryViewPanel extends JPanel implements ActionListener {

    public static final String NAME = "Review History View Panel";
    public static RatesDriver ratesDriver;
    JButton backBtn, profileBtn, closeBtn;
    JLabel title;
    JLabel topicLabel, textLabel, dateLabel, imageName, reviewIDLabel, usernameLabel, topicName;
    JTextField topicField, reviewDate, reviewID, username, topicNameField;
    JScrollPane reviewTextScroll;
    JTextArea reviewText;
    File file;

    Insets i;

    ReviewHistoryViewPanel (RatesDriver ratesDriver) {
        // Ratesdrive object and layout setup
        ReviewHistoryViewPanel.ratesDriver = ratesDriver;
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
        title = new JLabel("<html><H1>Review</h1></html>");
        addComp(title, 5, 1, 1, 0, i, gbc);
        addComp(Box.createHorizontalStrut(1), 5, 3, 2, 1, i, gbc);


        // -----------------------------------
        // ------- TopicID Label/Field -------
        // -----------------------------------
        Dimension d = new Dimension(100, 30);
        i = new Insets(75, 0, 0, 325);
        topicLabel = new JLabel("Topic ID:");
        addComp(topicLabel, 5, 1, 2, 0, i, gbc);

        i = new Insets(95, 0, 0, 325);
        topicField = new JTextField();
        topicField.setEditable(false);
        topicField.setSize(d);
        topicField.setMaximumSize(d);
        topicField.setMinimumSize(d);
        topicField.setPreferredSize(d);
        addComp(topicField, 5, 1, 2, 0, i, gbc);

        // -----------------------------------
        // ------- Date Label/Field ----------
        // -----------------------------------
        i = new Insets(75, 350, 0, 0);
        dateLabel = new JLabel("Review Date");
        addComp(dateLabel, 5, 1, 2, 0, i, gbc);

        d = new Dimension(130, 30);
        i = new Insets(95, 350, 0, 0);
        reviewDate = new JTextField();
        reviewDate.setEditable(false);
        reviewDate.setSize(d);
        reviewDate.setMaximumSize(d);
        reviewDate.setMinimumSize(d);
        reviewDate.setPreferredSize(d);
        addComp(reviewDate, 5, 1, 2, 0, i, gbc);

        // -----------------------------------
        // ------- Review Label/Field --------
        // -----------------------------------
        i = new Insets(135, 0, 0, 150);
        reviewIDLabel = new JLabel("Review ID:");
        addComp(reviewIDLabel, 5, 1, 2, 0, i, gbc);

        d = new Dimension(100, 30);
        i = new Insets(155, 0, 0, 150);
        reviewID = new JTextField();
        reviewID.setEditable(false);
        reviewID.setSize(d);
        reviewID.setMaximumSize(d);
        reviewID.setMinimumSize(d);
        reviewID.setPreferredSize(d);
        addComp(reviewID, 5, 1, 2, 0, i, gbc);

        // -----------------------------------
        // ------- Topic Name Label/Field ----
        // -----------------------------------
        i = new Insets(75, 0, 0, 0);
        topicName = new JLabel("Topic Name:");
        addComp(topicName, 5, 1, 2, 0, i, gbc);

        d = new Dimension(200, 30);
        i = new Insets(95, 0, 0, 0);
        topicNameField = new JTextField();
        topicNameField.setEditable(false);
        topicNameField.setSize(d);
        topicNameField.setMaximumSize(d);
        topicNameField.setMinimumSize(d);
        topicNameField.setPreferredSize(d);
        addComp(topicNameField, 5, 1, 2, 0, i, gbc);

        // -----------------------------------
        // ------- Username Label/Field ------
        // -----------------------------------
        d = new Dimension(100, 30);
        i = new Insets(135, 150, 0, 0);
        usernameLabel = new JLabel("Username:");
        addComp(usernameLabel, 5, 1, 2, 0, i, gbc);

        d = new Dimension(100, 30);
        i = new Insets(155, 150, 0, 0);
        username = new JTextField();
        username.setEditable(false);
        username.setSize(d);
        username.setMaximumSize(d);
        username.setMinimumSize(d);
        username.setPreferredSize(d);
        addComp(username, 5, 1, 2, 0, i, gbc);


        // -----------------------------------
        // ------- Review Text Body ----------
        // -----------------------------------
        i = new Insets(200, 0, 0, 0);
        textLabel = new JLabel("Review Body:");
        addComp(textLabel, 5, 2, 2, 0, i, gbc);

        reviewText = new JTextArea(8, 30);
        reviewText.setEditable(false);
        reviewTextScroll = new JScrollPane(reviewText);
        i = new Insets(220, 0, 0, 0);
        addComp(reviewTextScroll, 5, 2, 2, 0, i, gbc);


        // -----------------------------------
        // ------- Image Upload Label --------
        // -----------------------------------
        i = new Insets(360, 110, 0, 0);
        imageName = new JLabel("No File");
        imageName.setPreferredSize(new Dimension(200,80));
        addComp(imageName, 5, 3, 2, 0, i, gbc);


        // -----------------------------------
        // ------- Return History View Button
        // -----------------------------------
        i = new Insets(400, 10, 0, 10);
        closeBtn = new JButton("Close");
        closeBtn.addActionListener(this);
        setBtnSize(closeBtn, d);
        addComp(closeBtn, 5, 5, 1, 0, i, gbc);
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
    public void refreshPanel(Review review) {
        profileBtn.setText(ratesDriver.getCurrentUser());

        title.setText("<html><H1>" + review.getReview_type() + " Review</h1></html>");
        topicField.setText(review.getTopic_id());
        reviewDate.setText(review.getDate());
        reviewID.setText(review.getReview_id());
        topicNameField.setText(Database.getTopicName(review.getTopic_id()));

        if (review.isAnonymous()) {
            username.setText("Anonymous");
        } else {
            username.setText(review.getUserName());
        }

        reviewText.setText(review.getReviewText());

        imageName.setIcon(null);

        if (review.getFileUpload() == null) {
            imageName.setText("No File Uploaded");
        } else {
            JFileChooser fc = new JFileChooser();
            file = new File(review.getFileUpload());
            imageName.setText(file.getName());

            // Get file extension to test if image file
            String filename = file.getName();
            String fileEXT = filename.substring(filename.lastIndexOf("."), filename.length());
            // If image file, create scaled icon and apply to label
            if (fileEXT.equals(".jpg") || fileEXT.equals(".png")) {
                try {
                    BufferedImage img = null;
                    img = ImageIO.read(file);
                    //Image dimg = img.getScaledInstance(imageName.getWidth(), imageName.getHeight(), Image.SCALE_SMOOTH);
                    Image dimg = img.getScaledInstance(80, 80, Image.SCALE_SMOOTH);
                    ImageIcon icon = new ImageIcon(dimg);
                    imageName.setIcon(icon);
                    imageName.setSize(200, 200);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        }
    }

    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backBtn || e.getSource() == closeBtn) {
            ratesDriver.showCard(ReviewHistoryPanel.NAME);
        }
        else if (e.getSource() == profileBtn) {
            ratesDriver.profilePanel.refreshPanel();
            ratesDriver.showCard(ProfilePanel.NAME);
        }

    }
}
