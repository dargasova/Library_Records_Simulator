package userBuilder;

import department.Department;
import user.Teacher;

public class TeacherBuilder implements UserBuilder<Teacher> {
    private Teacher teacher;
    private final String departmentName;

    public TeacherBuilder(String departmentName) {
        this.departmentName = departmentName;
    }

    @Override
    public void buildName(String name) {
        teacher = new Teacher(0, name, "", "", new Department(departmentName));
    }

    @Override
    public void buildLastname(String lastname) {
        teacher.setLastname(lastname);
    }

    @Override
    public void buildPatronymic(String patronymic) {
        teacher.setPatronymic(patronymic);
    }

    @Override
    public Teacher getUser() {
        return teacher;
    }
}
