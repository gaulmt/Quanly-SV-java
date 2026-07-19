
import repository.StudentRepository;
import model.Student;
import logic.Logic;
import ui.UI;

public class Main {
    public static void main(String[] args) {
        StudentRepository repo = new StudentRepository();

        repo.add_Student(new Student("SV001", "Nguyễn Văn A", "CNTT1", "Nam", 3.85, 95, 20));
        repo.add_Student(new Student("SV002", "Trần Thị B", "CNTT1", "Nữ", 3.90, 85, 22));
        repo.add_Student(new Student("SV003", "Lê Văn C", "CNTT1", "Nam", 3.70, 92, 19));
        repo.add_Student(new Student("SV004", "Phạm Minh D", "CNTT1", "Nam", 3.55, 88, 20));
        repo.add_Student(new Student("SV005", "Hoàng Lan E", "ATTT2", "Nữ", 3.10, 82, 18));
        repo.add_Student(new Student("SV006", "Vũ Hoàng F", "ATTT2", "Nam", 2.40, 90, 20));
        repo.add_Student(new Student("SV007", "Đỗ Anh G", "CNTT1", "Nam", 3.70, 92, 21));



        Logic logic = new Logic();
        UI menu = new UI();
        menu.run(repo, logic);


    }
}