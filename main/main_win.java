package main;
import javax.swing.*;
import java.awt.event.*;

public class main_win {
    public static void main(String[] args) {
        JFrame win = new JFrame("Hotel Menu");
        win.setSize(600, 280);
        win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JButton b1 = new JButton("User");
        JButton b2 = new JButton("Admin");
        b1.setBounds(150, 85, 120, 40);
        b2.setBounds(320, 85, 120, 40);
        win.setLayout(null);
        win.add(b1);
        win.add(b2);
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
                // If the user entered a wrong password (and didn't press cancel)
                JOptionPane.showMessageDialog(
                    win, 
                    "Incorrect Password. Access Denied.", 
                    "Security Error", 
                    JOptionPane.ERROR_MESSAGE
                );
            }
            win.dispose();
        });
        win.setVisible(true);
    }
}
