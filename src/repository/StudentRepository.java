package repository;

import model.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentRepository {
    private final List<Student> list_Student = new ArrayList<>();

    public void add_Student(Student s){
        list_Student.add(s);
    }

    public Student get_Student_By_Id(String id){
        for (Student s:list_Student) {
            if (s.getId().equals(id)) {
                return s;
            }
        }
        return null;
    }

    public List<Student> get_All_Students() {
        return list_Student;
    }

}
