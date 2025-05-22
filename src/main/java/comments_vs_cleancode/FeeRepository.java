package comments_vs_cleancode;

import comments_vs_cleancode.model.Fee;
import comments_vs_cleancode.model.User;

import java.util.List;

public interface FeeRepository {
    List<Fee> getOpenFeesOfUser(User u);
}
