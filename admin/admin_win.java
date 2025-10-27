package admin;
import javax.swing.*;
import menu.menu_store;
import java.util.*;
import java.awt.*;

public class admin_win {
    JFrame win;
    JTextField t_name, t_price;
    JComboBox<String> t_type;
    JTextArea area_orders;

    public void show() {
        menu_store.load();
        win = new JFrame("Admin");
        win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        win.setMinimumSize(new Dimension(950, 600));

        // Main panel with padding
        JPanel mainPanel = new JPanel(new BorderLayout(15, 15));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // Heading
        JLabel heading = new JLabel("Admin Dashboard", SwingConstants.CENTER);
        heading.setFont(new Font("Arial", Font.BOLD, 28));
        mainPanel.add(heading, BorderLayout.NORTH);

        // Top panel: Add and Delete sections side by side with headings horizontally aligned
        JPanel topPanel = new JPanel(new GridLayout(1, 2, 40, 0));

        // --- Add Item panel ---
        JPanel addPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 0; gbc.gridy = 0;
        JLabel lblAdd = new JLabel("Add Item :");
        lblAdd.setFont(new Font("Arial", Font.BOLD, 18));
        addPanel.add(lblAdd, gbc);

        gbc.gridx = 1; gbc.gridy = 0;
        addPanel.add(new JLabel("Dish Name:"), gbc);
        gbc.gridx = 2; gbc.gridy = 0;
        t_name = new JTextField(13);
        addPanel.add(t_name, gbc);

        gbc.gridx = 1; gbc.gridy = 1;
        addPanel.add(new JLabel("Price:"), gbc);
        gbc.gridx = 2;
        t_price = new JTextField(13);
        addPanel.add(t_price, gbc);

        gbc.gridx = 1; gbc.gridy = 2;
        addPanel.add(new JLabel("Type:"), gbc);
        gbc.gridx = 2;
        String[] foodTypes = {"All", "Veg", "Non-Veg", "Jain"};
        t_type = new JComboBox<>(foodTypes);
        addPanel.add(t_type, gbc);

        gbc.gridx = 2; gbc.gridy = 3;
        JButton b_add = new JButton("Add");
        addPanel.add(b_add, gbc);

        
        // --- Delete Item panel ---
        JPanel deletePanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc2 = new GridBagConstraints();
        gbc2.insets = new Insets(8, 8, 8, 8);
        gbc2.anchor = GridBagConstraints.WEST;

        gbc2.gridx = 0; gbc2.gridy = 0;
        JLabel lblDelete = new JLabel("Delete Item :");
        lblDelete.setFont(new Font("Arial", Font.BOLD, 18));
        deletePanel.add(lblDelete, gbc2);

        gbc2.gridx = 1; gbc2.gridy = 0;
        deletePanel.add(new JLabel("Dish Name:"), gbc2);
        gbc2.gridx = 2;
        JComboBox<String> box_delete = new JComboBox<>();
        deletePanel.add(box_delete, gbc2);

        gbc2.gridx = 2; gbc2.gridy = 1;
        JButton b_delete = new JButton("Delete");
        deletePanel.add(b_delete, gbc2);

        // Fill delete dropdown
        menu_store.getDishes().forEach(dish -> box_delete.addItem(dish.getName()));

        // Wrap addPanel inside a FlowLayout wrapper for centered top-alignment
        JPanel addWrapper = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 10));
        addWrapper.add(addPanel);

        // Wrap deletePanel as before
        JPanel deleteWrapper = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 10));
        deleteWrapper.add(deletePanel);

        // Add wrappers to the topPanel grid layout
        topPanel.add(addWrapper);
        topPanel.add(deleteWrapper);


        // Orders and actions panel
        JPanel ordersPanel = new JPanel(new BorderLayout(20, 10));
        JPanel titlePanel = new JPanel(new BorderLayout());

        JLabel lblOrders = new JLabel("Recent Orders:");
        lblOrders.setFont(new Font("Arial", Font.BOLD, 18));
        titlePanel.add(lblOrders, BorderLayout.WEST);

        JButton b_r = new JButton("Refresh Orders");
        titlePanel.add(b_r, BorderLayout.EAST);

        area_orders = new JTextArea(8, 50);
        area_orders.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(area_orders,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        JPanel bottomActions = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton b_back = new JButton("Back");
        bottomActions.add(b_back);

        ordersPanel.add(titlePanel, BorderLayout.NORTH);
        ordersPanel.add(scrollPane, BorderLayout.CENTER);
        ordersPanel.add(bottomActions, BorderLayout.SOUTH);

        // --- Assemble main window ---
        mainPanel.add(topPanel, BorderLayout.CENTER);
        mainPanel.add(ordersPanel, BorderLayout.SOUTH);

        win.setContentPane(mainPanel);
        win.setLocationRelativeTo(null);
        win.setVisible(true);

        // --- Event handling ---
        b_delete.addActionListener(e -> {
            String selectedItem = (String) box_delete.getSelectedItem();
            if (selectedItem != null) {
                menu_store.removeDish(selectedItem);
                menu_store.load();
                JOptionPane.showMessageDialog(win, "Item deleted successfully!");
                box_delete.removeAllItems();
                menu_store.getDishes().forEach(dish -> box_delete.addItem(dish.getName()));
            } else {
                JOptionPane.showMessageDialog(win, "No item selected to delete.");
            }
        });

        b_add.addActionListener(e -> {
            String n = t_name.getText();
            int pr;
            try {
                pr = Integer.parseInt(t_price.getText());
                if (pr <= 0 || n.length() == 0) throw new Exception();
            } catch (Exception x) {
                JOptionPane.showMessageDialog(win, "Bad input");
                return;
            }
            menu_store.addDish(n, pr, (String) t_type.getSelectedItem());
            menu_store.load();
            JOptionPane.showMessageDialog(win, "Menu updated!");
        });

        b_r.addActionListener(e -> loadOrders());

        b_back.addActionListener(e -> {
            win.dispose();
            main.main_win.main(null);
        });

        loadOrders();
    }

    void loadOrders() {
        try (Scanner s = new Scanner(new java.io.File("orders.txt"))) {
            StringBuilder sb = new StringBuilder();
            while (s.hasNextLine()) sb.append(s.nextLine()).append("\n");
            area_orders.setText(sb.toString());
        } catch (Exception e) {
            area_orders.setText("no orders yet");
        }
    }
}
