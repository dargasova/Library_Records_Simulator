package department;

import user.Teacher;

import java.util.ArrayList;
import java.util.List;

public class Department {
    private String name;

    public Department(String name) {
        this.name = name;
        List<Teacher> teachers = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
