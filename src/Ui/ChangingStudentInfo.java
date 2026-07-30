package Ui;

import Controller.ChangingStudentLogic;
import Repository.Model.Student;

import javax.swing.*;
import java.awt.*;

public class ChangingStudentInfo extends JDialog {

    private JTextField txtId;
    private JTextField txtName;
    private JTextField txtClass;
    private JTextField txtGpa;
    private JTextField txtTrainingPoint;
    private JTextField txtCredits;
    private JComboBox<String> genderPack;

    private ChangingStudentLogic logic;

    public ChangingStudentInfo(Frame owner, Student oldInfo) {
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

        logic = new ChangingStudentLogic(this, txtId, txtName, txtClass,
                genderPack, txtGpa, txtTrainingPoint, txtCredits);

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

        btnConfirm.addActionListener(e -> logic.validateInput());
        btnCancel.addActionListener(e -> dispose());

        panel.add(btnConfirm);
        panel.add(btnCancel);
        return panel;
    }

    public boolean isChecked() {
        return logic.isChecked();
    }

    public Student getResult() {
        return logic.getResult();
    }
}