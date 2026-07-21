package ui;

import model.ScholarshipPackage;
import model.Student;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

// Form Swing: chọn lớp cần xét học bổng + nhập số suất (quota) cho từng loại.

public class ScholarshipDialog extends JDialog {

    private JComboBox<String> cbLop;
    private JTextField txtXuatSac, txtGioi, txtKha;

    private boolean daXacNhan = false;
    private String lopDaChon;
    private List<ScholarshipPackage> danhSachHocBong;

    public ScholarshipDialog(Frame owner, List<Student> danhSachSV) {
        super(owner, "Xét học bổng theo lớp", true);
        initUI(danhSachSV);
        pack();
        setLocationRelativeTo(owner);
    }

    private void initUI(List<Student> danhSachSV) {
        // Lấy danh sách lớp không trùng lặp từ danh sách sinh viên hiện có
        List<String> danhSachLop = new ArrayList<>();
        for (Student s : danhSachSV) {
            String lop = s.getClassRoom().toUpperCase();
            if (!danhSachLop.contains(lop)) {
                danhSachLop.add(lop);
            }
        }
        danhSachLop.sort(String::compareTo);

        List<ScholarshipPackage> macDinh = ScholarshipPackage.getScholarshipList();

        JPanel form = new JPanel(new GridBagLayout());
        form.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(6, 6, 6, 6);
        c.fill = GridBagConstraints.HORIZONTAL;

        cbLop = new JComboBox<>(danhSachLop.toArray(new String[0]));
        txtXuatSac = new JTextField(String.valueOf(macDinh.get(0).getQuota()), 8);
        txtGioi = new JTextField(String.valueOf(macDinh.get(1).getQuota()), 8);
        txtKha = new JTextField(String.valueOf(macDinh.get(2).getQuota()), 8);

        int row = 0;
        addRow(form, c, row++, "Chọn lớp:", cbLop);
        addRow(form, c, row++, "Số suất học bổng xuất sắc:", txtXuatSac);
        addRow(form, c, row++, "Số suất học bổng giỏi:", txtGioi);
        addRow(form, c, row++, "Số suất học bổng khá:", txtKha);

        JButton btnXet = new JButton("Xét học bổng");
        JButton btnHuy = new JButton("Hủy");
        btnXet.addActionListener(e -> onXet(macDinh));
        btnHuy.addActionListener(e -> dispose());

        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttons.add(btnXet);
        buttons.add(btnHuy);

        setLayout(new BorderLayout());
        add(form, BorderLayout.CENTER);
        add(buttons, BorderLayout.SOUTH);
        getRootPane().setDefaultButton(btnXet);
    }

    private void addRow(JPanel form, GridBagConstraints c, int row, String label, JComponent field) {
        c.gridx = 0; c.gridy = row; c.weightx = 0;
        form.add(new JLabel(label), c);
        c.gridx = 1; c.weightx = 1;
        form.add(field, c);
    }

    private void onXet(List<ScholarshipPackage> macDinh) {
        if (cbLop.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(this, "Chưa có lớp nào trong danh sách sinh viên!",
                    "Không có dữ liệu", JOptionPane.WARNING_MESSAGE);
            return;
        }
        try {
            int quotaXuatSac = Integer.parseInt(txtXuatSac.getText().trim());
            int quotaGioi = Integer.parseInt(txtGioi.getText().trim());
            int quotaKha = Integer.parseInt(txtKha.getText().trim());

            if (quotaXuatSac < 0 || quotaGioi < 0 || quotaKha < 0) {
                JOptionPane.showMessageDialog(this, "Số suất học bổng không được là số âm!",
                        "Dữ liệu không hợp lệ", JOptionPane.WARNING_MESSAGE);
                return;
            }

            macDinh.get(0).setQuota(quotaXuatSac);
            macDinh.get(1).setQuota(quotaGioi);
            macDinh.get(2).setQuota(quotaKha);

            danhSachHocBong = macDinh;
            lopDaChon = (String) cbLop.getSelectedItem();
            daXacNhan = true;
            dispose();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Số suất học bổng phải là số nguyên!",
                    "Dữ liệu không hợp lệ", JOptionPane.WARNING_MESSAGE);
        }
    }

    public boolean isDaXacNhan() {
        return daXacNhan;
    }

    public String getLopDaChon() {
        return lopDaChon;
    }

    public List<ScholarshipPackage> getDanhSachHocBong() {
        return danhSachHocBong;
    }
}
