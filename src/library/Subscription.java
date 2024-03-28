package library;

import book.Book;

import java.util.ArrayList;
import java.util.List;

public class Subscription {
    private final List<Book> booksOnHand;

    public Subscription() {
        this.booksOnHand = new ArrayList<>();
    }

    public List<Book> getBooksOnHand() {
        return booksOnHand;
    }

    public void addToBooksOnHand(Book book) {
        booksOnHand.add(book);
    }
}