package book.factory;

import book.educational.EducationalLiterature;
import book.educational.EnglishEducationalLiterature;
import book.educational.RussianEducationalLiterature;
import book.fiction.FictionLiterature;
import book.IdGenerator;
import book.Constants;
import com.github.javafaker.Faker;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EducationalBookFactory implements BookFactory {
    private final Faker faker = new Faker();

    private final List<String> enDisciplines = readLinesFromFile(Constants.EDUCATIONAL_EN_DISCIPLINES_FILE);
    private final List<String> types = readLinesFromFile(Constants.EDUCATIONAL_TYPES_FILE);
    private final List<String> ruDisciplines = readLinesFromFile(Constants.EDUCATIONAL_RU_DISCIPLINES_FILE);
    private final List<String> ruAuthors = readLinesFromFile(Constants.EDUCATIONAL_RU_AUTHORS_FILE);

    private final Random random = new Random();

    private List<String> readLinesFromFile(String filename) {
        List<String> lines = new ArrayList<>();
        try {
            lines = Files.readAllLines(Paths.get(filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }


    @Override
    public EducationalLiterature createEnglishEducationalLiterature() {
        int id = IdGenerator.generateId();
        String author = faker.book().author();
        String discipline = enDisciplines.get(random.nextInt(enDisciplines.size()));
        String university = faker.educator().university();
        String level = faker.bool().bool() ? "Bachelor course" : "Master's course";
        String name = "№ " + id + " " + author + " " + "'" + discipline + "'";
        return new EnglishEducationalLiterature(author, name, discipline, level, university);
    }

    public EducationalLiterature createRussianEducationalLiterature() {
        int id = IdGenerator.generateId();
        String discipline = ruDisciplines.get(random.nextInt(ruDisciplines.size()));
        String author = ruAuthors.get(random.nextInt(ruAuthors.size()));
        String type = types.get(random.nextInt(types.size()));
        String bookName = "№ " + id + " " + author + " " + type + " " + "'" + discipline + "'";
        return new RussianEducationalLiterature(author, bookName, discipline, type);
    }

    @Override
    public FictionLiterature createEnglishFictionLiterature() {
        throw new UnsupportedOperationException("Fiction.EducationalBookFactory не поддерживает создание художественной литературы.");
    }

    @Override
    public FictionLiterature createRussianFictionLiterature() {
        throw new UnsupportedOperationException("Fiction.EducationalBookFactory не поддерживает создание художественной литературы.");
    }
}

