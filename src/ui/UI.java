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
            System.out.println("|| 4.sắp xếp danh sách theo id                      ||");
            System.out.println("|| 5.Nhập vào file sinh viên                        ||");
            System.out.println("|| 6.xuất file sinh viên theo khoa                  ||");
            System.out.println("|| 0.thoát chương trình                             ||");
            System.out.println("======================================================");
            System.out.print("Mời bạn chọn chức năng: ");
            choice = sc.nextInt();
            switch (choice) {
                case 1:
                    break;
                    //coming soon
                case 2:
                    System.out.println("============Danh sách sinh viên hiện tại===========");
                    Show_All(repo.get_All_Students());
                    break;
                case 3:
                    List<Student> list_Students = repo.get_All_Students();
                    List<ScholarshipPackage> list_Scholarships = ScholarshipPackage.get_Scholarship_List();

                    String classRoom = setUp.inputClassRoom(list_Students);
                    setUp.input_Excellent_Quota(list_Scholarships);
                    setUp.input_Good_Quota(list_Scholarships);
                    setUp.input_fairlyGood_Quota(list_Scholarships);

                    List<Student> studentclassRoom = logic.Considering_Scholarships(list_Students, list_Scholarships, classRoom);
                    System.out.println("============Danh sách sinh viên sau khi xét học bổng===========");

                    for (Student student : studentclassRoom) {
                        System.out.println(student.getId() + " - " + student.getName() + " - Học bổng: " + student.getScholarship_Name());
                    }

                    sc.nextLine();
                    Clear_Screen_Screen();
                    break;
                case 4:
                    if (repo.get_All_Students().isEmpty()){
                        System.out.println("Danh sách rỗng....");
                        break;
                    }
                    logic.student_Sort_By_Id(repo.get_All_Students());
                    System.out.println("Danh sách đã được sắp xếp từ trên xuống theo mã số sinh viên!");
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



    private void Show_All(List<Student> s){
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
    
    private void Clear_Screen_Screen(){
        System.out.println("Nhân enter để tiếp tục...........");
        String info =sc.nextLine();
        if (info.isEmpty()){
            for (int i = 0; i < 50; i++) {
                System.out.println();
            }
        }
    }
}
