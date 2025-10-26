package menu;

import java.util.*;
import java.io.*;
import menu.dish;

public class menu_store {
    public static List<dish> items = new ArrayList<>();

    public static List<dish> getDishes() {
        return items;
    }

    public static void removeDish(String name) {
        items.removeIf(d -> d.getName().equals(name));
    }

    static String file = "menu.txt";

 
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
        }
    }

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
