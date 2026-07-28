package Ui;

import Repository.Data.DataSaver;
import Repository.FilePath;
import Repository.Model.Student;

import javax.swing.*;
import java.io.File;
import java.util.List;

public class SavingFileStudentDialog extends JDialog {


    private final FilePath filePath;
    private final DataSaver data = new DataSaver();

    public SavingFileStudentDialog() {
        this.filePath = new FilePath();
    }

    public String getFilePath() {
        return filePath.getFilePath();
    }

    public void saveStudentListByCsv(List<Student> studentList) {
        String path = filePath.getFilePath();
        if (path == null || path.isEmpty()) {
            System.out.println("Chưa chọn đường dẫn lưu file!");
            return;
        }

        File file = new File(path);
        if (file.exists()) {
            System.out.println("Đã ghi đè dữ liệu!");
        }

        data.saveStudentDataByCsv(studentList, path);
    }

    public boolean chooseFilePath(JFrame parent) {
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Chọn nơi lưu danh sách sinh viên");
        chooser.setSelectedFile(new File("DanhSachSinhVien.csv"));

        int option = chooser.showSaveDialog(parent);
        if (option == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            String path = file.getAbsolutePath();
            if (!path.toLowerCase().endsWith(".csv")) {
                path += ".csv";
            }
            filePath.setFilePath(path);
            return true;
        }
        return false;
    }

}
