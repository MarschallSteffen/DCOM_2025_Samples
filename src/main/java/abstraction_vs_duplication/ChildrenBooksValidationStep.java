package abstraction_vs_duplication;

import abstraction_vs_duplication.model.Book;
import abstraction_vs_duplication.model.ValidationError;

import java.util.*;

/**
 * Should check that stores keep high selling children books at least once in inventory.
 */
public class ChildrenBooksValidationStep extends AbstractBookInventoryValidationStep {

    private final BookRepository bookRepository;

    public ChildrenBooksValidationStep(StoreInventoryService storeInventoryService, BookRepository  bookRepository) {
        super(storeInventoryService);
        this.bookRepository = bookRepository;
    }

    @Override
    protected List<String> getBookIdsForValidation() {
        return bookRepository.findByCategory("children").stream().map( Book::getId ).distinct().toList();
    }

    @Override
    protected ValidationError buildDuplicateBookError(String storeId, String bookId) {
        // For children’s books, we don’t care about duplicates — skip error.
        return null;
    }

    @Override
    protected ValidationError buildMissingBookError(String storeId, String bookId) {
        return new ValidationError(String.format("Store %s is missing children book %s", storeId, bookId));
    }

    @Override
    public List<ValidationError> validate(Collection<String> storeIds) {
        List<ValidationError> errors = super.validate(storeIds);
        errors.removeIf(Objects::isNull);
        return errors;
    }
}
