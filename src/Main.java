import model.StudentRepository;
import model.Student;
import logic.ApprovalScholarShipLogic;
import ui.UI;

public class Main {
    public static void main(String[] args) {
        StudentRepository repo = new StudentRepository();

        repo.addStudent(new Student("SV001", "Nguyễn Văn A", "CNTT1", "Nam", 3.85, 95, 20));
        repo.addStudent(new Student("SV002", "Trần Thị B", "CNTT1", "Nữ", 3.90, 85, 22));
        repo.addStudent(new Student("SV003", "Lê Văn C", "CNTT2", "Nam", 3.70, 92, 19));
        repo.addStudent(new Student("SV004", "Phạm Minh D", "CNTT2", "Nam", 3.55, 88, 20));
        repo.addStudent(new Student("SV005", "Hoàng Lan E", "ATTT", "Nữ", 3.10, 82, 18));
        repo.addStudent(new Student("SV006", "Vũ Hoàng F", "ATTT", "Nam", 2.40, 90, 20));
        repo.addStudent(new Student("SV007", "Đỗ Anh G", "CNTT1", "Nam", 3.70, 92, 21));

        ApprovalScholarShipLogic approvalScholarShipLogic = new ApprovalScholarShipLogic();
        UI menu = new UI();
        menu.run(repo, approvalScholarShipLogic);
    }
}