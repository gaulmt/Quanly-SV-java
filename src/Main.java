import Repository.StudentManager;
import Controller.ApprovalScholarshipLogic;
import Ui.UI;

public class Main {
    public static void main(String[] args) {
        StudentManager repo = new StudentManager();

        ApprovalScholarshipLogic approvalScholarShipLogic = new ApprovalScholarshipLogic();
        UI menu = new UI();
        menu.run(repo, approvalScholarShipLogic);
    }
}