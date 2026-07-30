package Controller;

import Repository.Model.Student;

import javax.swing.*;

public class ChangingStudentLogic {

    private final JDialog owner;
    private final JTextField txtId;
    private final JTextField txtName;
    private final JTextField txtClass;
    private final JComboBox<String> genderPack;
    private final JTextField txtGpa;
    private final JTextField txtTrainingPoint;
    private final JTextField txtCredits;

    private boolean checked = false;
    private Student result;

    public ChangingStudentLogic(JDialog owner,
                                JTextField txtId, JTextField txtName, JTextField txtClass,
                                JComboBox<String> genderPack, JTextField txtGpa,
                                JTextField txtTrainingPoint, JTextField txtCredits) {
        this.owner = owner;
        this.txtId = txtId;
        this.txtName = txtName;
        this.txtClass = txtClass;
        this.genderPack = genderPack;
        this.txtGpa = txtGpa;
        this.txtTrainingPoint = txtTrainingPoint;
        this.txtCredits = txtCredits;
    }

    public void validateInput() {
        String id = txtId.getText().trim();
        String name = txtName.getText().trim();
        String classRoom = txtClass.getText().trim();
        String gender = (String) genderPack.getSelectedItem();

        if (id.isEmpty() || name.isEmpty() || classRoom.isEmpty()) {
            JOptionPane.showMessageDialog(owner, "MSSV, Họ tên và lớp học không được để trống!",
                    "Thiếu thông tin", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            double gpa = Double.parseDouble(txtGpa.getText().trim());
            int trainingPoint = Integer.parseInt(txtTrainingPoint.getText().trim());
            int credits = Integer.parseInt(txtCredits.getText().trim());

            if (gpa < 0 || gpa > 4) {
                JOptionPane.showMessageDialog(owner, "GPA phải nằm trong khoảng 0 đến 4!",
                        "Dữ liệu không hợp lệ", JOptionPane.WARNING_MESSAGE);
                return;
            }
            if (trainingPoint < 0 || trainingPoint > 100) {
                JOptionPane.showMessageDialog(owner, "Điểm rèn luyện phải nằm trong khoảng 0 đến 100!",
                        "Dữ liệu không hợp lệ", JOptionPane.WARNING_MESSAGE);
                return;
            }
            if (credits < 0) {
                JOptionPane.showMessageDialog(owner, "Số tín chỉ không được âm!",
                        "Dữ liệu không hợp lệ", JOptionPane.WARNING_MESSAGE);
                return;
            }

            result = new Student(id, name, classRoom, gender, gpa, trainingPoint, credits);
            checked = true;
            owner.dispose();

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(owner, "GPA / Điểm rèn luyện / Tín chỉ phải là số hợp lệ!",
                    "Sai định dạng", JOptionPane.WARNING_MESSAGE);
        }
    }

    public boolean isChecked() {
        return checked;
    }

    public Student getResult() {
        return result;
    }
}