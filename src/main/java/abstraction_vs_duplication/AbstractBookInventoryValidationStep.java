package abstraction_vs_duplication;

import abstraction_vs_duplication.model.Book;
import abstraction_vs_duplication.model.ValidationError;

import java.util.*;

public abstract class AbstractBookInventoryValidationStep implements InventoryValidation {

    protected final StoreInventoryService storeInventoryService;

    AbstractBookInventoryValidationStep(StoreInventoryService storeInventoryService){
        this.storeInventoryService = storeInventoryService;
    }

    public List<ValidationError> validate(Collection<String> storeIds) {
        List<ValidationError> validationErrors = new ArrayList<>();
        List<String> books = getBookIdsForValidation();

        for( String storeId : storeIds){
            Map<String, List<Book>> inventory = storeInventoryService.getInventory( storeId, books );

            for( Map.Entry<String, List<Book>> entry : inventory.entrySet()){
                if( entry.getValue().isEmpty()){
                    validationErrors.add(buildMissingBookError(storeId, entry.getKey()));
                }
                else if( entry.getValue().size() > 1){
                    validationErrors.add( buildDuplicateBookError(storeId, entry.getKey()) );
                }
            }
        }
        return validationErrors;
    }

    protected abstract List<String> getBookIdsForValidation();

    protected abstract ValidationError buildDuplicateBookError(String storeId, String bookId);

    protected abstract ValidationError buildMissingBookError(String storeId, String bookId);
}