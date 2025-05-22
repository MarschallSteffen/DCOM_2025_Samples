package comments_vs_cleancode.model;

public class Book {
    private final String bookId;
    private final String inventoryId;
    private final String title;

    public Book(String bookId, String inventoryId, String title) {
        this.bookId = bookId;
        this.inventoryId = inventoryId;
        this.title = title;
    }

    public String getBookId() {
        return bookId;
    }

    public String getInventoryId() {
        return inventoryId;
    }

    public String getTitle() {
        return title;
    }
}
