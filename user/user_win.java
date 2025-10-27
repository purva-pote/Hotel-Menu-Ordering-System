package user;

import javax.swing.*;

import main.RoundedButton;
import menu.*;
import java.util.*;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.awt.*;

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
        win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel root = new JPanel();
        root.setLayout(new BoxLayout(root, BoxLayout.Y_AXIS));
        root.setBorder(BorderFactory.createEmptyBorder(16,16,16,16));
        root.setBackground(new Color(255, 246, 218));

        JLabel hotelName = new JLabel("Hotel Solace");
        hotelName.setFont(new Font("SansSerif",Font.BOLD + Font.ITALIC, 58)); 
        hotelName.setForeground(new Color(118, 75, 0)); 
        hotelName.setAlignmentX(Component.CENTER_ALIGNMENT);
        root.add(hotelName);
        root.add(Box.createVerticalStrut(16));

        JPanel contentPanel = new JPanel(new BorderLayout(16,16));
        contentPanel.setBackground(new Color(255, 246, 218));

        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setPreferredSize(new Dimension(340, 0));
        int comboWidth = 220;
        leftPanel.setBackground(new Color(255, 246, 218));

        JPanel panelType = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelType.setBackground(new Color(255, 246, 218));
        JLabel lb1 = new JLabel("Type:");
        lb1.setForeground(new Color(118, 75, 0));
        lb1.setFont(new Font("SansSerif", Font.BOLD, 16));
        box_type = new JComboBox<>(new String[]{"All", "Veg", "Non-Veg", "Jain"});
        box_type.setPreferredSize(new Dimension(comboWidth, 32));
        box_type.setBackground(new Color(214, 229, 198));
        panelType.add(lb1);
        panelType.add(box_type);
        leftPanel.add(panelType);

        JPanel panelDish = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelDish.setBackground(new Color(255, 246, 218));
        JLabel lb2 = new JLabel("Dish:");
        lb2.setFont(new Font("SansSerif", Font.BOLD, 16));
        lb2.setForeground(new Color(118, 75, 0));
        box_dish = new JComboBox<>();
        box_dish.setPreferredSize(new Dimension(comboWidth, 32));
        box_dish.setBackground(new Color(214, 229, 198));
        panelDish.add(lb2);
        panelDish.add(box_dish);
        leftPanel.add(panelDish);

        JPanel panelQty = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelQty.setBackground(new Color(255, 246, 218));
        JLabel lb3 = new JLabel("Qty:");
        lb3.setForeground(new Color(118, 75, 0));
        lb3.setFont(new Font("SansSerif", Font.BOLD, 16));
        qty = new JTextField("1", 4);
        qty.setPreferredSize(new Dimension(comboWidth - 50, 28));
        qty.setBackground(new Color(214, 229, 198));
        panelQty.add(lb3);
        panelQty.add(qty);
        leftPanel.add(panelQty);

        JPanel panelTakeaway = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelTakeaway.setBackground(new Color(255, 246, 218));
        chk_takeaway = new JCheckBox("Takeaway");
        chk_takeaway.setForeground(new Color(118, 75, 0));
        chk_takeaway.setFont(new Font("SansSerif", Font.BOLD, 16));
        chk_takeaway.setBackground(new Color(255, 246, 218)); 
        chk_takeaway.setOpaque(true);
        panelTakeaway.add(chk_takeaway);
        panelTakeaway.setPreferredSize(new Dimension(comboWidth + 50, 40));
        leftPanel.add(panelTakeaway);

        leftPanel.add(Box.createVerticalStrut(16));

        JPanel panelBtn = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
        panelBtn.setBackground(new Color(255, 246, 218));
        RoundedButton b_add = new RoundedButton("Add Item", 10);
        b_add.setBackground(new Color(61,137, 58 ));
        b_add.setForeground(new Color(255, 246, 218));

        RoundedButton b_back = new RoundedButton("Back", 10);
        b_back.setBackground(new Color(61,137, 58 ));
        b_back.setForeground(new Color(255, 246, 218));

        panelBtn.add(b_add);
        panelBtn.add(b_back);
        leftPanel.add(panelBtn);

        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.setBackground(new Color(255, 246, 218));

        JPanel orderLabelPanel = new JPanel(new BorderLayout());
        orderLabelPanel.setBackground(new Color(255, 246, 218));
        JLabel orderLabel = new JLabel("Order Summary:");
        orderLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        orderLabel.setForeground(new Color(118, 75, 0));
        orderLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        orderLabelPanel.add(orderLabel, BorderLayout.WEST);
        orderLabelPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, orderLabel.getPreferredSize().height));
        rightPanel.add(orderLabelPanel);

        area_order = new JTextArea(10, 30);
        area_order.setBackground(new Color(214, 229, 198));
        area_order.setLineWrap(true);
        area_order.setEditable(false);
        JScrollPane scroll = new JScrollPane(area_order,
            JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
            JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        rightPanel.add(scroll);

        JPanel panelOrderBtn = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 8));
        panelOrderBtn.setBackground(new Color(255, 246, 218));
        panelOrderBtn.setBorder(BorderFactory.createEmptyBorder(10, 0, 0,0));

        /*RoundedButton b_viewMenu = new RoundedButton("View Menu",10);
        b_viewMenu.setBackground(new Color(61,137, 58 ));
        b_viewMenu.setForeground(new Color(255, 246, 218));*/

        RoundedButton b_show = new RoundedButton("Order Summary",10);
        b_show.setBackground(new Color(61,137, 58 ));
        b_show.setForeground(new Color(255, 246, 218));

        RoundedButton b_pay = new RoundedButton("Place Order",10);
        b_pay.setBackground(new Color(61,137, 58 ));
        b_pay.setForeground(new Color(255, 246, 218));

        //panelOrderBtn.add(b_viewMenu);
        panelOrderBtn.add(b_show);
        panelOrderBtn.add(b_pay);
        rightPanel.add(panelOrderBtn);

        contentPanel.add(leftPanel, BorderLayout.WEST);
        contentPanel.add(rightPanel, BorderLayout.CENTER);
        root.add(contentPanel);

        win.setContentPane(root);
        win.setMinimumSize(new Dimension(800, 450));
        win.setSize(1000, 600);
        win.setLocationRelativeTo(null);

        box_type.addActionListener(a -> updateDishList());
        updateDishList();

        b_add.addActionListener(e -> {
            dish d = (dish) box_dish.getSelectedItem();
            int q;
            try {
                q = Integer.parseInt(qty.getText());
                if (q <= 0) throw new Exception();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(win, "Qty must be positive integer");
                return;
            }
            boolean take = chk_takeaway.isSelected();
            order_now.add(d, q, take);
            JOptionPane.showMessageDialog(win, "Item added");
            area_order.setText(order_now.summary());
        });

        b_show.addActionListener(e -> area_order.setText(order_now.summary()));

        b_pay.addActionListener(e -> {
            saveOrder();
            win.dispose();
            new feedback_win().show();
        });

        b_back.addActionListener(e -> {
            win.dispose();
            main.main_win.main(null);
        });

        /*b_viewMenu.addActionListener(e -> {
            JOptionPane.showMessageDialog(win, "Menu view functionality not implemented.");
        });*/

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
        try(PrintWriter w = new PrintWriter(new FileWriter("orders.txt", true))) {
            w.println(order_now.summary().replace("\n", " | "));
        } catch (Exception x) {}
    }
}
