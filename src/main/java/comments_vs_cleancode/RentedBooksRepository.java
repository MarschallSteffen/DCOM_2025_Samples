package comments_vs_cleancode;

import comments_vs_cleancode.model.Book;
import comments_vs_cleancode.model.User;

import java.util.List;

public interface RentedBooksRepository {
    List<Book> getBooksRentedByUser(User u);
}
