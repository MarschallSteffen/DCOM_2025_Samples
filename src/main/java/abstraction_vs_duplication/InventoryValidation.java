package abstraction_vs_duplication;

import abstraction_vs_duplication.model.ValidationError;

import java.util.Collection;
import java.util.List;

public interface InventoryValidation {
    List<ValidationError> validate(Collection<String> storeIds);
}
