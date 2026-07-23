package ui;

import model.Student;

import javax.swing.*;
import java.awt.*;

public class ChangeStudentInfo extends JDialog {

    private JTextField txtId;
    private JTextField txtName;
    private JTextField txtClass;
    private JTextField txtGpa;
    private JTextField txtTrainingPoint;
    private JTextField txtCredits;
    private JComboBox<String> genderPack;

    private boolean checked = false;
    private Student result;

    public ChangeStudentInfo(Frame owner, Student oldInfo) {
        super(owner, "Sửa thông tin sinh viên", true);
        initUI(); // phải tạo component trước, rồi mới set dữ liệu pre-fill lên chúng

        txtId.setText(oldInfo.getId());
        txtName.setText(oldInfo.getName());
        txtClass.setText(oldInfo.getClassRoom());
        genderPack.setSelectedItem(oldInfo.getGender());
        txtGpa.setText(String.valueOf(oldInfo.getGpa()));
        txtTrainingPoint.setText(String.valueOf(oldInfo.getTrainingPoint()));
        txtCredits.setText(String.valueOf(oldInfo.getCredits()));

        pack();
        setLocationRelativeTo(owner);
    }

    public boolean isChecked() {
        return checked;
    }

    public Student getResult() {
        return result;
    }

    private void initUI() {
        JPanel form = new JPanel(new GridBagLayout());
        form.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5, 5, 5, 5);
        c.fill = GridBagConstraints.HORIZONTAL;

        txtId = new JTextField(16);
        txtName = new JTextField(16);
        txtClass = new JTextField(16);
        genderPack = new JComboBox<>(new String[]{"Nam", "Nữ", "Khác"});
        txtGpa = new JTextField(16);
        txtTrainingPoint = new JTextField(16);
        txtCredits = new JTextField(16);

        String[] labels = {"MSSV", "Họ và tên", "Lớp", "Giới tính", "GPA", "Điểm rèn luyện", "Tín chỉ"};
        JComponent[] fields = {txtId, txtName, txtClass, genderPack, txtGpa, txtTrainingPoint, txtCredits};

        for (int i = 0; i < labels.length; i++) {
            c.gridx = 0;
            c.gridy = i;
            c.weightx = 0;
            form.add(new JLabel(labels[i] + ":"), c);

            c.gridx = 1;
            c.weightx = 1;
            form.add(fields[i], c);
        }

        setLayout(new BorderLayout(10, 10));
        add(form, BorderLayout.CENTER);
        add(buttonPanel(), BorderLayout.SOUTH);
    }

    private JPanel buttonPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 8));

        JButton btnConfirm = new JButton("Xác nhận");
        JButton btnCancel = new JButton("Hủy");

        btnConfirm.addActionListener(e -> validateInput());

        btnCancel.addActionListener(e -> dispose());

        panel.add(btnConfirm);
        panel.add(btnCancel);
        return panel;
    }

    private void validateInput() {

        String id = txtId.getText().trim();
        String name = txtName.getText().trim();
        String classRoom = txtClass.getText().trim();
        String gender = (String) genderPack.getSelectedItem();
        if (id.isEmpty() || name.trim().isEmpty() || classRoom.isEmpty()) {
            JOptionPane.showMessageDialog(this, "MSSV , Họ tên và lớp học không được để trống!",
                    "Thiếu thông tin", JOptionPane.WARNING_MESSAGE);
            return;
        }
        try {
            double gpa = Double.parseDouble(txtGpa.getText().trim());
            int traningPoint = Integer.parseInt(txtTrainingPoint.getText().trim());
            int credits = Integer.parseInt(txtCredits.getText().trim());
            if (gpa < 0 || gpa > 4) {
                JOptionPane.showMessageDialog( this ,"GPA phải nằm trong khoảng 0 đến 4!");
                return;
            }
            if (traningPoint < 0 || traningPoint > 100) {
                JOptionPane.showMessageDialog(this ,"Điểm rèn luyện phải nằm trong khoảng 0 đến 100!");
                return;
            }
            if (credits < 0) {
                JOptionPane.showMessageDialog(this ,"Số tín chỉ không được âm!");
                return;
            }

            result = new Student(id, name, classRoom, gender, gpa, traningPoint, credits);
            checked = true;
            dispose();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "GPA / Điểm rèn luyện / Tín chỉ phải là số hợp lệ!",
                    "Sai định dạng", JOptionPane.WARNING_MESSAGE);
        }
    }
}

