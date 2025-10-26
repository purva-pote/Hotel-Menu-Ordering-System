package user;
import javax.swing.*;
import java.io.PrintWriter;
import java.io.FileWriter;

public class feedback_win {
    public void show() {
        JFrame win = new JFrame("Feedback");
        win.setSize(300, 200);
        JLabel lb = new JLabel("Feedback or tip?");
        lb.setBounds(30,40,120,25);
        win.add(lb);
        JTextField txt = new JTextField();
        txt.setBounds(30,70,200,25);
        win.add(txt);
        JButton b = new JButton("Submit");
        b.setBounds(30,110,100,25);
        win.add(b);
        b.addActionListener(e->{
            try(PrintWriter w=new PrintWriter(new FileWriter("feedback.txt",true))){
                w.println(txt.getText());
            }catch(Exception x){}
            JOptionPane.showMessageDialog(win,"Thank you!");
            win.dispose();
            main.main_win.main(null);
        }); 
        win.setLayout(null);
        win.setVisible(true);
    }
}
