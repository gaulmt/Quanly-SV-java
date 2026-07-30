package Ui;

import Controller.AddingStudentLogic;
import Repository.Model.Student;

import javax.swing.*;
import java.awt.*;

public class AddingStudentDialog extends JDialog {

    private AddingStudentLogic logic;

    public AddingStudentDialog(JFrame owner) {
        super(owner, "Thêm sinh viên mới", true);
        initUI();
        pack();
        setLocationRelativeTo(owner);
    }

    public AddingStudentDialog(JFrame owner, Student oldStudentInfo) {
        this(owner);
        logic.oldStudentInfo(oldStudentInfo);
    }

    private void initUI() {
        JPanel form = new JPanel(new GridBagLayout());
        form.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5, 5, 5, 5);
        c.fill = GridBagConstraints.HORIZONTAL;

        JTextField txtId = new JTextField(16);
        JTextField txtName = new JTextField(16);
        JTextField txtClass = new JTextField(16);
        JComboBox<String> genderPack = new JComboBox<>(new String[]{"Nam", "Nữ", "Khác"});
        JTextField txtGpa = new JTextField(16);
        JTextField txtTrainingPoint = new JTextField(16);
        JTextField txtCredits = new JTextField(16);

        logic = new AddingStudentLogic(this, txtId, txtName, txtClass,
                genderPack, txtGpa, txtTrainingPoint, txtCredits);

        int row = 0;
        addRow(form, c, row++, "MSSV:", txtId);
        addRow(form, c, row++, "Họ và tên:", txtName);
        addRow(form, c, row++, "Lớp:", txtClass);
        addRow(form, c, row++, "Giới tính:", genderPack);
        addRow(form, c, row++, "GPA (0 - 4):", txtGpa);
        addRow(form, c, row++, "Điểm rèn luyện (0 - 100):", txtTrainingPoint);
        addRow(form, c, row++, "Số tín chỉ tích lũy:", txtCredits);

        JButton btnSave = new JButton("Lưu");
        JButton btnCancel = new JButton("Hủy");
        btnSave.addActionListener(e -> logic.addStudent());
        btnCancel.addActionListener(e -> dispose());

        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttons.add(btnSave);
        buttons.add(btnCancel);

        setLayout(new BorderLayout());
        add(form, BorderLayout.CENTER);
        add(buttons, BorderLayout.SOUTH);
        getRootPane().setDefaultButton(btnSave);
    }

    private void addRow(JPanel form, GridBagConstraints c, int row, String label, JComponent field) {
        c.gridx = 0; c.gridy = row; c.weightx = 0;
        form.add(new JLabel(label), c);
        c.gridx = 1; c.weightx = 1;
        form.add(field, c);
    }

    public boolean isChecked() {
        return logic.isChecked();
    }

    public Student getResult() {
        return logic.getResult();
    }
}