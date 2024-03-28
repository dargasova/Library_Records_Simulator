package book.factory;

import book.educational.EducationalLiterature;
import book.IdGenerator;
import book.fiction.EnglishFictionLiterature;
import book.fiction.FictionLiterature;
import book.fiction.RussianFictionLiterature;
import book.Constants;
import com.github.javafaker.Faker;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FictionBookFactory implements BookFactory {
    private final Faker faker = new Faker();
    private final List<String> ruFicBook = readLinesFromFile();
    private static final Random random = new Random();
    @Override
    public EducationalLiterature createEnglishEducationalLiterature() {
        throw new UnsupportedOperationException("book.factory.FictionBookFactory не поддерживает создание учебной литературы.");
    }

    @Override
    public EducationalLiterature createRussianEducationalLiterature() {
        throw new UnsupportedOperationException("book.factory.FictionBookFactory не поддерживает создание учебной литературы.");
    }


    public FictionLiterature createEnglishFictionLiterature() {
        int id = IdGenerator.generateId();
        String author = faker.book().author();
        String genre = faker.book().genre();
        String publisher = faker.book().publisher();
        String title = faker.book().title();
        String name = "№ " + id + " " + author + " " + "'" + title + "'";
//        String name = "'" + id + "'" +  " " + "'" +  author + "'" + " " + "'" + title + "'" + " " + "'" + genre + "'" + " " + "'" + publisher + "'";
        return new EnglishFictionLiterature(author, name, genre, publisher);
    }

    public FictionLiterature createRussianFictionLiterature() {
        int id = IdGenerator.generateId();
        String randomLine = ruFicBook.get(random.nextInt(ruFicBook.size()));
        String[] parts = randomLine.split(";");
        if (parts.length != 4) {
            throw new IllegalArgumentException("Неверный формат строки в файле");
        }
        String author = parts[0].trim();
        String title = parts[1].trim();
        String genre = parts[2].trim();
        int year = Integer.parseInt(parts[3].trim());
        String name = "№ " + id + " " + author + " " + "'" + title + "'";
//        String name = "'" + id + "'" + " " + "'" + title + "'" + " " + "'" + author + "'" + " " + "'" + genre + "'" + " " + "'" + year + "'";
        return new RussianFictionLiterature(author, name, genre, year);
    }

    private List<String> readLinesFromFile() {
        List<String> lines = new ArrayList<>();
        try {
            lines = Files.readAllLines(Paths.get(Constants.FICTION_RU_BOOKS_FILE));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }
}
