package user;
import javax.swing.*;
import menu.*;
import java.util.*;
import java.io.PrintWriter;
import java.io.FileWriter;

public class user_win {
    JFrame win;
    JComboBox<String> box_type;
    JComboBox<dish> box_dish;
    JTextField qty;
    JCheckBox chk_takeaway;
    JTextArea area_order;
    order order_now = new order();

    public void show() {
        menu_store.load();
        win = new JFrame("User Order");
        win.setSize(700, 450);
        win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel lb1 = new JLabel("Type:");
        lb1.setBounds(30, 20, 80, 32);
        win.add(lb1);
        box_type = new JComboBox<>(new String[]{"All", "Veg", "Non-Veg", "Jain"});
        box_type.setBounds(120, 20, 120, 32);
        win.add(box_type);

        JLabel lb2 = new JLabel("Dish:");
        lb2.setBounds(30, 70, 80, 32);
        win.add(lb2);
        box_dish = new JComboBox<>();
        box_dish.setBounds(120, 70, 230, 32);
        win.add(box_dish);

        JLabel lb3 = new JLabel("Qty:");
        lb3.setBounds(30, 120, 50, 32);
        win.add(lb3);
        qty = new JTextField("1");
        qty.setBounds(90, 120, 60, 32);
        win.add(qty);

        chk_takeaway = new JCheckBox("Takeaway");
        chk_takeaway.setBounds(170, 120, 120, 32);
        win.add(chk_takeaway);

        JButton b_add = new JButton("Add Item");
        b_add.setBounds(30, 220, 140, 32);
        win.add(b_add);

        JButton b_back = new JButton("Back");
        b_back.setBounds(30, 322, 140, 32);
        win.add(b_back);
        b_back.addActionListener(e -> {
            win.dispose(); 
            main.main_win.main(null);
        });

        area_order = new JTextArea();
        area_order.setEditable(false);
        JScrollPane scroll = new JScrollPane(area_order);
        scroll.setBounds(370, 20, 280, 180);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        win.add(scroll);

        JButton b_show = new JButton("Order Summary");
        b_show.setBounds(370, 220, 140, 32);
        win.add(b_show);

        JButton b_pay = new JButton("Place Order");
        b_pay.setBounds(520, 220, 130, 32);
        win.add(b_pay);

        box_type.addActionListener(a -> updateDishList());
        updateDishList();

        b_add.addActionListener(e->{
            dish d = (dish)box_dish.getSelectedItem();
            int q;
            try {
                q = Integer.parseInt(qty.getText());
                if(q<=0) throw new Exception();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(win,"Qty must be positive integer");
                return;
            }
            boolean take = chk_takeaway.isSelected();
            order_now.add(d, q, take);
            JOptionPane.showMessageDialog(win,"Item added");
            area_order.setText(order_now.summary());
        });

        b_show.addActionListener(e -> area_order.setText(order_now.summary()));

        b_pay.addActionListener(e->{
            saveOrder();
            win.dispose();
            new feedback_win().show();
        });

        win.setLayout(null);
        win.setVisible(true);
    }


    void updateDishList() {
        String tp = (String) box_type.getSelectedItem();
        box_dish.removeAllItems();
    
        for (dish d : menu_store.items) {
            if (tp.equals("All") || d.type.equalsIgnoreCase(tp)) {
                box_dish.addItem(d);
            }
        }
    }
    
    void saveOrder() {
        try(PrintWriter w = new PrintWriter(new FileWriter("orders.txt",true))){
            w.println(order_now.summary().replace("\n"," | "));
        }catch(Exception x){}
    }
}

