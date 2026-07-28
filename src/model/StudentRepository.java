package model;

import System.Data.LoadData;
import System.Data.SaveData;

import java.util.ArrayList;
import java.util.List;

public class StudentRepository {
    private final List<Student> listStudent = new ArrayList<>();
    private final SaveData saveData = new SaveData();
    private final LoadData loadData = new LoadData();
    private final String storagePath = "C:\\Users\\Admin\\OneDrive\\Documents\\Java\\Quanly-SV-java\\src\\System\\Data\\Students.csv";


    public StudentRepository() {
        listStudent.addAll(loadData.load(storagePath));
    }

    public List<Student> addStudent(Student s) {
        listStudent.add(s);
        saveData.dataSaver(listStudent);
        return listStudent;
    }

    public List<Student> getAllStudents() {
        return listStudent;
    }

    public List<Student> updateStudent(String oldId, Student student) {
        for (int i = 0; i < listStudent.size(); i++) {
            if (listStudent.get(i).getId().equals(oldId)) {
                listStudent.set(i, student);
                break;
            }
        }
        saveData.dataSaver(listStudent);
        return listStudent;
    }

}
