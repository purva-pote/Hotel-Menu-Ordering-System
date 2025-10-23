package menu;

import java.util.*;
import java.io.*;

public class menu_store {
    public static List<dish> items = new ArrayList<>();
    static String file = "menu.txt";

    // Load menu from disk
    public static void load() {
        items.clear();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String l;
            while ((l = br.readLine()) != null) {
                String[] t = l.split(",");
                if (t.length == 3)
                    items.add(new dish(t[0], Integer.parseInt(t[1]), t[2]));
            }
        } catch (Exception ex) {
            // ignore file not found on first run
        }
    }

    // Save menu to disk
    public static void save() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(file))) {
            for (dish d : items) {
                pw.println(d.name + "," + d.price + "," + d.type);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // Add or edit dish
    public static void addDish(String name, int price, String type) {
        for (dish d : items) {
            if (d.name.equalsIgnoreCase(name)) {
                d.price = price;
                d.type = type;
                save();
                return;
            }
        }
        items.add(new dish(name, price, type));
        save();
    }
}
