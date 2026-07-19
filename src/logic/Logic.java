package logic;

import model.ScholarshipPackage;
import model.Student;
import ui.ScholarshipPackageSetup;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Logic {

    ScholarshipPackageSetup setUp = new ScholarshipPackageSetup();
    //xét xem sv có đủ dk nhận học bổng hay không
    public void Considering_Scholarships(List<Student> Students, List<ScholarshipPackage> list,String faculty,  int min_Credits) {
        if (Students == null || list == null || Students.isEmpty() || list.isEmpty()) {
            return;
        }
        ScholarshipPackage excellent_Scholarship    = list.get(0);
        ScholarshipPackage good_Scholarship         = list.get(1);
        ScholarshipPackage fairlyGood_Scholarship   = list.get(2);

        List<Student> student_Faculty = Students.stream()
                .filter(student -> student.getFaculty_Name().equalsIgnoreCase(faculty))
                .collect(Collectors.toList());

        student_Faculty.sort(
                Comparator.comparing(Student::getGpa, Comparator.reverseOrder())
                .thenComparing(Student::getTraning_Point, Comparator.reverseOrder())
                .thenComparing(Student::getCredits, Comparator.reverseOrder())
        );

       int excellent_slot   = excellent_Scholarship.getQuota();
       int good_slot        = good_Scholarship.getQuota();
       int fairlyGood_slot  = fairlyGood_Scholarship.getQuota();

        for (Student s : student_Faculty) {
            double gpa = s.getGpa();
            int traning_Point = s.getTraning_Point();
            int credit = s.getCredits();

            if (
                    gpa >= excellent_Scholarship.getMin_Gpa()
                            && traning_Point >= excellent_Scholarship.getMin_Traning_Point()
                            && excellent_slot != 0
                            && credit >= min_Credits) {

                excellent_slot--;
                s.setScholarship_Name(excellent_Scholarship.getName());

            } else if (
                    gpa >= good_Scholarship.getMin_Gpa()
                            && traning_Point >= good_Scholarship.getMin_Traning_Point()
                            && good_slot != 0
                            && credit >= min_Credits) {
                good_slot--;
                s.setScholarship_Name(good_Scholarship.getName());

            } else if (
                    gpa >= fairlyGood_Scholarship.getMin_Gpa()
                            && traning_Point >= fairlyGood_Scholarship.getMin_Traning_Point()
                            && fairlyGood_slot != 0
                            && credit >= min_Credits) {

                fairlyGood_slot--;
                s.setScholarship_Name(fairlyGood_Scholarship.getName());

            } else {
                s.setScholarship_Name("chưa có học bổng");
            }
        }
    }


    //sắp xếp theo mssv
    public void student_Sort_By_Id(List<Student> Students){
        Students.sort(Comparator.comparing(Student::getId));
    }

}
