package Controller;

import Repository.Model.Student;

import javax.swing.*;

public class AddingStudentLogic {

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

    public AddingStudentLogic(JDialog owner,
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


    public void oldStudentInfo(Student oldStudentInfo) {
        if (oldStudentInfo == null) return;
        txtId.setText(oldStudentInfo.getId());
        txtName.setText(oldStudentInfo.getName());
        txtClass.setText(oldStudentInfo.getClassRoom());
        genderPack.setSelectedItem(oldStudentInfo.getGender());
        txtGpa.setText(String.valueOf(oldStudentInfo.getGpa()));
        txtTrainingPoint.setText(String.valueOf(oldStudentInfo.getTrainingPoint()));
        txtCredits.setText(String.valueOf(oldStudentInfo.getCredits()));
        txtId.selectAll();
        txtId.requestFocusInWindow();
    }

    public void addStudent() {
        String id = txtId.getText().trim().toUpperCase();
        String name = txtName.getText().trim();
        String classRoom = txtClass.getText().trim().toUpperCase();
        String gender = (String) genderPack.getSelectedItem();

        if (id.isEmpty() || name.isEmpty() || classRoom.isEmpty()) {
            warning("Vui lòng nhập đầy đủ MSSV, Họ tên và Lớp!");
            return;
        }

        if (!id.matches("SV\\d{3}")){
            warning("ID phải có dạng SVxxx!");
            return;
        }

        if (!classRoom.matches("\\p{L}{4}\\d{1}")){
            warning("Lớp học phải có dạng 4 chữ và 1 số, VD: AAA1!");
            return;
        }

        try {
            double gpa = Double.parseDouble(txtGpa.getText().trim());
            int trainingPoint = Integer.parseInt(txtTrainingPoint.getText().trim());
            int credits = Integer.parseInt(txtCredits.getText().trim());

            if (gpa < 0 || gpa > 4) {
                warning("GPA phải nằm trong khoảng 0 đến 4!");
                return;
            }
            if (trainingPoint < 0 || trainingPoint > 100) {
                warning("Điểm rèn luyện phải nằm trong khoảng 0 đến 100!");
                return;
            }
            if (credits < 0) {
                warning("Số tín chỉ không được âm!");
                return;
            }

            result = new Student(id, name, classRoom, gender, gpa, trainingPoint, credits);
            checked = true;
            owner.dispose();

        } catch (NumberFormatException ex) {
            warning("GPA, Điểm rèn luyện và Số tín chỉ phải là số hợp lệ!");
        }
    }

    private void warning(String message) {
        JOptionPane.showMessageDialog(owner, message, "Dữ liệu không hợp lệ", JOptionPane.WARNING_MESSAGE);
    }

    public boolean isChecked() {
        return checked;
    }

    public Student getResult() {
        return result;
    }
}