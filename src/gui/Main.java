package gui;
import book.Book;
import book.Constants;
import library.Librarian;
import library.Library;
import library.LibraryManager;
import user.*;
import userBuilder.Director;
import userBuilder.TeacherBuilder;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        LibraryManager libraryManager = new LibraryManager("Моя библиотека");

        libraryManager.generateBooks();

        Library library = libraryManager.getLibrary();
        Librarian librarian = libraryManager.getLibrarian();

        generateTeachers(librarian);
        generateStudents(librarian);

        for (User user : library.getUsers()) {
            takeBooksFromLibrary(user, librarian, library);
        }

        for (User user : library.getUsers()) {
            System.out.println(user.getName() + " взял(а) следующие книги:");
            for (Book book : user.getSubscription().getBooksOnHand()) {
                System.out.println(book.getName());
            }
        }
    }

    static void generateTeachers(Librarian librarian) {
        Director director = new Director();
        List<User> teachers = director.generateTeachers(15);
        for (User teacher : teachers) {
            librarian.addUserToLibrary(teacher);
        }
    }

    static void generateStudents(Librarian librarian) {
        Director director = new Director();
        List<User> students = director.generateStudents(15);
        for (User student : students) {
            librarian.addUserToLibrary(student);
        }
    }

    static void takeBooksFromLibrary(User user, Librarian librarian, Library library) {
        int booksToTake = new Random().nextInt(8) + 3;

        Set<Book> booksTaken = new HashSet<>();

        for (int i = 0; i < booksToTake; i++) {
            Book book;
            do {
                book = getRandomBook(library);
            } while (booksTaken.contains(book));
            librarian.issueBookToUser(user, book);
            booksTaken.add(book);
        }
    }


    private static Book getRandomBook(Library library) {
        List<Book> allBooks = new ArrayList<>();
        allBooks.addAll(library.getEducationalBooks());
        allBooks.addAll(library.getFictionBooks());

        Random random = new Random();
        return allBooks.get(random.nextInt(allBooks.size()));
    }
}