package userBuilder;

import book.Constants;
import com.github.javafaker.Faker;
import user.User;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Director {
    private UserBuilder<?> builder;

    public void setBuilder(UserBuilder<?> builder) {
        this.builder = builder;
    }

    public <T extends User> T buildUser(String name, String lastname, String patronymic) {
        builder.buildName(name);
        builder.buildLastname(lastname);
        builder.buildPatronymic(patronymic);
        return (T) builder.getUser();
    }

    public List<User> generateStudents(int count) {
        List<User> students = new ArrayList<>();
        Faker faker = new Faker(new Locale("ru"));

        for (int i = 0; i < count; i++) {
            String firstName = "";
            String lastName = "";
            String middleName = "";

            while (middleName.isEmpty()) {
                String fullName = faker.name().fullName();
                String[] parts = fullName.split(" ");
                if (parts.length >= 3) {
                    firstName = parts[0];
                    lastName = parts[1];
                    middleName = parts[2];
                }
            }

            UserBuilder<?> studentBuilder = new StudentBuilder(i + 1);
            setBuilder(studentBuilder);
            User student = buildUser(firstName, lastName, middleName);
            students.add(student);
        }

        return students;
    }

    public List<User> generateTeachers(int count) {
        List<User> teachers = new ArrayList<>();
        try {
            File file = new File(Constants.TEACHERS_FILE);
            Scanner scanner = new Scanner(file);
            List<String> names = new ArrayList<>();
            List<String> firstNames = new ArrayList<>();
            List<String> patronymics = new ArrayList<>();

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split("\\s+");
                if (parts.length >= 3) {
                    names.add(parts[0]);
                    firstNames.add(parts[1]);
                    patronymics.add(parts[2]);
                }
            }
            scanner.close();

            Collections.shuffle(names);
            Collections.shuffle(firstNames);
            Collections.shuffle(patronymics);

            UserBuilder<?> teacherBuilder = new TeacherBuilder("Кафедра автоматики (№ 2)\n");
            setBuilder(teacherBuilder);
            for (int i = 0; i < count && i < names.size() && i < firstNames.size() && i < patronymics.size(); i++) {
                User teacher = buildUser(names.get(i), firstNames.get(i), patronymics.get(i));
                teachers.add(teacher);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return teachers;
    }
}