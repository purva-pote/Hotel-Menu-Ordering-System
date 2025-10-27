package main;
import javax.swing.*;
import java.awt.*;

public class main_win {
    public static void main(String[] args) {
        JFrame win = new JFrame("Hotel Menu");
        win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        


        Font font = new Font("SansSerif", Font.PLAIN, 16);
        java.util.Enumeration<Object> keys = UIManager.getDefaults().keys();
        while (keys.hasMoreElements()) {
            Object key = keys.nextElement();
            Object value = UIManager.get(key);
            if (value instanceof javax.swing.plaf.FontUIResource) {
                UIManager.put(key, new javax.swing.plaf.FontUIResource(font));
            }
        }

        BackgroundPanel bgPanel = new BackgroundPanel("mainbg.jpg");
        bgPanel.setLayout(new GridBagLayout());
        GridBagConstraints outerGbc = new GridBagConstraints();
        outerGbc.gridx = 0;
        outerGbc.gridy = 0;
        outerGbc.anchor = GridBagConstraints.CENTER;


        JPanel innerPanel = new JPanel();
        innerPanel.setLayout(new BoxLayout(innerPanel, BoxLayout.Y_AXIS));

        innerPanel.setBackground(new Color(0, 0, 0, 0)); // black with alpha for transparency
        innerPanel.setOpaque(true);



        JLabel heading = new JLabel("Your Hotel Name Here", SwingConstants.CENTER);
        heading.setAlignmentX(Component.CENTER_ALIGNMENT);
        heading.setFont(new Font("SansSerif", Font.BOLD, 48)); 
        heading.setOpaque(false); 
        heading.setForeground(Color.WHITE); 
        heading.setBorder(BorderFactory.createEmptyBorder(30, 40, 20, 40));

        /*
        heading.setUI(new javax.swing.plaf.basic.BasicLabelUI() {
            protected void paintSafely(Graphics g) {
                Graphics2D g2 = (Graphics2D) g;
                g2.setColor(Color.BLACK);
                g2.drawString(heading.getText(), 3, heading.getBaseline(0, 0) + 1);
                g2.setColor(Color.WHITE);
                g2.drawString(heading.getText(), 2, heading.getBaseline(0, 0));
            }
        });*/
        

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        panel.setOpaque(false);

        JButton b1 = new JButton("User");
        JButton b2 = new JButton("Admin");

        gbc.insets = new Insets(8, 24, 8, 24);
        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(b1, gbc);
        gbc.gridx = 1;
        panel.add(b2, gbc);

        panel.setAlignmentX(Component.CENTER_ALIGNMENT);

        innerPanel.add(heading);
        innerPanel.add(panel);

        bgPanel.add(innerPanel, outerGbc);
        win.setContentPane(bgPanel);

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
