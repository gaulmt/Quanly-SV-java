package ui;

import logic.ApprovalScholarShipLogic;
import model.Student;
import repository.StudentRepository;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

// Cửa sổ chính: hiển thị danh sách sinh viên (JTable) + các thao tác
// Thêm sinh viên / Xét học bổng / Sắp xếp theo MSSV.
// Toàn bộ dữ liệu và xử lý nghiệp vụ vẫn nằm ở StudentRepository và Logic,
// UI chỉ gọi lại các hàm có sẵn.

public class StudentFrame extends JFrame {

    private final StudentRepository repo;
    private final ApprovalScholarShipLogic approvalScholarShipLogic;

    private final String[] cot = {"MSSV", "Họ và tên", "Lớp", "Giới tính", "GPA", "Điểm rèn luyện", "Tín chỉ", "Học bổng"};
    private DefaultTableModel model;
    private JTable table;

    public StudentFrame(StudentRepository repo, ApprovalScholarShipLogic approvalScholarShipLogic) {
        super("Quản lý sinh viên");
        this.repo = repo;
        this.approvalScholarShipLogic = approvalScholarShipLogic;
        initUI();
        updateTable();
    }

    private void initUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 600);
        setMinimumSize(new Dimension(850, 450));
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(8, 8));

        JPanel content = new JPanel(new BorderLayout(8, 8));
        content.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        content.add(toolBarMaker(), BorderLayout.NORTH);

        model = new DefaultTableModel(cot, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table = new JTable(model);
        table.setRowHeight(26);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
        content.add(new JScrollPane(table), BorderLayout.CENTER);

        add(content, BorderLayout.CENTER);
    }

    private JPanel toolBarMaker() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 6, 0));

        JButton btnAdd = new JButton("Thêm sinh viên");
        JButton btnScholarShipGiver = new JButton("Xét học bổng");
        JButton btnSortByID = new JButton("Sắp xếp theo MSSV");
        JButton btnRefresh = new JButton("Làm mới");

        btnAdd.addActionListener(e -> addNewStudent());
        btnScholarShipGiver.addActionListener(e -> scholarShipGiver());
        btnSortByID.addActionListener(e -> sortByID());
        btnRefresh.addActionListener(e -> updateTable());

        panel.add(btnAdd);
        panel.add(btnScholarShipGiver);
        panel.add(btnSortByID);
        panel.add(btnRefresh);
        return panel;
    }

    private void addNewStudent() {
        AddStudentDialog dlg = new AddStudentDialog(this);
        dlg.setVisible(true);
        if (dlg.isChecked()) {
            if (repo.getStudentById(dlg.getResult().getId()) != null) {
                JOptionPane.showMessageDialog(this,
                        "MSSV này đã tồn tại trong danh sách!",
                        "Trùng MSSV", JOptionPane.WARNING_MESSAGE);
                return;
            }
            repo.addStudent(dlg.getResult());
            updateTable();
        }

    }

    private void scholarShipGiver() {
        List<Student> List = repo.getAllStudents();
        if (List.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Danh sách sinh viên đang rỗng!",
                    "Không có dữ liệu", JOptionPane.WARNING_MESSAGE);
            return;
        }

        ScholarshipDialog dlg = new ScholarshipDialog(this, List);
        dlg.setVisible(true);
        if (!dlg.isChecked()) return;

        List<Student> result = approvalScholarShipLogic.consideringScholarships(
                List, dlg.getScholarShipList(), dlg.getStudentClass());
        updateTable();
        JOptionPane.showMessageDialog(this,
                "Đã xét học bổng xong cho lớp " + dlg.getStudentClass() +
                        "! (" + result.size() + " sinh viên trong lớp)");
    }

    private void sortByID() {
        approvalScholarShipLogic.studentSortById(repo.getAllStudents());
        updateTable();
    }

    private void updateTable() {
        model.setRowCount(0);
        for (Student sv : repo.getAllStudents()) {
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
