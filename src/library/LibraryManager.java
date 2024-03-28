package library;

import book.factory.EducationalBookFactory;
import book.educational.EducationalLiterature;
import book.factory.FictionBookFactory;
import book.fiction.FictionLiterature;
import book.Book;
import book.factory.BookFactory;

import java.util.HashSet;
import java.util.Set;

public class LibraryManager {
    private final Library library;
    private final Librarian librarian;
    private final BookFactory educationalBookFactory;
    private final BookFactory fictionBookFactory;

    public LibraryManager(String libraryName) {
        this.library = new Library(libraryName);
        this.librarian = new Librarian(this.library);
        this.educationalBookFactory = new EducationalBookFactory();
        this.fictionBookFactory = new FictionBookFactory();
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
            EducationalLiterature eduBook = educationalBookFactory.createEnglishEducationalLiterature();
            if (isUniqueBook(eduBook)) {
                librarian.addEducationalBook(eduBook);
            } else {
                System.out.println("Дубликат учебной книги: " + eduBook.getName());
            }
        }

        for (int i = 0; i < 30; i++) {
            EducationalLiterature eduBook = educationalBookFactory.createRussianEducationalLiterature();
            if (isUniqueBook(eduBook)) {
                librarian.addEducationalBook(eduBook);
            } else {
                System.out.println("Дубликат учебной книги: " + eduBook.getName());
            }
        }
    }

    private void generateFictionBooks() {
        for (int i = 0; i < 20; i++) {
            FictionLiterature ficBook = fictionBookFactory.createEnglishFictionLiterature();
            if (isUniqueBook(ficBook)) {
                librarian.addFictionBook(ficBook);
            } else {
                System.out.println("Дубликат художественной книги: " + ficBook.getName());
            }
        }

        for (int i = 0; i < 30; i++) {
            FictionLiterature ficBook = fictionBookFactory.createRussianFictionLiterature();
            if (isUniqueBook(ficBook)) {
                librarian.addFictionBook(ficBook);
            } else {
                System.out.println("Дубликат художественной книги: " + ficBook.getName());
            }
        }
    }

    private boolean isUniqueBook(Book book) {
        Set<String> bookNames = new HashSet<>();
        for (Book existingBook : library.getEducationalBooks()) {
            bookNames.add(existingBook.getName());
        }
        for (Book existingBook : library.getFictionBooks()) {
            bookNames.add(existingBook.getName());
        }
        return !bookNames.contains(book.getName());
    }
}
