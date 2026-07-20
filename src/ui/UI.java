// package ui;

// import model.ScholarshipPackage;
// import model.Student;
// import repository.StudentRepository;
// import logic.Logic;

// import java.util.List;
// import java.util.Scanner;

// public class UI {
//     Scanner sc = new Scanner(System.in);
//     int choice = -36;
//     public void run(StudentRepository repo,Logic logic)
//     {
//         do {
//             System.out.println("=====================MENU quảng lý ===================");
//             System.out.println("|| 1.Nhập thêm sinh viên mới                        ||");
//             System.out.println("|| 2.Hiển thị danh sách sinh viên hiện tại          ||");
//             System.out.println("|| 3.xét học bổng                                   ||");
//             System.out.println("|| 4.sắp xếp danh sách                              ||");
//             System.out.println("|| 5.meo meo                                        ||");
//             System.out.println("|| 6.gâu gâu                                        ||");
//             System.out.println("|| 7.helo kitty                                     ||");
//             System.out.println("|| 0.thoát chương trình                             ||");
//             System.out.println("======================================================");
//             System.out.print("Mời bạn chọn chức năng: ");
//             choice = sc.nextInt();
//             sc.nextLine();
//             switch (choice) {
//                 case 1:
//                     break;
//                 case 2:
//                     break;
//                 case 3:
//                     List<Student> list_Students = repo.get_All_Students();
//                     List<ScholarshipPackage> list_Scholarships = ScholarshipPackage.get_Scholarship_List();

//                     logic.Considering_Scholarships(list_Students, list_Scholarships);
//                     System.out.println("============Danh sách sinh viên sau khi xét học bổng===========");
//                     for (int i = 0; i < list_Students.size(); i++) {
//                         Student student = list_Students.get(i);
//                         System.out.println("#" + student.getId() + " - " + student.getName() + " - Học bổng: " + student.getScholarship_Name());
//                     }
//                     break;
//                 case 4:
//                     break;
//                 case 0:
//                     System.out.println("tạm biệt bro!");
//                 default:
//                     choice = -36;
//             }
//         }
//         while (choice != 0);
//     }
// }

package ui;

import logic.Logic;
import repository.StudentRepository;

import javax.swing.*;

/**
 * Điểm khởi chạy giao diện. Giữ nguyên chữ ký run(repo, logic) như bản console cũ
 * để Main.java không cần thay đổi gì.
 */
public class UI {
    public void run(StudentRepository repo, Logic logic) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ignored) {
            }
            new StudentFrame(repo, logic).setVisible(true);
        });
    }
    private void ShowAll(List<Student> s){
        if(s == null ||s.isEmpty()){
            System.out.println("Danh sách rỗng.....");
            return;
        }


        for (Student student : s){
            System.out.println(

                    student.getId()                 + " - " +
                            student.getName()               + " - " +
                            student.getClass_Room()         + " - " +
                            student.getGender()             + " - " +
                            student.getGpa()                + " - " +
                            student.getTraning_Point()      + " - " +
                            student.getCredits());
        }

    }

    private void clearScreen(){
        System.out.println("Nhân enter để tiếp tục...........");
        String info =sc.nextLine();
        if (info.isEmpty()){
            for (int i = 0; i < 50; i++) {
                System.out.println();
            }
        }
    }
}
