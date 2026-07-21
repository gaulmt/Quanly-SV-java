package model;

import java.util.ArrayList;
import java.util.List;

public class ScholarshipPackage {
    private String name;
    private Double value;
    private Double minGpa;
    private Integer minTrainingPoint;
    private Integer quota;

    //constructor
    public ScholarshipPackage() {
    }

    public ScholarshipPackage(String name, Double value, Double minGpa, Integer minTrainingPoint, Integer quota) {
        this.name = name;
        this.value = value;
        this.minGpa = minGpa;
        this.minTrainingPoint = minTrainingPoint;
        this.quota = quota;
    }

    //getter
    public String getName() {
        return name;
    }

    public Double getValue() {
        return value;
    }

    public Integer getMinTrainingPoint() {
        return minTrainingPoint;
    }

    public Double getMinGpa() {
        return minGpa;
    }

    public Integer getQuota() {
        return quota;
    }

    //setter
    public void setMinGpa(Double minGpa) {
        this.minGpa = minGpa;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMinTrainingPoint(Integer minTrainingPoint) {
        this.minTrainingPoint = minTrainingPoint;
    }

    public void setQuota(Integer quota) {
        this.quota = quota;
    }

    public static List<ScholarshipPackage> getScholarshipList() {
        List<ScholarshipPackage> list = new ArrayList<>();

        list.add(new ScholarshipPackage("xuất sắc", 36000000.0, 3.6, 90, 0));
        list.add(new ScholarshipPackage("giỏi", 18000000.0, 3.2, 80, 0));
        list.add(new ScholarshipPackage("khá", 6700000.0, 2.5, 75, 0));
        return list;
    }
}
