package Ui;

import Controller.ApprovalScholarshipLogic;
import Repository.Data.DataSaver;
import Repository.Model.Student;
import Repository.StudentManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class StudentFrame extends JFrame {

    private final StudentManager repo;
    private final ApprovalScholarshipLogic logic;
    private final StudentManager studentManager = new StudentManager();
    private JTable table;

    private final String[] row = {"MSSV", "Họ và tên", "Lớp", "Giới tính", "GPA", "Điểm rèn luyện", "Tín chỉ", "Học bổng"};
    private DefaultTableModel model;

    public StudentFrame(StudentManager repo, ApprovalScholarshipLogic logic) {
        super("Quản lý sinh viên");
        this.repo = repo;
        this.logic = logic;
        initUI();
        updateTable(repo.getAllStudents());
    }

    private void initUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 600);
        setMinimumSize(new Dimension(850, 450));
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(8, 8));
        JPanel content = new JPanel(new BorderLayout(10, 10));

        content.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        content.add(panelHeader(), BorderLayout.NORTH);
        content.add(panelFooter(), BorderLayout.SOUTH);

        model = new DefaultTableModel(row, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        table = new JTable(model);
        Font font = new Font("Segoe UI", Font.PLAIN, 13);
        table.setRowHeight(25);
        table.setFont(font);
        table.getTableHeader().setFont(font);
        content.add(new JScrollPane(table), BorderLayout.CENTER);

        add(content, BorderLayout.CENTER);

    }

    private JPanel panelHeader() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 6, 0));

        JButton btnAddStudent = new JButton("Thêm sinh viên");
        JButton btnScholarShipGiver = new JButton("Xét học bổng");
        JButton btnSortStudentByID = new JButton("Sắp xếp theo MSSV");
        JButton btnSaveStudentList = new JButton("Lưu danh sách sinh viên");
        JButton btnRefresh = new JButton("Làm mới");

        btnAddStudent.addActionListener(e -> addNewStudent());
        btnScholarShipGiver.addActionListener(e -> scholarShipGiver());
        btnSortStudentByID.addActionListener(e -> sortStudentByID());
        btnSaveStudentList.addActionListener(e -> saveFileStudent());
        btnRefresh.addActionListener(e -> updateTable(repo.getAllStudents()));

        panel.add(btnAddStudent);
        panel.add(btnScholarShipGiver);
        panel.add(btnSortStudentByID);
        panel.add(btnSaveStudentList);
        panel.add(btnRefresh);
        return panel;
    }

    private JPanel panelFooter() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 6, 0));

        JButton btnChangeStudentInfo = new JButton("Sửa thông tin");
        JButton btnDeleteStudent = new JButton("Xóa sinh viên");


        btnChangeStudentInfo.addActionListener(e -> changeStudentInfo());
        btnDeleteStudent.addActionListener(e -> deleteStudentInfo());


        panel.add(btnChangeStudentInfo);
        panel.add(btnDeleteStudent);

        return panel;
    }


    private void addNewStudent() {
        Student oldStudentInfo = null;

        while (true) {
            AddingStudentDialog addStudentDialog = new AddingStudentDialog(this, oldStudentInfo);
            addStudentDialog.setVisible(true);
            if (!addStudentDialog.isChecked()) {
                break;
            }
            Student newStudentInfo = addStudentDialog.getResult();
            if (studentManager.findStudentById(repo, newStudentInfo.getId()) != null) {
                JOptionPane.showMessageDialog(this,
                        "MSSV này đã tồn tại trong danh sách!",
                        "Trùng MSSV", JOptionPane.WARNING_MESSAGE);
                oldStudentInfo = newStudentInfo;
                continue;
            }
            List<Student> updatedList = repo.addStudent(newStudentInfo);
            updateTable(updatedList);
            break;
        }


    }

    private void scholarShipGiver() {
        List<Student> studentList = repo.getAllStudents();
        if (studentList.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Danh sách sinh viên đang rỗng!",
                    "Không có dữ liệu", JOptionPane.WARNING_MESSAGE);
            return;
        }

        ScholarshipDialog dlg = new ScholarshipDialog(this, studentList, logic);
        dlg.setVisible(true);
        if (!dlg.isChecked()) return;

        List<Student> result = logic.consideringScholarships(
                studentList, dlg.getScholarShipList(), dlg.getStudentClass());
        updateTable(result);
        JOptionPane.showMessageDialog(this,
                "Đã xét học bổng xong cho lớp " + dlg.getStudentClass() +
                        "! (" + result.size() + " sinh viên trong lớp)");
    }

    private void sortStudentByID() {
        List<Student> sortedStudents = studentManager.studentSortById(repo.getAllStudents());
        updateTable(sortedStudents);
        JOptionPane.showMessageDialog(this, "Đã sắp xếp sinh viên theo MSSV!");
    }

    private void saveFileStudent() {
        List<Student> students = repo.getAllStudents();
        if (students.isEmpty()) {

            JOptionPane.showMessageDialog(this, "Danh sách sinh viên đang rỗng!",
                    "Không có dữ liệu", JOptionPane.WARNING_MESSAGE);
            return;
        }
        SavingFileStudentDialog save = new SavingFileStudentDialog();
        if (!save.chooseFilePath(this)) {
            return;
        }
        save.saveStudentListByCsv(students);
        JOptionPane.showMessageDialog(this,
                "Đã lưu danh sách về " + save.getFilePath());
    }

    private void changeStudentInfo() {
        int selectedRow = table.getSelectedRow();
        while (true) {

            if (selectedRow < 0) {
                return;
            }

            String id = String.valueOf(model.getValueAt(selectedRow, 0));
            Student oldStudentInfo = studentManager.findStudentById(repo, id);

            if (oldStudentInfo == null) {
                return;
            }

            ChangingStudentInfo dlg = new ChangingStudentInfo(this, oldStudentInfo);
            dlg.setVisible(true);

            if (!dlg.isChecked()) {
                return;
            }

            Student newStudentInfo = dlg.getResult();

            Student studentDuplicate = studentManager.findStudentById(repo, newStudentInfo.getId());
            if (studentDuplicate != null && !studentDuplicate.getId().equals(oldStudentInfo.getId())) {
                JOptionPane.showMessageDialog(this,
                        "MSSV mới đã tồn tại trong danh sách!",
                        "Trùng MSSV", JOptionPane.WARNING_MESSAGE);
                continue;
            }

            List<Student> updatedList = repo.updateStudent(oldStudentInfo.getId(), newStudentInfo);
            updateTable(updatedList);
            JOptionPane.showMessageDialog(this, "Đã cập nhật thông tin sinh viên!");
            return;
        }
    }

    private void deleteStudentInfo(){
        int selectedRow = table.getSelectedRow();
        List<Student> oldList = repo.getAllStudents();

        if (selectedRow < 0) {
            return;
        }

        Student studentChose = oldList.get(selectedRow);
        DeletingStudentDialog dlg = new DeletingStudentDialog(this, studentChose);
        dlg.setVisible(true);

        if (!dlg.isChecked()) {
            return;
        }

        String id = studentChose.getId();
        List<Student> newList = repo.deleteAStudent(id);

        updateTable(newList);
        JOptionPane.showMessageDialog(this, "Đã xóa sinh viên này ra khỏi danh sách!");

    }

    private void updateTable(List<Student> List) {
        model.setRowCount(0);
        for (Student sv : List) {
            model.addRow(new Object[]{
                    sv.getId(),
                    sv.getName(),
                    sv.getClassRoom(),
                    sv.getGender(),
                    sv.getGpa(),
                    sv.getTrainingPoint(),
                    sv.getCredits(),
                    sv.getScholarshipName() == null ? "Không có" : sv.getScholarshipName()
            });
        }
    }
}
