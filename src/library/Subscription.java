package library;

import book.educational.EducationalLiterature;
import book.fiction.FictionLiterature;

import java.util.ArrayList;
import java.util.List;

public class Subscription {
    private final List<EducationalLiterature> educationalBooksOnHand;
    private final List<FictionLiterature> fictionBooksOnHand;

    public Subscription() {
        this.educationalBooksOnHand = new ArrayList<>();
        this.fictionBooksOnHand = new ArrayList<>();
    }

    public List<EducationalLiterature> getEducationalBooksOnHand() {
        return educationalBooksOnHand;
    }

    public List<FictionLiterature> getFictionBooksOnHand() {
        return fictionBooksOnHand;
    }

    public void addToEducationalBooksOnHand(EducationalLiterature book) {
        educationalBooksOnHand.add(book);
    }

    public void addToFictionBooksOnHand(FictionLiterature book) {
        fictionBooksOnHand.add(book);
    }
}