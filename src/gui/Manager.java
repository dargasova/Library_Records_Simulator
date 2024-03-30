package gui;
import book.educational.EducationalLiterature;
import book.factory.EducationalBookFactory;
import book.factory.FictionBookFactory;
import book.fiction.FictionLiterature;
import library.Librarian;
import library.Library;
import library.Generator;
import userBuilder.Director;
import user.User;


import java.util.*;

public class Manager {
    public static Library initializeLibrary() {
        EducationalBookFactory educationalBookFactory = new EducationalBookFactory();
        FictionBookFactory fictionBookFactory = new FictionBookFactory();

        Generator generator = new Generator("Моя библиотека", educationalBookFactory, fictionBookFactory);
        generator.generateBooks();

        Library library = generator.getLibrary();
        Librarian librarian = generator.getLibrarian();
        library.setLibrarian(librarian);

        generateTeachers(library);
        generateStudents(library);

        return library;
    }

    public static void generateTeachers(Library library) {
        Director director = new Director();
        List<User> teachers = director.generateTeachers(15);
        for (User teacher : teachers) {
            library.getLibrarian().addUserToLibrary(teacher);
        }
    }

    public static void generateStudents(Library library) {
        Director director = new Director();
        List<User> students = director.generateStudents(15);
        for (User student : students) {
            library.getLibrarian().addUserToLibrary(student);
        }
    }

    public static void takeBooksFromLibrary(User user, Library library) {
        int booksToTake = new Random().nextInt(8) + 3;

        Set<EducationalLiterature> educationalBooksTaken = new HashSet<>();
        Set<FictionLiterature> fictionBooksTaken = new HashSet<>();

        for (int i = 0; i < booksToTake; i++) {
            if (new Random().nextBoolean()) {
                EducationalLiterature eduBook;
                do {
                    eduBook = getRandomEducationalBook(library);
                } while (educationalBooksTaken.contains(eduBook));
                library.getLibrarian().issueEducationalBookToUser(user, eduBook);
                educationalBooksTaken.add(eduBook);
            } else {
                FictionLiterature ficBook;
                do {
                    ficBook = getRandomFictionBook(library);
                } while (fictionBooksTaken.contains(ficBook));
                library.getLibrarian().issueFictionBookToUser(user, ficBook);
                fictionBooksTaken.add(ficBook);
            }
        }
    }

    private static EducationalLiterature getRandomEducationalBook(Library library) {
        List<EducationalLiterature> availableBooks = library.getEducationalBooks();
        return availableBooks.get(new Random().nextInt(availableBooks.size()));
    }

    private static FictionLiterature getRandomFictionBook(Library library) {
        List<FictionLiterature> availableBooks = library.getFictionBooks();
        return availableBooks.get(new Random().nextInt(availableBooks.size()));
    }

}