package saveStudentObjectInputOutput;

import java.io.Serializable;

public class Course implements Serializable {

    private String name;
    private double score;

    public Course() {
    }

    public Course(String name, double score) {
        this.name = name;
        this.score = score;
    }

    @Override
    public String toString() {
        return "Course{" +
                "name='" + name + '\'' +
                ", score=" + score +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }
}
