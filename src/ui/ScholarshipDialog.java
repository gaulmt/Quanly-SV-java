package ui;

import model.ScholarshipPackage;
import model.Student;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

// Form Swing: chọn lớp cần xét học bổng + nhập số suất (quota) cho từng loại.

public class ScholarshipDialog extends JDialog {

    private JComboBox<String> classPack;
    private JTextField txtExcellent;
    private JTextField txtGood;
    private JTextField txtFairlyGood;

    private boolean checked = false;
    private String studentClass;
    private List<ScholarshipPackage> scholarShipList;

    public ScholarshipDialog(Frame owner, List<Student> studentList) {
        super(owner, "Xét học bổng theo lớp", true);
        initUI(studentList);
        pack();
        setLocationRelativeTo(owner);
    }

    private void initUI(List<Student> studentList) {
        // Lấy danh sách lớp không trùng lặp từ danh sách sinh viên hiện có
        List<String> studentClassList = new ArrayList<>();
        for (Student s : studentList) {
            String studentClass = s.getClassRoom().toUpperCase();
            if (!studentClassList.contains(studentClass)) {
                studentClassList.add(studentClass);
            }
        }
        studentClassList.sort(String::compareTo);

        List<ScholarshipPackage> Default = ScholarshipPackage.getScholarshipList();

        JPanel form = new JPanel(new GridBagLayout());
        form.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(6, 6, 6, 6);
        c.fill = GridBagConstraints.HORIZONTAL;

        classPack = new JComboBox<>(studentClassList.toArray(new String[0]));
        txtExcellent = new JTextField(String.valueOf(Default.get(0).getQuota()), 8);
        txtGood = new JTextField(String.valueOf(Default.get(1).getQuota()), 8);
        txtFairlyGood = new JTextField(String.valueOf(Default.get(2).getQuota()), 8);

        int row = 0;
        addRow(form, c, row++, "Chọn lớp:", classPack);
        addRow(form, c, row++, "Số suất học bổng xuất sắc:", txtExcellent);
        addRow(form, c, row++, "Số suất học bổng giỏi:", txtGood);
        addRow(form, c, row++, "Số suất học bổng khá:", txtFairlyGood);

        JButton btnScholarShipGiver = new JButton("Xét học bổng");
        JButton btnCancel = new JButton("Hủy");
        btnScholarShipGiver.addActionListener(e -> onCheck(Default));
        btnCancel.addActionListener(e -> dispose());

        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttons.add(btnScholarShipGiver);
        buttons.add(btnCancel);

        setLayout(new BorderLayout());
        add(form, BorderLayout.CENTER);
        add(buttons, BorderLayout.SOUTH);
        getRootPane().setDefaultButton(btnScholarShipGiver);
    }

    private void addRow(JPanel form, GridBagConstraints c, int row, String label, JComponent field) {
        c.gridx = 0; c.gridy = row; c.weightx = 0;
        form.add(new JLabel(label), c);
        c.gridx = 1; c.weightx = 1;
        form.add(field, c);
    }

    private void onCheck(List<ScholarshipPackage> Default) {
        if (classPack.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(this, "Chưa có lớp nào trong danh sách sinh viên!",
                    "Không có dữ liệu", JOptionPane.WARNING_MESSAGE);
            return;
        }
        try {
            int quotaExcellent = Integer.parseInt(txtExcellent.getText().trim());
            int quotaGood = Integer.parseInt(txtGood.getText().trim());
            int quotaFairlyGood = Integer.parseInt(txtFairlyGood.getText().trim());

            if (quotaExcellent < 0 || quotaGood < 0 || quotaFairlyGood < 0) {
                JOptionPane.showMessageDialog(this, "Số suất học bổng không được là số âm!",
                        "Dữ liệu không hợp lệ", JOptionPane.WARNING_MESSAGE);
                return;
            }

            Default.get(0).setQuota(quotaExcellent);
            Default.get(1).setQuota(quotaGood);
            Default.get(2).setQuota(quotaFairlyGood);

            scholarShipList = Default;
            studentClass = (String) classPack.getSelectedItem();
            checked = true;
            dispose();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Số suất học bổng phải là số nguyên!",
                    "Dữ liệu không hợp lệ", JOptionPane.WARNING_MESSAGE);
        }
    }

    public boolean isChecked() {
        return checked;
    }

    public String getStudentClass() {
        return studentClass;
    }

    public List<ScholarshipPackage> getScholarShipList() {
        return scholarShipList;
    }
}
