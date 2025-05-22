package abstraction_vs_duplication;

import abstraction_vs_duplication.model.Book;
import abstraction_vs_duplication.model.ValidationError;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;


/**
 * Should check that stores keep low selling programming books exactly once in inventory.
 */
public class ProgrammingBooksValidation implements InventoryValidation {

    private final StoreInventoryService storeInventoryService;
    private final BookRepository bookRepository;

    public ProgrammingBooksValidation(StoreInventoryService storeInventoryService, BookRepository bookRepository) {
        this.storeInventoryService = storeInventoryService;
        this.bookRepository = bookRepository;
    }

    public List<ValidationError> validate(Collection<String> storeIds) {
        List<ValidationError> validationErrors = new ArrayList<>();
        List<String> books = bookRepository.findByCategory("programming").stream().map(Book::getId).distinct().toList();

        for( String storeId : storeIds){
            Map<String, List<Book>> inventory = storeInventoryService.getInventory( storeId, books );

            for( Map.Entry<String, List<Book>> entry : inventory.entrySet()){
                if( entry.getValue().isEmpty()){
                    validationErrors.add(new ValidationError(String.format("Store %s is programming rare book %s in inventory", storeId, entry.getKey())));
                }
                else if( entry.getValue().size() > 1){
                    validationErrors.add(new ValidationError(String.format("Store %s is having programming book %s multiple times in inventory", storeId, entry.getKey())));
                }
            }
        }
        return validationErrors;
    }


}