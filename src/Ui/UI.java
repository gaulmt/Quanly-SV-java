

package Ui;

import Controller.ApprovalScholarshipLogic;
import Repository.StudentManager;

import javax.swing.*;

public class UI {

    public void run(StudentManager repo, ApprovalScholarshipLogic logic) {
        SwingUtilities.invokeLater(() -> {
            try {

                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

            } catch (Exception ignored) {
            }
            new StudentFrame(repo, logic).setVisible(true);
        });
    }
}
