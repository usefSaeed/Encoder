package Model;

public class Item {
    private String id;
    private String label;

    public Item(String id, String label) {
        this.id = id;
        this.label = label;
    }

    public Item(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }
}
