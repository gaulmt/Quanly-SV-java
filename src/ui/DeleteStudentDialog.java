package ui;

import model.Student;

import javax.swing.*;
import java.awt.*;

public class DeleteStudentDialog extends JDialog {

    private boolean checked = false;

    public DeleteStudentDialog(Frame owner, Student student) {
        super(owner, "Xác nhận xóa sinh viên", true);
        initUI(student);
    }

    private void initUI(Student student) {
        setLayout(new BorderLayout(10, 10));
        setMinimumSize(new Dimension(380, 150));
        setLocationRelativeTo(getOwner());

        JPanel messagePanel =new JPanel();
        messagePanel.setLayout(new BoxLayout(messagePanel, BoxLayout.Y_AXIS));
        messagePanel.setBorder(BorderFactory.createEmptyBorder(20, 15, 10, 15));


        JLabel sideContent = new JLabel("Bạn có chắc muốn xóa sinh viên: ");
        sideContent.setAlignmentX(Component.CENTER_ALIGNMENT);
        sideContent.setFont(new Font("Arial", Font.PLAIN, 15));

        JLabel mainContent = new JLabel(student.getName());
        mainContent.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainContent.setFont(new Font("Arial", Font.PLAIN, 20));

        messagePanel.add(sideContent, BorderLayout.CENTER);
        messagePanel.add(Box.createVerticalStrut(6));
        messagePanel.add(mainContent, BorderLayout.CENTER);

        add(messagePanel, BorderLayout.CENTER);
        add(buttonPanel(), BorderLayout.SOUTH);
        pack();
    }

    private JPanel buttonPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 8));

        JButton btnConfirm = new JButton("Đồng ý");
        JButton btnCancel = new JButton("Hủy");

        btnConfirm.addActionListener(e -> {
            checked = true;
            dispose();
        });

        btnCancel.addActionListener(e -> {
            checked = false;
            dispose();
        });

        panel.add(btnConfirm);
        panel.add(btnCancel);
        return panel;
    }

    public boolean isChecked() {
        return checked;
    }
}
