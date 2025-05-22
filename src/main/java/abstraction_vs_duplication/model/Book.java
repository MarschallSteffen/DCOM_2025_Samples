package abstraction_vs_duplication.model;

public class Book {
    private final String id;
    private final String inventoryId;
    private final String title;
    private final String category;

    public Book(String id, String inventoryId, String title, String category) {
        this.id = id;
        this.inventoryId = inventoryId;
        this.title = title;
        this.category = category;
    }

    public String getId() {
        return id;
    }

    public String getInventoryId() {
        return inventoryId;
    }

    public String getTitle() {
        return title;
    }

    public String getCategory() {
        return this.category;
    }
}
