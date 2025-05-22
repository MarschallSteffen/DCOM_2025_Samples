package abstraction_vs_duplication;

import abstraction_vs_duplication.model.Book;
import abstraction_vs_duplication.model.ValidationError;

import java.util.*;

/**
 * Should check that stores keep high selling children books at least once in inventory.
 */
public class ChildrenBooksValidation implements InventoryValidation {

    private final StoreInventoryService storeInventoryService;
    private final BookRepository bookRepository;

    public ChildrenBooksValidation(StoreInventoryService storeInventoryService, BookRepository bookRepository) {
        this.storeInventoryService = storeInventoryService;
        this.bookRepository = bookRepository;
    }

    @Override
    public List<ValidationError> validate(Collection<String> storeIds) {
        List<ValidationError> validationErrors = new ArrayList<>();
        List<String> books = bookRepository.findByCategory("children").stream().map(Book::getId).distinct().toList();

        for (String storeId : storeIds) {
            Map<String, List<Book>> inventory = storeInventoryService.getInventory(storeId, books);

            for (Map.Entry<String, List<Book>> entry : inventory.entrySet()) {
                if (entry.getValue().isEmpty()) {
                    validationErrors.add(new ValidationError(String.format("Store %s is missing children book %s", storeId, entry.getKey())));
                }
            }
        }
        return validationErrors;
    }

}
