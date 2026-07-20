package model;

public class Student {
    private String id;
    private String name;
    private String class_Room;
    private String gender;
    private Double gpa;
    private Integer traning_Point;
    private Integer credits;
    private String scholarship_Name;

    public Student(String id, String name, String classRoom, String gender, Double gpa, Integer traning_Point, Integer credits) {
        this.id = id;
        this.name = name;
        this.class_Room = classRoom;
        this.gender = gender;
        this.gpa = gpa;
        this.traning_Point = traning_Point;
        this.credits = credits;
    }


    public String getScholarship_Name() {
        return scholarship_Name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getClass_Room() {
        return class_Room;
    }

    public String getGender() {
        return gender;
    }

    public Double getGpa() {
        return gpa;
    }

    public Integer getTraning_Point() {
        return traning_Point;
    }

    public Integer getCredits() {
        return credits;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTraning_Point(Integer traning_Point) {
        this.traning_Point = traning_Point;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setClass_Room(String class_Room) {
        this.class_Room = class_Room;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setGpa(Double gpa) {
        this.gpa = gpa;
    }

    public void setCredits(Integer credits) {
        this.credits = credits;
    }

    public void setScholarship_Name(String scholarship_Name) {
        this.scholarship_Name = scholarship_Name;
    }


}
