package library;

import book.educational.EducationalLiterature;
import book.fiction.FictionLiterature;
import book.factory.BookFactory;

import java.util.HashSet;
import java.util.Set;

public class Generator {
    private final Library library;
    private final Librarian librarian;
    private final BookFactory educationalBookFactory;
    private final BookFactory fictionBookFactory;

    public Generator(String libraryName, BookFactory educationalBookFactory, BookFactory fictionBookFactory) {
        this.library = new Library(libraryName);
        this.librarian = new Librarian(this.library);
        this.educationalBookFactory = educationalBookFactory;
        this.fictionBookFactory = fictionBookFactory;
    }

    public Library getLibrary() {
        return library;
    }

    public Librarian getLibrarian() {
        return librarian;
    }

    public void generateBooks() {
        generateEducationalBooks();
        generateFictionBooks();
    }

    private void generateEducationalBooks() {
        for (int i = 0; i < 20; i++) {
            EducationalLiterature eduBook = (EducationalLiterature) educationalBookFactory.createEnglishLiterature();
            if (isUniqueBook(eduBook)) {
                librarian.addEducationalBook(eduBook);
            } else {
                System.out.println("Дубликат учебной книги: " + eduBook.getName());
            }
        }

        for (int i = 0; i < 30; i++) {
            EducationalLiterature eduBook = (EducationalLiterature) educationalBookFactory.createRussianLiterature();
            if (isUniqueBook(eduBook)) {
                librarian.addEducationalBook(eduBook);
            } else {
                System.out.println("Дубликат учебной книги: " + eduBook.getName());
            }
        }
    }

    private void generateFictionBooks() {
        for (int i = 0; i < 20; i++) {
            FictionLiterature ficBook = (FictionLiterature) fictionBookFactory.createEnglishLiterature();
            if (isUniqueBook(ficBook)) {
                librarian.addFictionBook(ficBook);
            } else {
                System.out.println("Дубликат художественной книги: " + ficBook.getName());
            }
        }

        for (int i = 0; i < 30; i++) {
            FictionLiterature ficBook = (FictionLiterature) fictionBookFactory.createRussianLiterature();
            if (isUniqueBook(ficBook)) {
                librarian.addFictionBook(ficBook);
            } else {
                System.out.println("Дубликат художественной книги: " + ficBook.getName());
            }
        }
    }

    private boolean isUniqueBook(Object book) {
        Set<String> bookNames = new HashSet<>();
        for (Object existingBook : library.getEducationalBooks()) {
            bookNames.add(getName(existingBook));
        }
        for (Object existingBook : library.getFictionBooks()) {
            bookNames.add(getName(existingBook));
        }
        return !bookNames.contains(getName(book));
    }

    private String getName(Object book) {
        if (book instanceof EducationalLiterature) {
            return ((EducationalLiterature) book).getName();
        } else if (book instanceof FictionLiterature) {
            return ((FictionLiterature) book).getName();
        }
        return null;
    }
}