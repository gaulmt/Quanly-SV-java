package logic;

import model.ScholarshipPackage;
import model.Student;
import model.StudentRepository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Logic {

    // xét xem sv có đủ điều kiện nhận học bổng hay không
    public List<Student> consideringScholarships(List<Student> students, List<ScholarshipPackage> list, String classRoom) {

        ScholarshipPackage excellentScholarship = list.get(0);
        ScholarshipPackage goodScholarship = list.get(1);
        ScholarshipPackage fairlyGoodScholarship = list.get(2);

        List<Student> studentClassRoom = students.stream()
                .filter(student -> student.getClassRoom().equalsIgnoreCase(classRoom))
                .collect(Collectors.toList());

        studentClassRoom.sort(
                Comparator.comparing(Student::getGpa, Comparator.reverseOrder())
                        .thenComparing(Student::getTrainingPoint, Comparator.reverseOrder())
                        .thenComparing(Student::getCredits, Comparator.reverseOrder())
        );

        int excellentSlot = excellentScholarship.getQuota();
        int goodSlot = goodScholarship.getQuota();
        int fairlyGoodSlot = fairlyGoodScholarship.getQuota();

        for (Student s : studentClassRoom) {
            double gpa = s.getGpa();
            int trainingPoint = s.getTrainingPoint();
            int credit = s.getCredits();

            if (
                    gpa >= excellentScholarship.getMinGpa()
                            && trainingPoint >= excellentScholarship.getMinTrainingPoint()
                            && excellentSlot != 0
                            && credit >= 15) {

                excellentSlot--;
                s.setScholarshipName(excellentScholarship.getName());

            } else if (
                    gpa >= goodScholarship.getMinGpa()
                            && trainingPoint >= goodScholarship.getMinTrainingPoint()
                            && goodSlot != 0
                            && credit >= 15) {
                goodSlot--;
                s.setScholarshipName(goodScholarship.getName());

            } else if (
                    gpa >= fairlyGoodScholarship.getMinGpa()
                            && trainingPoint >= fairlyGoodScholarship.getMinTrainingPoint()
                            && fairlyGoodSlot != 0
                            && credit >= 15) {

                fairlyGoodSlot--;
                s.setScholarshipName(fairlyGoodScholarship.getName());

            } else {
                s.setScholarshipName("chưa có học bổng");
            }
        }
        return studentClassRoom;
    }

    // sắp xếp theo mssv
    public void studentSortById(List<Student> students) {
        students.sort(Comparator.comparing(Student::getId));
    }

    // Xóa sinh viên theo ID
    public int deleteStudentById(StudentRepository repo, String id) {

        List<Student> students = repo.getAllStudents();

        for (int i = 0; i < students.size(); i++) {
            Student s = students.get(i);

            if (s.getId().equals(id)) {
                students.remove(i);
                return 1;  // tìm thấy
            }
        }
        return 0; // không tìm thấy
    }

    // tìm sv theo ID
    public Student findStudentById(StudentRepository repo, String id) {
        List<Student> students = repo.getAllStudents();

        for (int i = 0; i < students.size(); i++) {
            Student s = students.get(i);
            if (s.getId().equals(id)) {
                return s; // tìm thấy -> trả về sinh viên
            }
        }
        return null;
    }
}
