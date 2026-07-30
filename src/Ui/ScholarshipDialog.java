package Ui;

import Controller.ApprovalScholarshipLogic;
import Repository.Model.ScholarshipPackage;
import Repository.Model.Student;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

// Form Swing: chọn lớp cần xét học bổng + nhập số suất (quota) cho từng loại.
public class ScholarshipDialog extends JDialog {

    private final ApprovalScholarshipLogic logic;

    public ScholarshipDialog(Frame owner, List<Student> studentList, ApprovalScholarshipLogic logic) {
        super(owner, "Xét học bổng theo lớp", true);
        this.logic = logic;
        this.logic.reset();
        initUI(studentList);
        pack();
        setLocationRelativeTo(owner);
    }

    private void initUI(List<Student> studentList) {
        List<String> studentClassList = new ArrayList<>();
        for (Student s : studentList) {
            String studentClass = s.getClassRoom().toUpperCase();
            if (!studentClassList.contains(studentClass)) {
                studentClassList.add(studentClass);
            }
        }
        studentClassList.sort(String::compareTo);

        List<ScholarshipPackage> defaultScholarshipList = ScholarshipPackage.getScholarshipList();

        JPanel form = new JPanel(new GridBagLayout());
        form.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(6, 6, 6, 6);
        c.fill = GridBagConstraints.HORIZONTAL;

        JComboBox<String> classPack = new JComboBox<>(studentClassList.toArray(new String[0]));
        JTextField txtExcellent = new JTextField(String.valueOf(defaultScholarshipList.get(0).getQuota()), 8);
        JTextField txtGood = new JTextField(String.valueOf(defaultScholarshipList.get(1).getQuota()), 8);
        JTextField txtFairlyGood = new JTextField(String.valueOf(defaultScholarshipList.get(2).getQuota()), 8);

        int row = 0;
        addRow(form, c, row++, "Chọn lớp:", classPack);
        addRow(form, c, row++, "Số suất học bổng xuất sắc:", txtExcellent);
        addRow(form, c, row++, "Số suất học bổng giỏi:", txtGood);
        addRow(form, c, row++, "Số suất học bổng khá:", txtFairlyGood);

        JButton btnScholarShipGiver = new JButton("Xét học bổng");
        JButton btnCancel = new JButton("Hủy");
        btnScholarShipGiver.addActionListener(e ->
                logic.onCheck(this, classPack, txtExcellent, txtGood, txtFairlyGood, defaultScholarshipList));
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

    public boolean isChecked() {
        return logic.isChecked();
    }

    public String getStudentClass() {
        return logic.getStudentClass();
    }

    public List<ScholarshipPackage> getScholarShipList() {
        return logic.getScholarShipList();
    }
}