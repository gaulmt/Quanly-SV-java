package ui;

import model.SaveData;
import model.Student;

import javax.swing.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class SaveFileStudentDialog extends JDialog {


    private final SaveData saveData;

    public SaveFileStudentDialog() {
        this.saveData = new SaveData();
    }

    public SaveFileStudentDialog(String filePath) {
        this.saveData = new SaveData();
        this.saveData.setFilePath(filePath);
    }

    public String getFilePath() {
        return saveData.getFilePath();
    }

    public void saveStudentListByTxt(List<Student> studentList) {
        String path = saveData.getFilePath();
        if (path == null || path.isEmpty()) {
            System.out.println("Chưa chọn đường dẫn lưu file!");
            return;
        }

        File file = new File(path);
        if (file.exists()) {
            System.out.println("Đã ghi đè dữ liệu!");
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (Student sv : studentList) {
                String scholarship = sv.getScholarshipName() == null ? "Không có" : sv.getScholarshipName();
                writer.write(
                        sv.getId()                 + " " +
                                sv.getName()           + " " +
                                sv.getClassRoom()      + " " +
                                sv.getGender()         + " " +
                                sv.getGpa()            + " " +
                                sv.getTrainingPoint()  + " " +
                                sv.getCredits()        + " " +
                                scholarship
                );
                writer.newLine();
            }
            System.out.println("Đã lưu danh sách sinh viên vào: " + path);
        } catch (IOException e) {
            System.out.println("Không thể xuất ra danh sách dưới dạng file!");
        }
    }

    public boolean chooseFilePath(JFrame parent) {
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Chọn nơi lưu danh sách sinh viên");
        chooser.setSelectedFile(new File("DanhSachSinhVien.txt"));

        int option = chooser.showSaveDialog(parent);
        if (option == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            String path = file.getAbsolutePath();
            if (!path.toLowerCase().endsWith(".txt")) {
                path += ".txt";
            }
            saveData.setFilePath(path);
            return true;
        }
        return false;
    }

}
