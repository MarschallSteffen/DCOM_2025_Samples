package compact_vs_readable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;

class CompactVsReadableExample1 {

    private List<String> getInStockBooks_compact(List<Book> books) {
        return books.stream()
                .map(Book::getBookId)
                .filter(Objects::nonNull)
                .filter(Predicate.not(String::isBlank))
                .filter(Predicate.not(BookService::isBookInStock))
                .collect(Collectors.toList());

    }

    private List<String> getInStockBooks_lesscompact(List<Book> books) {
        List<String> inStockBooks = new ArrayList<>();
        for (Book book : books) {
            String bookId = book.getBookId();
            if (null != bookId) {
                if (!bookId.isBlank()) {
                    if (!BookService.isBookInStock(bookId)) {
                        inStockBooks.add(bookId);
                    }
                }
            }
        }
        return inStockBooks;

    }

}
