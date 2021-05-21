package Rates;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Objects;
import javax.imageio.ImageIO;
import javax.swing.*;

public class ProfilePanel extends JPanel implements ActionListener {

    public static final String NAME = "Profile Panel";
    public static RatesDriver ratesDriver;
    JButton backBtn, profileBtn, uploadBtn, editBTN;
    JLabel title, profileIMG;
    Insets i;
    JFileChooser fc = new JFileChooser();

    ProfilePanel (RatesDriver ratesDriver) {
        // Ratesdrive object and layout setup
        ProfilePanel.ratesDriver = ratesDriver;
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
        title = new JLabel("<html><H1>Profile</h1></html>");
        addComp(title, 5, 1, 1, 0, i, gbc);
        addComp(Box.createHorizontalStrut(1), 5, 3, 2, 1, i, gbc);


        // -----------------------------------
        // ------- Image Upload Label --------
        // -----------------------------------
        i = new Insets(80, 0, 0, 0);
        profileIMG = new JLabel("");
        profileIMG.setPreferredSize(new Dimension(150,150));
        addComp(profileIMG, 5, 3, 2, 0, i, gbc);

        Dimension d = new Dimension(100, 30);
        i = new Insets(250, 0, 0, 0);
        uploadBtn = new JButton("Upload Pic");
        uploadBtn.addActionListener(this);
        setBtnSize(uploadBtn, d);
        addComp(uploadBtn, 5, 3, 1, 0, i, gbc);



        i = new Insets(400, 0, 0, 0);
        editBTN = new JButton("Add Topics");
        editBTN.addActionListener(this);
        setBtnSize(editBTN, d);
        addComp(editBTN, 5, 3, 1, 0, i, gbc);

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
        title.setText("<html><H1>" + ratesDriver.getCurrentUser() + " Profile</h1></html>");

        String pictureURL = Database.getProfilePic(ratesDriver.getCurrentUser());
        File file;

        file = new File(Objects.requireNonNullElse(pictureURL, "./files/placeholder.png"));
        JFileChooser fc = new JFileChooser();

        // If image file, create scaled icon and apply to label
        try {
            BufferedImage img = null;
            img = ImageIO.read(file);
            Image dimg = img.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
            ImageIcon icon = new ImageIcon(dimg);
            profileIMG.setIcon(icon);
            profileIMG.setSize(200, 200);
        } catch (IOException ioException) {
            ioException.printStackTrace();
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
        else if (e.getSource() == editBTN) {
            ratesDriver.profileEditPanel.refreshPanel();
            ratesDriver.showCard(ProfileEditPanel.NAME);
        }

        if (e.getSource() == uploadBtn) {
            int returnVal = fc.showOpenDialog(this);
            if (returnVal == fc.getApproveButtonMnemonic()) {
                // Get file from open dialog
                File file = fc.getSelectedFile();

                // Get file extension to test if image file
                String filename = file.getName();
                String fileEXT = filename.substring(filename.lastIndexOf("."), filename.length());
                // If image file, create scaled icon and apply to label then save
                if (fileEXT.equals(".jpg") || fileEXT.equals(".png")) {
                    try {
                        BufferedImage img = null;
                        img = ImageIO.read(file);
                        Image dimg = img.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
                        ImageIcon icon = new ImageIcon(dimg);
                        profileIMG.setIcon(icon);
                        profileIMG.setSize(200, 200);

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
                            String photoURL = "./files/" + file.getName();
                            Database.addProfilePic(photoURL, ratesDriver.getCurrentUser());

                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }

                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }

            }

        }

    }
}
