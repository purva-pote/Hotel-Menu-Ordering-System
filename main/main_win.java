package main;
import javax.swing.*;
import java.awt.event.*;

public class main_win {
    public static void main(String[] args) {
        JFrame win = new JFrame("Hotel Menu");
        win.setSize(400, 200);
        win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JButton b1 = new JButton("User");
        JButton b2 = new JButton("Admin");
        b1.setBounds(100, 70, 80, 30);
        b2.setBounds(200, 70, 80, 30);
        win.setLayout(null);
        win.add(b1);
        win.add(b2);
        b1.addActionListener(e -> {
            new user.user_win().show();
            win.dispose();
        });
        b2.addActionListener(e -> {
            new admin.admin_win().show();
            win.dispose();
        });
        win.setVisible(true);
    }
}
