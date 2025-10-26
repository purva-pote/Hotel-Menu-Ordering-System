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
        win.setSize(650, 520);
        win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel l_add_header = new JLabel("Add Item:");
        l_add_header.setBounds(20, 10, 150, 20); // X=20, above the first label
        win.add(l_add_header);

        JLabel l1 = new JLabel("Dish Name");
        l1.setBounds(20, 45, 100, 32);
        t_name = new JTextField();
        t_name.setBounds(150, 45, 180, 32);

        JLabel l2 = new JLabel("Price");
        l2.setBounds(20, 100, 100, 32);
        t_price = new JTextField();
        t_price.setBounds(150, 100, 180, 32);

        JLabel l3 = new JLabel("Type");
        l3.setBounds(20, 150, 100, 32);
        String[] foodTypes = {"All", "Veg", "Non-Veg", "Jain"};
        JComboBox<String> t_type = new JComboBox<>(foodTypes);
        t_type.setBounds(150, 150, 180, 32);

        JButton b_add = new JButton("Add");
        b_add.setBounds(20, 215, 150, 32);

        JLabel l_delete_header = new JLabel("Delete Item:");
        l_delete_header.setBounds(360, 10, 150, 20); // X=360, Y=5 for header position
        win.add(l_delete_header);

        JLabel l_delete = new JLabel("Dish Name:");
        l_delete.setBounds(360, 45, 80, 32);
        win.add(l_delete);

        JComboBox<String> box_delete = new JComboBox<>();
        box_delete.setBounds(460, 45, 150, 32);
        win.add(box_delete);

        menu_store.getDishes().forEach(dish -> box_delete.addItem(dish.getName()));

        JButton b_delete = new JButton("Delete");
        b_delete.setBounds(460, 100, 150, 32);
        win.add(b_delete);

        b_delete.addActionListener(e -> {
            String selectedItem = (String) box_delete.getSelectedItem();
            if (selectedItem != null) {
                menu_store.removeDish(selectedItem); // Remove the selected item from the menu
                menu_store.load(); // Reload the menu
                JOptionPane.showMessageDialog(win, "Item deleted successfully!");
        
                // Refresh the JComboBox
                box_delete.removeAllItems();
                menu_store.getDishes().forEach(dish -> box_delete.addItem(dish.getName()));
            } else {
                JOptionPane.showMessageDialog(win, "No item selected to delete.");
            }
        });

        JLabel l4 = new JLabel("Recent Orders:");
        l4.setBounds(20, 280, 140, 25);
        area_orders = new JTextArea();
        area_orders.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(area_orders);
        scrollPane.setBounds(20, 315, 600, 100);
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
        b_r.setBounds(480, 275, 140, 25);
        win.add(b_r);
        b_r.addActionListener(e->loadOrders());

        JButton b_back = new JButton("Back");
        b_back.setBounds(20, 435, 120, 32);
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

