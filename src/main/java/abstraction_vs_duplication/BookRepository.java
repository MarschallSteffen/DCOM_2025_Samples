package abstraction_vs_duplication;

import abstraction_vs_duplication.model.Book;

import java.util.List;

public interface BookRepository{
    List<Book> findByCategory(String category);
}
