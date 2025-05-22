package abstraction_vs_duplication;

import abstraction_vs_duplication.model.Book;

import java.util.List;
import java.util.Map;

public interface StoreInventoryService {
    Map<String, List<Book>> getInventory(String storeId, List<String> bookIds);
}
