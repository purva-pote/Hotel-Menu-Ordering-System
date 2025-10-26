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
        win.setSize(350, 400);
        win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel l1 = new JLabel("Dish Name");
        l1.setBounds(20, 20, 80, 25);
        t_name = new JTextField();
        t_name.setBounds(100, 20, 120, 25);

        JLabel l2 = new JLabel("Price");
        l2.setBounds(20, 60, 80, 25);
        t_price = new JTextField();
        t_price.setBounds(100, 60, 120, 25);

        /*JLabel l3 = new JLabel("Type");
        l3.setBounds(20, 100, 80, 25);
        t_type = new JTextField();
        t_type.setBounds(100, 100, 120, 25);*/

        JLabel l3 = new JLabel("Type");
        l3.setBounds(20, 100, 80, 25);
        String[] foodTypes = {"All", "Veg", "Non-Veg", "Jain"};
        JComboBox<String> t_type = new JComboBox<>(foodTypes);
        t_type.setBounds(100, 100, 120, 25);


        JButton b_add = new JButton("Add/Update");
        b_add.setBounds(60, 140, 120, 30);

        JLabel l4 = new JLabel("Recent Orders:");
        l4.setBounds(20, 190, 120, 25);
        area_orders = new JTextArea();
        area_orders.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(area_orders);
        scrollPane.setBounds(20, 220, 300, 100);
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
        b_r.setBounds(200, 190, 120, 25);
        win.add(b_r);
        b_r.addActionListener(e->loadOrders());

        JButton b_back = new JButton("Back");
        b_back.setBounds(20, 330, 80, 25);
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

