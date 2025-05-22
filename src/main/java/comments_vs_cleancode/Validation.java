package comments_vs_cleancode;

import comments_vs_cleancode.model.Book;
import comments_vs_cleancode.model.Fee;
import comments_vs_cleancode.model.User;

import java.time.Instant;
import java.util.List;

public class Validation {
    public static final int MAX_NUMBER_OF_RENTALS_AT_SAME_TIME = 3;

    private final RentedBooksRepository rentedBooksRepository;
    private final FeeRepository feeRepository;

    public Validation(RentedBooksRepository rentedBooksRepository, FeeRepository feeRepository){
        this.rentedBooksRepository = rentedBooksRepository;
        this.feeRepository = feeRepository;
    }

    public boolean isUserAllowedToRentBook(User user, Book bookToRent) {
        return isUserStillSubscribed(user) &&
                isUserAllowedToRentThisBook(user, bookToRent) &&
                !doesUserHaveAnyUnpaidExpiredFees(user);
    }

    private boolean isUserStillSubscribed(User user){
        return Instant.now().isBefore( user.getSubscriptionExpirationTime() );
    }

    private boolean isUserAllowedToRentThisBook(User user, Book bookToRent){
        List<Book> booksRentedByUser = rentedBooksRepository.getBooksRentedByUser(user);

        return !isUserRentingMaxNumberOfBooks(booksRentedByUser) &&
                !isUserAlreadyRentingIdenticalBook(bookToRent, booksRentedByUser);
    }

    private boolean isUserRentingMaxNumberOfBooks(List<Book> rentedBooks){
        return rentedBooks.size() < MAX_NUMBER_OF_RENTALS_AT_SAME_TIME;
    }

    private boolean isUserAlreadyRentingIdenticalBook(Book bookToRent, List<Book> rentedBooks){
        return rentedBooks.stream().anyMatch( book -> book.getBookId().equals(bookToRent.getBookId()));
    }

    private boolean doesUserHaveAnyUnpaidExpiredFees(User user){
        List<Fee> fees = feeRepository.getOpenFeesOfUser(user);
        return fees.stream().anyMatch(this::isFeeExpired);
    }


    private boolean isFeeExpired( Fee fee ){
        return fee.getAmount() > 0.0 && fee.getPaymentDeadline().isAfter( Instant.now() );
    }

}