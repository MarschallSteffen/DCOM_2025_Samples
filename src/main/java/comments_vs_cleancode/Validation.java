package comments_vs_cleancode;

import comments_vs_cleancode.model.Book;
import comments_vs_cleancode.model.Fee;
import comments_vs_cleancode.model.User;

import java.time.Instant;
import java.util.List;

public class Validation {

    private final RentedBooksRepository rentRep;
    private final FeeRepository feeRep;

    public Validation(RentedBooksRepository rentRep, FeeRepository feeRep){
        this.rentRep = rentRep;
        this.feeRep = feeRep;
    }

    /**
     * Checks if the user is allowed to checkout a book
     *
     * @param u The User
     * @param b The book to rent
     * @return True if he's allowed to checkout
     */
    public boolean check(User u, Book b) {

        // check if the users still has an active subscription
        Instant t1 = Instant.now();
        if (t1.isAfter(u.getSubscriptionExpirationTime()) || t1.equals(u.getSubscriptionExpirationTime())) {
            return false;
        }

        // The user is allowed to rent 3 books at the same time.
        List<Book> bks = rentRep.getBooksRentedByUser(u);
        if (bks.size() > 3) {
            return false;
        }

        // check if the user has already rented the same Book
        for (Book bk : bks) {
            if (bk.getBookId().equals(b.getBookId())) {
                return false;
            }
        }

        // checks if the user has an open fee to pay
        List<Fee> bls = feeRep.getOpenFeesOfUser(u);
        for( Fee bl : bls){
            boolean af = bl.getPaymentDeadline().isAfter(t1);
            if( bl.getAmount()> 0.0 && af){
                return false;
            }
        }

        // user is allowed to rent to check out a book
        return true;
    }
}
