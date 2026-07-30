package Repository;

import Repository.Data.DataLoader;
import Repository.Data.DataSaver;
import Repository.Model.Student;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class StudentManager {
    private final List<Student> listStudent = new ArrayList<>();
    private final DataSaver saveData = new DataSaver();


    public StudentManager() {
        DataLoader loadData = new DataLoader();
        String storagePath = "C:\\Users\\Admin\\OneDrive\\Documents\\Java\\Quanly-SV-java\\src\\Repository\\Data\\Students.csv";
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

    public List<Student> deleteAStudent(String Id) {
        for (int i = 0; i < listStudent.size(); i++) {
            if (listStudent.get(i).getId().equals(Id)) {
                listStudent.remove(i);
                break;
            }
        }
        saveData.dataSaver(listStudent);
        return listStudent;
    }

    public List<Student> studentSortById(List<Student> students) {
        students.sort(Comparator.comparing(Student::getId));
        saveData.dataSaver(students);
        return students;
    }


    public Student findStudentById(StudentManager repo, String id) {
        List<Student> students = repo.getAllStudents();

        for (Student s : students) {
            if (s.getId().equals(id)) {
                return s;
            }
        }
        return null;
    }
}
