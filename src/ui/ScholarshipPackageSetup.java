package ui;

import model.ScholarshipPackage;
import model.Student;

import java.util.*;

public class ScholarshipPackageSetup {

    private final Scanner sc = new Scanner(System.in);

    private String input() {
        return sc.nextLine().trim();
    }

    public String input_Faculty(List<Student> students) {
        List<String> faculty_List = new ArrayList<>();
        for (Student s : students) {
            String faculty = s.getFaculty_Name().toUpperCase();
            if (!faculty_List.contains(faculty)) {
                faculty_List.add(faculty);
            }
        }

        faculty_List.sort(String::compareTo);


        while (true) {
            System.out.println("========= Danh sách khoa =========");
            for (int i = 0; i < faculty_List.size(); i++) {
                System.out.println((i + 1) + ". " + faculty_List.get(i));
            }
            System.out.print("Chọn khoa (nhập số thứ tự): ");

            String temp = input();
            try {
                int choice = Integer.parseInt(temp);
                if (choice >= 1 && choice <= faculty_List.size()) {
                    return faculty_List.get(choice - 1);
                }
                System.out.println("Số thứ tự không hợp lệ, vui lòng chọn lại!");
            } catch (NumberFormatException e) {
                System.out.println("Vui lòng nhập một con số!");
            }
        }
    }


    private void inputQuota(List<ScholarshipPackage> list, int index, String label) {
        while (true) {
            try {
                System.out.print("Nhập số suất học bổng loại " + label + ": ");
                int quota = Integer.parseInt(input());
                if (quota >= 0) {
                    list.get(index).setQuota(quota);
                    return;
                }
                System.out.println("Suất học bổng không được là số âm!");
            } catch (NumberFormatException e) {
                System.out.println("Suất học bổng phải là một con số!");
            }
        }
    }

    public void input_Excellent_Quota(List<ScholarshipPackage> list) {
        inputQuota(list, 0, "xuất sắc");
    }

    public void input_Good_Quota(List<ScholarshipPackage> list) {
        inputQuota(list, 1, "giỏi");
    }

    public void input_fairlyGood_Quota(List<ScholarshipPackage> list) {
        inputQuota(list, 2, "khá");
    }
}




