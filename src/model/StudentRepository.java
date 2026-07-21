package model;

import java.util.ArrayList;
import java.util.List;

public class StudentRepository {
    private final List<Student> listStudent = new ArrayList<>();

    public void addStudent(Student s) {
        listStudent.add(s);
    }



    public List<Student> getAllStudents() {
        return listStudent;
    }

}
