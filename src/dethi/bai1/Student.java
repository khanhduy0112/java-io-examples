package dethi.bai1;


import java.util.List;

public class Student {

    private int id;
    private String name;
    List<Course> courseList;

    public Student() {
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", courseList=" + courseList +
                '}';
    }


    public Student(int id, String name, List<Course> courseList) {
        this.id = id;
        this.name = name;
        this.courseList = courseList;
    }

    public double calculateAvgScore(List<Course> courseList) {
        double totalScore = 0;
        int total = courseList.size();
        for (Course course :
                courseList) {
            totalScore += course.getScore();
        }
        double avg = totalScore/total;
        return Math.floor(avg*100)/100;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Course> getCourseList() {
        return courseList;
    }

    public void setCourseList(List<Course> courseList) {
        this.courseList = courseList;
    }
}
