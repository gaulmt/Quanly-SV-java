package model;

import java.util.ArrayList;
import java.util.List;

public class StudentRepository {
    private final List<Student> listStudent = new ArrayList<>();

    public List<Student> addStudent(Student s) {
        listStudent.add(s);
        return listStudent;
    }

    public List<Student> getAllStudents() {
        return listStudent;
    }

}
