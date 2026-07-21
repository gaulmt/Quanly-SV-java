package repository;

import model.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentRepository {
    private final List<Student> listStudent = new ArrayList<>();

    public void addStudent(Student s) {
        listStudent.add(s);
    }

    public Student getStudentById(String id) {
        for (Student s : listStudent) {
            if (s.getId().equals(id)) {
                return s;
            }
        }
        return null;
    }

    public List<Student> getAllStudents() {
        return listStudent;
    }
}
