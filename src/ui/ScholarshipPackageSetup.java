package ui;

import model.ScholarshipPackage;

import java.util.List;
import java.util.Scanner;

public class ScholarshipPackageSetup {

    private final Scanner sc = new Scanner(System.in);

    private String input() {
        return sc.nextLine().trim();
    }

    public String input_Faculty() {
        while (true) {
            System.out.print("Nhập tên khoa: ");
            String temp = "";
            temp = input();
            if (temp.trim().isEmpty()) {
                System.out.println("Tên khoa không được để trống!");
                continue;
            }
            if (temp.matches("[\\p{L} ]+")) {
                return temp;
            }
            System.out.println("Tên khoa phải là chuỗi chữ, không chứa số hoặc ký tự đặc biệt!");
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




