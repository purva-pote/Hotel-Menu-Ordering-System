package main;
import javax.swing.*;
import java.awt.*;

public class main_win {
    public static void main(String[] args) {
        JFrame win = new JFrame("Hotel Menu");
        win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Font font = new Font("SansSerif", Font.PLAIN, 16); // Or any font and size you want
        java.util.Enumeration<Object> keys = UIManager.getDefaults().keys();
        while (keys.hasMoreElements()) {
            Object key = keys.nextElement();
            Object value = UIManager.get(key);
            if (value instanceof javax.swing.plaf.FontUIResource) {
                UIManager.put(key, new javax.swing.plaf.FontUIResource(font));
            }
        }

        // Center panel using GridBagLayout for button arrangement
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JButton b1 = new JButton("User");
        JButton b2 = new JButton("Admin");

        gbc.insets = new Insets(8, 24, 8, 24); // Spacing between buttons

        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(b1, gbc);
        gbc.gridx = 1;
        panel.add(b2, gbc);

        win.add(panel, BorderLayout.CENTER);

        b1.addActionListener(e -> {
            new user.user_win().show();
            win.dispose();
        });
        b2.addActionListener(e -> {
            final String CORRECT_PASSWORD = "admin";
            String inputPassword = JOptionPane.showInputDialog(
                win,
                "Enter Admin Password:",
                "Admin Access Required",
                JOptionPane.QUESTION_MESSAGE
            );
            if (inputPassword != null && inputPassword.equals(CORRECT_PASSWORD)) {
                new admin.admin_win().show();
                win.dispose();
            } else if (inputPassword != null) {
                JOptionPane.showMessageDialog(
                    win,
                    "Incorrect Password. Access Denied.",
                    "Security Error",
                    JOptionPane.ERROR_MESSAGE
                );
            }
            win.dispose();
        });

        win.setSize(600, 280);
        win.setMinimumSize(new Dimension(400, 200));
        win.setLocationRelativeTo(null);
        win.setVisible(true);
    }
}
