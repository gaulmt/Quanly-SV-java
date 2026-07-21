package model;

public class Points {
    private String studentId;
    private String subjectName;
    private Double gpa;
    private Integer trainingPoint;
    private Double regularGrade1;
    private Double regularGrade2;
    private Double regularLabGrade1;
    private Double regularLabGrade2;
    private Double midtermGrade;
    private Double midtermLabGrade;
    private Double finalGrade;
    private Double finalLabGrade;

    public Points(String studentId, String subjectName, Integer trainingPoint, Double gpa, Double regularGrade1, Double regularGrade2, Double regularLabGrade1, Double regularLabGrade2, Double midtermGrade, Double midtermLabGrade, Double finalGrade, Double finalLabGrade) {
        this.studentId = studentId;
        this.subjectName = subjectName;
        this.trainingPoint = trainingPoint;
        this.gpa = gpa;
        this.regularGrade1 = regularGrade1;
        this.regularGrade2 = regularGrade2;
        this.regularLabGrade1 = regularLabGrade1;
        this.regularLabGrade2 = regularLabGrade2;
        this.midtermGrade = midtermGrade;
        this.midtermLabGrade = midtermLabGrade;
        this.finalGrade = finalGrade;
        this.finalLabGrade = finalLabGrade;
    }

    // getter
    public String getStudentId() {
        return studentId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public Double getGpa() {
        return gpa;
    }

    public Integer getTrainingPoint() {
        return trainingPoint;
    }

    public Double getRegularGrade1() {
        return regularGrade1;
    }

    public Double getRegularGrade2() {
        return regularGrade2;
    }

    public Double getRegularLabGrade1() {
        return regularLabGrade1;
    }

    public Double getRegularLabGrade2() {
        return regularLabGrade2;
    }

    public Double getMidtermGrade() {
        return midtermGrade;
    }

    public Double getFinalGrade() {
        return finalGrade;
    }

    public Double getMidtermLabGrade() {
        return midtermLabGrade;
    }

    public Double getFinalLabGrade() {
        return finalLabGrade;
    }

    // setter
    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public void setGpa(Double gpa) {
        this.gpa = gpa;
    }

    public void setTrainingPoint(Integer trainingPoint) {
        this.trainingPoint = trainingPoint;
    }

    public void setRegularGrade1(Double regularGrade1) {
        this.regularGrade1 = regularGrade1;
    }

    public void setRegularGrade2(Double regularGrade2) {
        this.regularGrade2 = regularGrade2;
    }

    public void setRegularLabGrade2(Double regularLabGrade2) {
        this.regularLabGrade2 = regularLabGrade2;
    }

    public void setMidtermGrade(Double midtermGrade) {
        this.midtermGrade = midtermGrade;
    }

    public void setRegularLabGrade1(Double regularLabGrade1) {
        this.regularLabGrade1 = regularLabGrade1;
    }

    public void setMidtermLabGrade(Double midtermLabGrade) {
        this.midtermLabGrade = midtermLabGrade;
    }

    public void setFinalGrade(Double finalGrade) {
        this.finalGrade = finalGrade;
    }

    public void setFinalLabGrade(Double finalLabGrade) {
        this.finalLabGrade = finalLabGrade;
    }
}
