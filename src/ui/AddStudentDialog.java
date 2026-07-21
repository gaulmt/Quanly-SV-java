package ui;

import model.Student;

import javax.swing.*;
import java.awt.*;

public class AddStudentDialog extends JDialog {

    private JTextField txtId;
    private JTextField txtName;
    private JTextField txtClass;
    private JTextField txtGpa;
    private JTextField txtTraningPoint;
    private JTextField txtCredits;
    private JComboBox<String> genderPack;

    private boolean checked = false;
    private Student result;

    public AddStudentDialog(Frame owner) {
        super(owner, "Thêm sinh viên mới", true);
        initUI();
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
        txtTraningPoint = new JTextField(16);
        txtCredits = new JTextField(16);

        int row = 0;
        addRow(form, c, row++, "MSSV:", txtId);
        addRow(form, c, row++, "Họ và tên:", txtName);
        addRow(form, c, row++, "Lớp:", txtClass);
        addRow(form, c, row++, "Giới tính:", genderPack);
        addRow(form, c, row++, "GPA (0 - 4):", txtGpa);
        addRow(form, c, row++, "Điểm rèn luyện (0 - 100):", txtTraningPoint);
        addRow(form, c, row++, "Số tín chỉ tích lũy:", txtCredits);

        JButton btnSave = new JButton("Lưu");
        JButton btnCancel = new JButton("Hủy");
        btnSave.addActionListener(e -> onSave());
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

    private void onSave() {
        String id = txtId.getText().trim();
        String name = txtName.getText().trim();
        String classRoom = txtClass.getText().trim();
        String gender = (String) genderPack.getSelectedItem();

        if (id.isEmpty() || name.isEmpty() || classRoom.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Vui lòng nhập đầy đủ MSSV, Họ tên và Lớp!",
                    "Thiếu thông tin", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            double gpa = Double.parseDouble(txtGpa.getText().trim());
            int traningPoint = Integer.parseInt(txtTraningPoint.getText().trim());
            int credits = Integer.parseInt(txtCredits.getText().trim());

            if (gpa < 0 || gpa > 4) {
                warning("GPA phải nằm trong khoảng 0 đến 4!");
                return;
            }
            if (traningPoint < 0 || traningPoint > 100) {
                warning("Điểm rèn luyện phải nằm trong khoảng 0 đến 100!");
                return;
            }
            if (credits < 0) {
                warning("Số tín chỉ không được âm!");
                return;
            }

            result = new Student(id, name, classRoom, gender, gpa, traningPoint, credits);
            checked = true;
            dispose();

        } catch (NumberFormatException ex) {
            warning("GPA, Điểm rèn luyện và Số tín chỉ phải là số hợp lệ!");
        }
    }

    private void warning(String message) {
        JOptionPane.showMessageDialog(this, message, "Dữ liệu không hợp lệ", JOptionPane.WARNING_MESSAGE);
    }

    public boolean isChecked() {
        return checked;
    }

    public Student getResult() {
        return result;
    }
}
