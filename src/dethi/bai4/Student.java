package dethi.bai4;

import java.util.List;

public class Student {
    private int id;
    private String name;
    private List<Course> courseList;

    public Student() {
    }

    public Student(int id, String name, List<Course> courseList) {
        this.id = id;
        this.name = name;
        this.courseList = courseList;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", courseList=" + courseList +
                '}';
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
