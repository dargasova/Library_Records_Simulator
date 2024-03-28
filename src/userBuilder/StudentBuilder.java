package userBuilder;

import user.Student;

public class StudentBuilder implements UserBuilder<Student> {
    private final Student student;

    public StudentBuilder(int id) {
        student = new Student(id, "", "", "");
    }

    @Override
    public void buildName(String name) {
        student.setName(name);
    }

    @Override
    public void buildLastname(String lastname) {
        student.setLastname(lastname);
    }

    @Override
    public void buildPatronymic(String patronymic) {
        student.setPatronymic(patronymic);
    }

    @Override
    public Student getUser() {
        return student;
    }

}
