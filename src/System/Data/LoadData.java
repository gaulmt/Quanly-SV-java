package System.Data;

import model.Student;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class LoadData {

    public List<Student> load(String path){

        File file = new File(path);
        List<Student> result = new ArrayList<>();
        if(!file.exists()){
            System.out.println("file không tồn tại!");
            return result;
        }

        try(BufferedReader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8)
        )){
        String line;
        while((line = reader.readLine() ) != null){
            if(line.isBlank()) break;

            try{
                Student sv = getStudentInfo(line);
                result.add(sv);
            }catch (Exception e){
                System.out.println("Danh sách đang rỗng");
            }


        }
        }catch (IOException e){
            System.out.println("Không mở được file!");
        }
        return result;
    }

    private Student getStudentInfo(String line) {
        String[] s = line.split(",");

        String id=                 s[0];
        String name=               s[1];
        String classRoom=          s[2];
        String gender=             s[3];
        Double gpa=                Double.parseDouble(s[4].trim());
        Integer trainingPoint=     Integer.parseInt(s[5].trim());
        Integer credits=           Integer.parseInt(s[6].trim());
        String scholarshipName=    s[7];

        Student sv = new Student(id, name, classRoom, gender, gpa, trainingPoint, credits);
        return sv;
    }
}
