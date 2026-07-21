package logic;

import model.ScholarshipPackage;
import model.Student;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ApprovalScholarShipLogic {

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
}
