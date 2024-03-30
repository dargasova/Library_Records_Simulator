package library;

import book.educational.EducationalLiterature;
import book.fiction.FictionLiterature;
import user.User;

import java.util.ArrayList;
import java.util.List;

public class Library {
    private String name;
    private final List<EducationalLiterature> educationalBooks;
    private final List<FictionLiterature> fictionBooks;
    private final List<User> users;
    private final List<Subscription> subscriptions;
    private Librarian librarian;

    public Library(String name) {
        this.name = name;
        this.educationalBooks = new ArrayList<>();
        this.fictionBooks = new ArrayList<>();
        this.users = new ArrayList<>();
        this.subscriptions = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addEducationalBook(EducationalLiterature book) {
        educationalBooks.add(book);
    }

    public void addFictionBook(FictionLiterature book) {
        fictionBooks.add(book);
    }

    public void addUser(User user) {
        users.add(user);
    }

    public List<EducationalLiterature> getEducationalBooks() {
        return educationalBooks;
    }

    public List<FictionLiterature> getFictionBooks() {
        return fictionBooks;
    }

    public List<User> getUsers() {
        return users;
    }

    public List<Subscription> getSubscriptions() {
        return subscriptions;
    }

    public Librarian getLibrarian() {
        return librarian;
    }

    public void setLibrarian(Librarian librarian) {
        this.librarian = librarian;
    }
}
