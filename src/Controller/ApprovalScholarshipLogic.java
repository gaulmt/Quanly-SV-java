package Controller;

import Repository.Model.ScholarshipPackage;
import Repository.Model.Student;

import javax.swing.*;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ApprovalScholarshipLogic {

    private boolean checked;
    private String studentClass;
    private List<ScholarshipPackage> scholarShipList;

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

            if (gpa >= excellentScholarship.getMinGpa()
                    && trainingPoint >= excellentScholarship.getMinTrainingPoint()
                    && excellentSlot != 0
                    && credit >= 15) {

                excellentSlot--;
                s.setScholarshipName(excellentScholarship.getName());

            } else if (gpa >= goodScholarship.getMinGpa()
                    && trainingPoint >= goodScholarship.getMinTrainingPoint()
                    && goodSlot != 0
                    && credit >= 15) {

                goodSlot--;
                s.setScholarshipName(goodScholarship.getName());

            } else if (gpa >= fairlyGoodScholarship.getMinGpa()
                    && trainingPoint >= fairlyGoodScholarship.getMinTrainingPoint()
                    && fairlyGoodSlot != 0
                    && credit >= 15) {

                fairlyGoodSlot--;
                s.setScholarshipName(fairlyGoodScholarship.getName());

            } else {
                s.setScholarshipName("Không có");
            }
        }
        return studentClassRoom;
    }


    public void reset() {
        checked = false;
        studentClass = null;
        scholarShipList = null;
    }

    public void onCheck(JDialog owner, JComboBox<String> classPack,
                        JTextField txtExcellent, JTextField txtGood, JTextField txtFairlyGood,
                        List<ScholarshipPackage> defaultScholarshipList) {

        if (classPack.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(owner, "Chưa có lớp nào trong danh sách sinh viên!",
                    "Không có dữ liệu", JOptionPane.WARNING_MESSAGE);
            return;
        }
        try {
            int quotaExcellent = Integer.parseInt(txtExcellent.getText().trim());
            int quotaGood = Integer.parseInt(txtGood.getText().trim());
            int quotaFairlyGood = Integer.parseInt(txtFairlyGood.getText().trim());

            if (quotaExcellent < 0 || quotaGood < 0 || quotaFairlyGood < 0) {
                JOptionPane.showMessageDialog(owner, "Số suất học bổng không được là số âm!",
                        "Dữ liệu không hợp lệ", JOptionPane.WARNING_MESSAGE);
                return;
            }

            defaultScholarshipList.get(0).setQuota(quotaExcellent);
            defaultScholarshipList.get(1).setQuota(quotaGood);
            defaultScholarshipList.get(2).setQuota(quotaFairlyGood);

            scholarShipList = defaultScholarshipList;
            studentClass = (String) classPack.getSelectedItem();
            checked = true;
            owner.dispose();

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(owner, "Số suất học bổng phải là số nguyên!",
                    "Dữ liệu không hợp lệ", JOptionPane.WARNING_MESSAGE);
        }
    }

    public boolean isChecked() {
        return checked;
    }

    public String getStudentClass() {
        return studentClass;
    }

    public List<ScholarshipPackage> getScholarShipList() {
        return scholarShipList;
    }
}