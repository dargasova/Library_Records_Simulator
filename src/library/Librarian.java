package library;

import book.educational.EducationalLiterature;
import book.fiction.FictionLiterature;
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

    public void issueEducationalBookToUser(User user, EducationalLiterature book) {
        Subscription subscription = findSubscriptionContainingEducationalBook(book);
        if (subscription == null) {
            subscription = user.getSubscription();
            subscription.addToEducationalBooksOnHand(book);
        }
    }

    public void issueFictionBookToUser(User user, FictionLiterature book) {
        Subscription subscription = findSubscriptionContainingFictionBook(book);
        if (subscription == null) {
            subscription = user.getSubscription();
            subscription.addToFictionBooksOnHand(book);
        }
    }

    private Subscription findSubscriptionContainingEducationalBook(EducationalLiterature book) {
        for (Subscription subscription : library.getSubscriptions()) {
            if (subscription.getEducationalBooksOnHand().contains(book)) {
                return subscription;
            }
        }
        return null;
    }

    private Subscription findSubscriptionContainingFictionBook(FictionLiterature book) {
        for (Subscription subscription : library.getSubscriptions()) {
            if (subscription.getFictionBooksOnHand().contains(book)) {
                return subscription;
            }
        }
        return null;
    }
}