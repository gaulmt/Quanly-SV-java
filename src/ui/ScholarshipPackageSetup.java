package ui;

import model.ScholarshipPackage;
import model.Student;

import java.util.*;

public class ScholarshipPackageSetup {

    private final Scanner sc = new Scanner(System.in);

    private String input() {
        return sc.nextLine().trim();
    }

    public String inputClassRoom(List<Student> students) {
        List<String> classRoom = new ArrayList<>();
        for (Student s : students) {
            String class_Room = s.getClass_Room().toUpperCase();
            if (!classRoom.contains(class_Room)) {
                classRoom.add(class_Room);
            }
        }

        classRoom.sort(String::compareTo);


        while (true) {
            System.out.println("========= Danh sách lớp =========");
            for (int i = 0; i < classRoom.size(); i++) {
                System.out.println((i + 1) + ". " + classRoom.get(i));
            }
            System.out.print("Chọn khoa (nhập số thứ tự): ");

            String temp = input();
            try {
                int choice = Integer.parseInt(temp);
                if (choice >= 1 && choice <= classRoom.size()) {
                    return classRoom.get(choice - 1);
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




