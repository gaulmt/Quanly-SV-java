package ui;

import model.ScholarshipPackage;
import model.Student;
import repository.StudentRepository;
import logic.Logic;

import java.util.List;
import java.util.Scanner;

public class UI {
    Scanner sc = new Scanner(System.in);
    ScholarshipPackageSetup setUp = new ScholarshipPackageSetup();
    int choice = -36;
    public void run(StudentRepository repo,Logic logic)
    {
        do {

            System.out.println("=====================MENU quảng lý ===================");
            System.out.println("|| 1.Nhập thêm sinh viên mới                        ||");
            System.out.println("|| 2.Hiển thị danh sách sinh viên hiện tại          ||");
            System.out.println("|| 3.xét học bổng                                   ||");
            System.out.println("|| 4.sắp xếp danh sách                              ||");
            System.out.println("|| 5.meo meo                                        ||");
            System.out.println("|| 6.gâu gâu                                        ||");
            System.out.println("|| 7.helo kitty                                     ||");
            System.out.println("|| 0.thoát chương trình                             ||");
            System.out.println("======================================================");
            System.out.print("Mời bạn chọn chức năng: ");
            choice = sc.nextInt();
            switch (choice) {
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    List<Student> list_Students = repo.get_All_Students();
                    List<ScholarshipPackage> list_Scholarships = ScholarshipPackage.get_Scholarship_List();

                    String faculty = setUp.input_Faculty();
                    int min_Credits = setUp.inputMin_totalCredits();
                    setUp.input_Excellent_Quota(list_Scholarships);
                    setUp.input_Good_Quota(list_Scholarships);
                    setUp.input_fairlyGood_Quota(list_Scholarships);

                    List<Student> studentFaculty = logic.Considering_Scholarships(list_Students, list_Scholarships, faculty, min_Credits);
                    System.out.println("============Danh sách sinh viên sau khi xét học bổng===========");

                    for (Student student : studentFaculty) {
                        System.out.println("#" + student.getId() + " - " + student.getName() + " - Học bổng: " + student.getScholarship_Name());
                    }

                    sc.nextLine();
                    clear();
                    break;
                case 4:
                    break;
                case 0:
                    System.out.println("tạm biệt bro!");
                    break;
                default:
                    choice = -36;
            }

        }
        while (choice != 0);
    }

    private void clear(){
        System.out.println("Nhân enter để tiếp tục...........");
        String info =sc.nextLine();
        if (info.isEmpty()){
            for (int i = 0; i < 50; i++) {
                System.out.println();
            }
        }
    }
}
