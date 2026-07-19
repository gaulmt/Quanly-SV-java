package model;


import java.util.ArrayList;
import java.util.List;

public class ScholarshipPackage {
    private String name;
    private Double value;
    private Double min_Gpa;
    private Integer min_Traning_Point;
    private Integer quota;


    //constructor
    public ScholarshipPackage() {
    }
    public ScholarshipPackage(String name, Double value, Double min_Gpa, Integer min_Traning_Point, Integer quota) {
        this.name = name;
        this.value = value;
        this.min_Gpa = min_Gpa;
        this.min_Traning_Point = min_Traning_Point;
        this.quota = quota;
    }
    //getter

    public String getName() {
        return name;
    }

    public Double getValue() {
        return value;
    }

    public Integer getMin_Traning_Point() {
        return min_Traning_Point;
    }

    public Double getMin_Gpa() {
        return min_Gpa;
    }

    public Integer getQuota() {
        return quota;
    }

    //setter

    public void setMin_Gpa(Double min_Gpa) {
        this.min_Gpa = min_Gpa;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMin_Traning_Point(Integer min_Traning_Point) {
        this.min_Traning_Point = min_Traning_Point;
    }

    public void setQuota(Integer quota) {
        this.quota = quota;
    }


    public  static List<ScholarshipPackage>
            get_Scholarship_List(){
        List<ScholarshipPackage> list = new ArrayList<>();

        list.add(new ScholarshipPackage("xuất sắc",36000000.0,3.6,90,18));
        list.add(new ScholarshipPackage("giỏi",18000000.0,3.2,80,36));
        list.add(new ScholarshipPackage("khá",6700000.0,2.5,75,67));
        return list;
    }
}
