package abstraction_vs_duplication;

import abstraction_vs_duplication.model.Book;
import abstraction_vs_duplication.model.ValidationError;

import java.util.List;


/**
 * Should check that stores keep low selling programming books exactly once in inventory.
 */
public class ProgrammingBooksValidationStep extends AbstractBookInventoryValidationStep{

    private final BookRepository bookRepository;

    public ProgrammingBooksValidationStep(StoreInventoryService storeInventoryService, BookRepository bookRepository) {
        super(storeInventoryService);
        this.bookRepository = bookRepository;
    }

    @Override
    protected List<String> getBookIdsForValidation() {
        return bookRepository.findByCategory("programming").stream().map( Book::getId).distinct().toList();
    }

    @Override
    protected ValidationError buildDuplicateBookError(String storeId, String bookId) {
        return new ValidationError(String.format("Store %s is having programming book %s multiple times in inventory", storeId, bookId));
    }

    @Override
    protected ValidationError buildMissingBookError(String storeId, String bookId) {
        return new ValidationError(String.format("Store %s is programming rare book %s in inventory", storeId, bookId));
    }
}