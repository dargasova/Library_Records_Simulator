package user;

import department.Department;

public class Teacher extends User {

    public Teacher(int id, String name, String lastname, String patronymic, Department department) {
        super(id, name, lastname, patronymic);
    }
}