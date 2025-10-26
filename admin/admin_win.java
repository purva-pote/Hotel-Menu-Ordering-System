package admin;
import javax.swing.*;
import menu.menu_store;
import menu.dish;
import java.util.*;

public class admin_win {
    JFrame win;
    JTextField t_name, t_price, t_type;
    JTextArea area_orders;

    public void show() {
        menu_store.load();
        win = new JFrame("Admin");
        win.setSize(650, 480);
        win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel l1 = new JLabel("Dish Name");
        l1.setBounds(20, 20, 120, 32);
        t_name = new JTextField();
        t_name.setBounds(150, 20, 180, 32);

        JLabel l2 = new JLabel("Price");
        l2.setBounds(20, 70, 120, 32);
        t_price = new JTextField();
        t_price.setBounds(150, 70, 180, 32);

        JLabel l3 = new JLabel("Type");
        l3.setBounds(20, 120, 120, 32);
        String[] foodTypes = {"All", "Veg", "Non-Veg", "Jain"};
        JComboBox<String> t_type = new JComboBox<>(foodTypes);
        t_type.setBounds(150, 120, 180, 32);


        JButton b_add = new JButton("Add/Update");
        b_add.setBounds(20, 180, 150, 32);

        JLabel l4 = new JLabel("Recent Orders:");
        l4.setBounds(20, 240, 140, 25);
        area_orders = new JTextArea();
        area_orders.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(area_orders);
        scrollPane.setBounds(20, 270, 600, 100);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        win.add(scrollPane);


        win.add(l1); win.add(t_name); win.add(l2); win.add(t_price); win.add(l3); win.add(t_type);
        win.add(b_add); win.add(l4);

        b_add.addActionListener(e->{
            String n = t_name.getText();
            int pr;
            try {
                pr = Integer.parseInt(t_price.getText());
                if(pr<=0||n.length()==0) throw new Exception();
            } catch (Exception x) {
                JOptionPane.showMessageDialog(win,"Bad input");
                return;
            }
            menu_store.addDish(n, pr, (String) t_type.getSelectedItem()
            );
            menu_store.load();
            JOptionPane.showMessageDialog(win,"Menu updated!");
        });

        JButton b_r = new JButton("Refresh Orders");
        b_r.setBounds(480, 230, 140, 25);
        win.add(b_r);
        b_r.addActionListener(e->loadOrders());

        JButton b_back = new JButton("Back");
        b_back.setBounds(20, 390, 120, 32);
        win.add(b_back);
        b_back.addActionListener(e -> {
            win.dispose(); 
            main.main_win.main(null);
        });

        win.setLayout(null);
        win.setVisible(true);

        loadOrders();
    }

    void loadOrders() {
        try(Scanner s=new Scanner(new java.io.File("orders.txt"))){
            StringBuilder sb = new StringBuilder();
            while(s.hasNextLine()) sb.append(s.nextLine()+"\n");
            area_orders.setText(sb.toString());
        }catch(Exception e){ area_orders.setText("no orders yet"); }
    }
}

