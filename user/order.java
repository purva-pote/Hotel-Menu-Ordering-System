package user;

import menu.dish;
import java.util.*;

public class order {
    public static class item_line {
        public dish d;
        public int qty;
        public boolean takeaway;
        item_line(dish d, int qty, boolean takeaway) {
            this.d = d;
            this.qty = qty;
            this.takeaway = takeaway;
        }
        public String row() {
            return d.name + " x" + qty + (takeaway ? " (Takeaway)" : " (Dine-In)") + ": ₹" + (d.price * qty);
        }
    }

    public List<item_line> list = new ArrayList<>();
    public void add(dish d, int qty, boolean takeaway) {
        list.add(new item_line(d, qty, takeaway));
    }
    public int total() {
        int t=0;
        for (item_line l : list)
            t += l.d.price * l.qty;
        return t;
    }
    public String summary() {
        StringBuilder sb = new StringBuilder();
        for (item_line l : list)
            sb.append(l.row() + "\n");
        sb.append("Total: ₹" + total());
        return sb.toString();
    }
}
