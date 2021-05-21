package Rates;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ReviewAddPanel extends JPanel implements ActionListener {

    public static final String NAME = "Review Add Panel";
    public static RatesDriver ratesDriver;
    JButton backBtn, profileBtn, uploadBtn, submitBtn;;
    JLabel title;
    String topic, reviewType;
    JLabel topicLabel, textLabel, topicName, anonymousLabel, imageName;
    JTextField topicField, topicNameField;
    JScrollPane reviewTextScroll;
    JTextArea reviewText;
    DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
    JFormattedTextField reviewDate;
    JFileChooser fc = new JFileChooser();
    FileNameExtensionFilter filter;
    JCheckBox anonymousCheck;
    File file;
    Boolean fileUploaded = false;
    String fileURL;

    Insets i;

    ReviewAddPanel (RatesDriver ratesDriver) {
        // Set filter for file types - .txt/text files
        filter = new FileNameExtensionFilter("Image/Text FILE", "txt", "text", "png", "jpg", "jpeg");
        // Apply the filter to the file chooser
        fc.setFileFilter(filter);

        // Ratesdrive object and layout setup
        ReviewAddPanel.ratesDriver = ratesDriver;
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
        title = new JLabel("<html><H1>New Review</h1></html>");
        addComp(title, 5, 1, 1, 0, i, gbc);
        addComp(Box.createHorizontalStrut(1), 5, 3, 2, 1, i, gbc);


        Dimension d = new Dimension(100, 30);

        // TOPIC ID LABEL/FIELD
        i = new Insets(75, 0, 0, 180);
        topicLabel = new JLabel("Topic ID:");
        addComp(topicLabel, 5, 1, 2, 0, i, gbc);

        i = new Insets(95, 0, 0, 180);
        topicField = new JTextField();
        topicField.setEditable(false);
        topicField.setSize(d);
        topicField.setMaximumSize(d);
        topicField.setMinimumSize(d);
        topicField.setPreferredSize(d);
        addComp(topicField, 5, 1, 2, 0, i, gbc);


        i = new Insets(75, 180, 0, 0);
        topicName = new JLabel("Topic Name:");
        addComp(topicName, 5, 1, 2, 0, i, gbc);

        d = new Dimension(200, 30);
        i = new Insets(95, 180, 0, 0);
        topicNameField = new JTextField();
        topicNameField.setEditable(false);
        topicNameField.setSize(d);
        topicNameField.setMaximumSize(d);
        topicNameField.setMinimumSize(d);
        topicNameField.setPreferredSize(d);
        addComp(topicNameField, 5, 1, 2, 0, i, gbc);


        // REVIEW TEXT BODY
        d = new Dimension(100, 30);
        i = new Insets(130, 0, 0, 0);
        textLabel = new JLabel("Review Body:");
        addComp(textLabel, 5, 2, 2, 0, i, gbc);

        reviewText = new JTextArea(8, 30);
        reviewTextScroll = new JScrollPane(reviewText);
        i = new Insets(150, 0, 0, 0);
        addComp(reviewTextScroll, 5, 2, 2, 0, i, gbc);


        // Image Upload
        i = new Insets(300, 0, 0, 80);
        imageName = new JLabel("No File");
        imageName.setPreferredSize(new Dimension(200,80));
        addComp(imageName, 5, 3, 2, 0, i, gbc);

        i = new Insets(325, 150, 0, 0);
        uploadBtn = new JButton("Upload");
        uploadBtn.addActionListener(this);
        setBtnSize(uploadBtn, d);
        addComp(uploadBtn, 5, 3, 1, 0, i, gbc);



        // Submit button / Anonymous checkbox
        i = new Insets(350, 10, 0, 10);
        submitBtn = new JButton("Submit");
        submitBtn.addActionListener(this);
        setBtnSize(submitBtn, d);
        addComp(submitBtn, 5, 5, 1, 0, i, gbc);

        i = new Insets(345, 0, 0, 300);
        anonymousLabel = new JLabel("Post Anonymously?");
        addComp(anonymousLabel, 5, 5, 2, 0, i, gbc);

        anonymousCheck = new JCheckBox();
        anonymousCheck.setSelected(false);
        i = new Insets(360, 0, 0, 300);
        addComp(anonymousCheck, 5, 5, 2, 0, i, gbc);





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
    public void refreshPanel(String topic, String reviewType, String subject) {
        this.topic = topic;
        this.reviewType = reviewType;
        file = null;
        fileUploaded = false;
        fileURL = null;

        topicNameField.setText(subject);

        fc = new JFileChooser();
        title.setText("<html><H1>" + reviewType + " Review</h1></html>");
        profileBtn.setText(ratesDriver.getCurrentUser());
        imageName.setText("No File");
        imageName.setIcon(null);
        reviewText.setText("");
        anonymousCheck.setSelected(false);
        topicField.setText(this.topic);
    }

    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backBtn) {
            ratesDriver.showCard(ReviewPanel.NAME);
        }
        else if (e.getSource() == profileBtn) {
            ratesDriver.profilePanel.refreshPanel();
            ratesDriver.showCard(ProfilePanel.NAME);
        }


        // ---------------------------------
        // Check if Open Button is pressed
        // ---------------------------------
        if (e.getSource() == uploadBtn) {
            int returnVal = fc.showOpenDialog(this);
            if (returnVal == fc.getApproveButtonMnemonic()) {
                // Reset file icon
                imageName.setIcon(null);
                // Get file from open dialog
                file = fc.getSelectedFile();
                try {
                    FileReader fr = new FileReader(file.getAbsoluteFile());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                // Set name of file to label
                imageName.setText(file.getName());
                fileUploaded = true;

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

            // ---------------------------------
            // Check if Save Button is pressed
            // ---------------------------------
        } else if (e.getSource() == submitBtn) {

            // Check if file was actually uploaded, dont run if nothing was uploaded
            if (fileUploaded) {
                InputStream is;
                OutputStream os;
                try {
                    // Read in opened file, output it in /files/ folder
                    // e.g. User opens a file, and it copies it to program files folder
                    is = new FileInputStream(file);
                    os = new FileOutputStream("./files/" + file.getName());
                    byte[] buffer = new byte[1024];
                    int length;
                    while ((length = is.read(buffer)) > 0) {
                        os.write(buffer, 0, length);
                    }
                    is.close();
                    os.close();

                } catch (Exception ex) {
                    ex.printStackTrace();
                }

                // File is saved, get the url to file location
                fileURL = "./files/" + file.getName();
            }

            Review review = new Review();
            review.setTopic_id(topic);
            review.setUserName(ratesDriver.getCurrentUser());
            review.setReviewText(reviewText.getText());
            review.setDate(getCurrentTimeStamp());
            review.setAnonymous(anonymousCheck.isSelected());
            review.setFileUpload(fileURL);
            review.setReview_type(reviewType);

            if (Database.insertReview(review)) {
                JOptionPane.showMessageDialog(this, "Review successfully Created!");
                ratesDriver.showCard(HomePanel.NAME);
            } else {
                JOptionPane.showMessageDialog(this, "Error creating review", "ERROR",
                        JOptionPane.ERROR_MESSAGE);
            }
            fileUploaded = false;
        }
    }

    public static String getCurrentTimeStamp() {
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//dd/MM/yyyy
        Date now = new Date();
        String strDate = sdfDate.format(now);
        return strDate;
    }
}
