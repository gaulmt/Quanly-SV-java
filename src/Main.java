import model.StudentRepository;
import model.Student;
import System.logic.ApprovalScholarShipLogic;
import ui.UI;

public class Main {
    public static void main(String[] args) {
        StudentRepository repo = new StudentRepository();

        ApprovalScholarShipLogic approvalScholarShipLogic = new ApprovalScholarShipLogic();
        UI menu = new UI();
        menu.run(repo, approvalScholarShipLogic);
    }
}