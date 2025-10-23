package menu;

public class dish {
    public String name;
    public int price;
    public String type;
    public dish(String name, int price, String type) {
        this.name = name;
        this.price = price;
        this.type = type; // "veg", "nonveg", "jain"
    }
    public String toString() {
        return name + " (" + type + ") - ₹" + price;
    }
}
