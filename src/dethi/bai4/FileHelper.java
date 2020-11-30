package dethi.bai4;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileHelper {

    public void write(String path, List<Student> studentList) throws IOException {
        File file = new File(path);
        DataOutputStream dos = new DataOutputStream(new FileOutputStream(file));
        dos.writeInt(studentList.size());
        for (Student student :
                studentList) {
            dos.writeInt(student.getId());
            dos.writeUTF(student.getName());
            dos.writeInt(student.getCourseList().size());
            for (Course course :
                    student.getCourseList()) {
                dos.writeUTF(course.getName());
                dos.writeDouble(course.getScore());
            }
        }
        dos.flush();
        dos.close();
    }

    public void read(String path) throws IOException {
        File file = new File(path);
        if (!file.exists()) {
            return;
        }
        DataInputStream dis = new DataInputStream(new FileInputStream(file));
        Student student;
        int countSt = dis.readInt();
        for (int i = 0; i < countSt; i++) {
            student = new Student();
            student.setId(dis.readInt());
            student.setName(dis.readUTF());
            int countCourse = dis.readInt();
            List<Course> courseList = new ArrayList<>();
            Course course;
            for (int j = 0; j < countCourse; j++) {
                course = new Course();
                course.setName(dis.readUTF());
                course.setScore(dis.readDouble());
                courseList.add(course);
            }
            student.setCourseList(courseList);
            System.out.println(student.toString());
        }
        dis.close();

    }

    public static void main(String[] args) throws IOException {

        List<Course> courseList = new ArrayList<>();
        courseList.add(new Course("Hoa", 9.8));
        courseList.add(new Course("Sinh", 8.6));
        List<Course> courseList1 = new ArrayList<>();

        List<Student> studentList = new ArrayList<>();
        studentList.add(new Student(181200399, "Nguyen Khanh Duy", courseList));
        studentList.add(new Student(832923, "Nguyen Khanh Huyen", courseList1));

        FileHelper fileHelper = new FileHelper();

        String path = "E:\\Net programing\\dethi\\bai4\\students.txt";
        fileHelper.write(path, studentList);
        fileHelper.read(path);
    }
}
