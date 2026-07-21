package model;

public class Student {
    private String id;
    private String name;
    private String classRoom;
    private String gender;
    private Double gpa;
    private Integer trainingPoint;
    private Integer credits;
    private String scholarshipName;

    public Student(String id, String name, String classRoom, String gender, Double gpa, Integer trainingPoint, Integer credits) {
        this.id = id;
        this.name = name;
        this.classRoom = classRoom;
        this.gender = gender;
        this.gpa = gpa;
        this.trainingPoint = trainingPoint;
        this.credits = credits;
    }

    public String getScholarshipName() {
        return scholarshipName;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getClassRoom() {
        return classRoom;
    }

    public String getGender() {
        return gender;
    }

    public Double getGpa() {
        return gpa;
    }

    public Integer getTrainingPoint() {
        return trainingPoint;
    }

    public Integer getCredits() {
        return credits;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTrainingPoint(Integer trainingPoint) {
        this.trainingPoint = trainingPoint;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setClassRoom(String classRoom) {
        this.classRoom = classRoom;
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

    public void setScholarshipName(String scholarshipName) {
        this.scholarshipName = scholarshipName;
    }
}
