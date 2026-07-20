package ui;

import logic.Logic;
import model.ScholarshipPackage;
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
    private final Logic logic;

    private final String[] cot = {"MSSV", "Họ và tên", "Lớp", "Giới tính", "GPA", "Điểm rèn luyện", "Tín chỉ", "Học bổng"};
    private DefaultTableModel model;
    private JTable table;

    public StudentFrame(StudentRepository repo, Logic logic) {
        super("Quản lý sinh viên");
        this.repo = repo;
        this.logic = logic;
        initUI();
        capNhatBang();
    }

    private void initUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 600);
        setMinimumSize(new Dimension(850, 450));
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(8, 8));

        JPanel noiDung = new JPanel(new BorderLayout(8, 8));
        noiDung.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        noiDung.add(taoToolbar(), BorderLayout.NORTH);

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
        noiDung.add(new JScrollPane(table), BorderLayout.CENTER);

        add(noiDung, BorderLayout.CENTER);
    }

    private JPanel taoToolbar() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 6, 0));

        JButton btnThem = new JButton("Thêm sinh viên");
        JButton btnXetHocBong = new JButton("Xét học bổng");
        JButton btnSapXep = new JButton("Sắp xếp theo MSSV");
        JButton btnLamMoi = new JButton("Làm mới");

        btnThem.addActionListener(e -> themSinhVien());
        btnXetHocBong.addActionListener(e -> xetHocBong());
        btnSapXep.addActionListener(e -> sapXep());
        btnLamMoi.addActionListener(e -> capNhatBang());

        panel.add(btnThem);
        panel.add(btnXetHocBong);
        panel.add(btnSapXep);
        panel.add(btnLamMoi);
        return panel;
    }

    private void themSinhVien() {
        AddStudentDialog dlg = new AddStudentDialog(this);
        dlg.setVisible(true);
        if (dlg.isDaXacNhan()) {
            if (repo.get_Student_By_Id(dlg.getKetQua().getId()) != null) {
                JOptionPane.showMessageDialog(this,
                        "MSSV này đã tồn tại trong danh sách!",
                        "Trùng MSSV", JOptionPane.WARNING_MESSAGE);
                return;
            }
            repo.add_Student(dlg.getKetQua());
            capNhatBang();
        }
    }

    private void xetHocBong() {
        List<Student> danhSach = repo.get_All_Students();
        List<ScholarshipPackage> danhSachHocBong = ScholarshipPackage.get_Scholarship_List();
        logic.Considering_Scholarships(danhSach, danhSachHocBong);
        capNhatBang();
        JOptionPane.showMessageDialog(this, "Đã xét học bổng xong!");
    }

    private void sapXep() {
        logic.student_Sort_By_Id(repo.get_All_Students());
        capNhatBang();
    }

    private void capNhatBang() {
        model.setRowCount(0);
        for (Student sv : repo.get_All_Students()) {
            model.addRow(new Object[]{
                    sv.getId(),
                    sv.getName(),
                    sv.getClass_Room(),
                    sv.getGender(),
                    sv.getGpa(),
                    sv.getTraning_Point(),
                    sv.getCredits(),
                    sv.getScholarship_Name() == null ? "" : sv.getScholarship_Name()
            });
        }
    }
}
