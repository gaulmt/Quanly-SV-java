package Repository.Data;

import Repository.Model.Student;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class DataSaver {

    String path = "C:\\Users\\Admin\\OneDrive\\Documents\\Java\\Quanly-SV-java\\src\\Repository\\Data\\Students.csv";

    public void saveStudentDataByCsv(List<Student> studentList, String path) {
        File file = new File(path);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {

            for (Student sv : studentList) {
                String scholarship = sv.getScholarshipName() == null ? "Không có" : sv.getScholarshipName();
                writer.write(
                        sv.getId()                 + "," +
                                sv.getName()           + "," +
                                sv.getClassRoom()      + "," +
                                sv.getGender()         + "," +
                                sv.getGpa()            + "," +
                                sv.getTrainingPoint()  + "," +
                                sv.getCredits()        + "," +
                                scholarship
                );
                writer.newLine();
            }
            System.out.println("Đã lưu danh sách sinh viên vào: " + path);
        } catch (IOException e) {
            System.out.println("Không thể xuất ra danh sách dưới dạng file!");
        }
    }

    public void dataSaver(List<Student> List){
        saveStudentDataByCsv(List, path);
    }
}
