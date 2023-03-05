package Model;

import java.util.ArrayList;

public class Menu {
    private String header;
    private ArrayList<Item> items;

    public Menu(String header) {
        this.header = header;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "header='" + header + '\'' +
                ", items=" + items +
                '}';
    }

    public String getHeader() {
        return header;
    }

    public ArrayList<Item> getItems() {
        return items;
    }
}
