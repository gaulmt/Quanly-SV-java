package logic;

import model.ScholarshipPackage;
import model.Student;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Logic {


    //xét xem sv có đủ dk nhận học bổng hay không
    public void Considering_Scholarships(List<Student> Students, List<ScholarshipPackage> list) {
        if (Students == null || list == null || Students.isEmpty() || list.isEmpty()) {
            return;
        }
        ScholarshipPackage excellent_Scholarship = list.get(0);
        ScholarshipPackage good_Scholarship      = list.get(1);
        ScholarshipPackage mid_Scholarship       = list.get(2);

        int excellent_Scholarship_slot      =0;
        int good_Scholarship_slot           =0;
        int mid_Scholarship_slot            =0;

        for (int i=0;i < Students.size(); i++){
            Student s = Students.get(i);

            double gpa =s.getGpa();
            int traning_Point= s.getTraning_Point();
            int credit =s.getCredits();

            if (
                    gpa >= excellent_Scholarship.getMin_Gpa()
                    && traning_Point >= excellent_Scholarship.getMin_Traning_Point()
                    && excellent_Scholarship_slot < excellent_Scholarship.getQuota()
                    && credit >=15){

                    s.setScholarship_Name(excellent_Scholarship.getName());
                    excellent_Scholarship_slot +=1;
            }else if(
                    gpa >= good_Scholarship.getMin_Gpa()
                    && traning_Point >= good_Scholarship.getMin_Traning_Point()
                    && good_Scholarship_slot < good_Scholarship.getQuota()
                    && credit >=15){

                    s.setScholarship_Name(good_Scholarship.getName());
                    good_Scholarship_slot +=1;
            }else if (
                    gpa >= mid_Scholarship.getMin_Gpa()
                    && traning_Point >= mid_Scholarship.getMin_Traning_Point()
                    && mid_Scholarship_slot < mid_Scholarship.getQuota()
                    && credit >=15){

                    s.setScholarship_Name(mid_Scholarship.getName());
                    mid_Scholarship_slot+=1;
            }else{
                s.setScholarship_Name("chưa có học bổng");
            }
        }
    }

    //sắp xếp theo mssv
    public void student_Sort_By_Id(List<Student> Students){
        Students.sort(Comparator.comparing(Student::getId));
    }

}
