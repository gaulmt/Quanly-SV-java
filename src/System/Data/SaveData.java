package System.Data;

import model.Student;

import javax.swing.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class SaveData {

    String path = "C:\\Users\\Admin\\OneDrive\\Documents\\Java\\Quanly-SV-java\\src\\System\\Data\\SudentData.csv";

    public void saveStudentDataByCsv(List<Student> studentList) {
        File file = new File(path);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write('\uFEFF');
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
}
