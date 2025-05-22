package compact_vs_readable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;

class CompactVsReadableExample1 {

    private List<String> getBooksInStock(List<Book> books) {
        List<String> booksInStock = new ArrayList<>();
        for (Book book : books) {
            String bookId = book.getBookId();
            if (null != bookId) {
                if (!bookId.isBlank()) {
                    if (!BookService.isBookInStock(bookId)) {
                        booksInStock.add(bookId);
                    }
                }
            }
        }
        return booksInStock;

    }

}
