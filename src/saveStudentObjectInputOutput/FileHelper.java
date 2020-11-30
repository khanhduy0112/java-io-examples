package saveStudentObjectInputOutput;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileHelper {

    public static void write(String pathDes, List<Student> students) throws IOException {
        File file = new File(pathDes);
        ObjectOutput oo = new ObjectOutputStream(new FileOutputStream(file));
        oo.writeInt(students.size());
        for (Student student : students) {
            oo.writeInt(student.getId());
            oo.writeUTF(student.getName());
            oo.writeInt(student.getCourses().size());
            for (Course course : student.getCourses()) {
                oo.writeUTF(course.getName());
                oo.writeDouble(course.getScore());
            }
        }
        oo.close();
        System.out.println("Complete!");
        System.out.println("=================================================");
    }

    public static void read(String path) throws IOException {
        System.out.println("=================================================");
        File file = new File(path);
        ObjectInput oi = new ObjectInputStream(new FileInputStream(file));
        int countStudent = oi.readInt();
        for (int i = 0; i < countStudent; i++) {
            Student student = new Student();
            student.setId(oi.readInt());
            student.setName(oi.readUTF());
            List<Course> courses = new ArrayList<>();
            int countCourse = oi.readInt();
            for (int j = 0; j < countCourse; j++)
                courses.add(new Course(oi.readUTF(), oi.readDouble()));
            student.setCourses(courses);
            System.out.println(student.toString());
        }
        oi.close();
        System.out.println("=================================================");

    }

    public static void main(String[] args) throws IOException {
        Course course = new Course("Hoa", 10);
        Course course1 = new Course("Sinh", 8);
        Course course2 = new Course("Lis", 8);
        List<Course> coursesList = new ArrayList<>();
        coursesList.add(course);
        coursesList.add(course1);
        coursesList.add(course2);
        //
        Student student = new Student(18130060, "Nguyen Khanh Duy", coursesList);
        Student student1 = new Student(1218291, "Nguyen Duy Khanh", coursesList);
        List<Student> studentList = new ArrayList<>();
        studentList.add(student);
        studentList.add(student1);
        //
        String pathDes = "E:\\Net programing\\lab\\oi oo student\\students.txt";
        FileHelper.write(pathDes, studentList);
        FileHelper.read(pathDes);
    }

}
