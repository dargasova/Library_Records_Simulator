package library;

import book.educational.EducationalLiterature;
import book.fiction.FictionLiterature;
import book.Book;
import user.User;

public class Librarian {
    private final Library library;

    public Librarian(Library library) {
        this.library = library;
    }

    public void addUserToLibrary(User user) {
        library.addUser(user);
    }

    public void addEducationalBook(EducationalLiterature book) {
        library.addEducationalBook(book);
    }

    public void addFictionBook(FictionLiterature book) {
        library.addFictionBook(book);
    }

    public void issueBookToUser(User user, Book book) {
        Subscription subscription = findSubscriptionContainingBook(book);
        if (subscription == null) {
            subscription = user.getSubscription();
            subscription.addToBooksOnHand(book);
        }
    }

    private Subscription findSubscriptionContainingBook(Book book) {
        for (Subscription subscription : library.getSubscriptions()) {
            if (subscription.getBooksOnHand().contains(book)) {
                return subscription;
            }
        }
        return null;
    }
}