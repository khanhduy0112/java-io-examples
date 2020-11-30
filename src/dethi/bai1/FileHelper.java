package dethi.bai1;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class FileHelper {

    public void write(String desPath, List<Student> studentList, String charset) throws FileNotFoundException, UnsupportedEncodingException {
        File file = new File(desPath);
        PrintWriter writer = new PrintWriter(file, charset);
        for (Student student : studentList) {
            writer.print(student.getId() + "\t" + student.getName() + "\t");
            for (Course course :
                    student.getCourseList()) {
                writer.print(course.getName() + "\t" + course.getScore() + "\t");
            }
            writer.print("\n");
        }
        writer.flush();
        writer.close();
    }

    public void read(String path, String charset) throws IOException {
        File file = new File(path);
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), charset));
        List<Student> studentList = new ArrayList<>();
        Student student;
        String data;
        while ((data = reader.readLine()) != null) {
            StringTokenizer stringTokenizer = new StringTokenizer(data, "\t");
            while (stringTokenizer.hasMoreTokens()) {
                student = new Student();
                student.setId(Integer.parseInt(stringTokenizer.nextToken()));
                student.setName(stringTokenizer.nextToken());
                List<Course> courseList = new ArrayList<>();
                while (stringTokenizer.hasMoreTokens()) {
                    courseList.add(new Course(stringTokenizer.nextToken(), Double.parseDouble(stringTokenizer.nextToken())));
                }
                student.setCourseList(courseList);
                studentList.add(student);
                System.out.println(student.toString() + "Diem trung binh la : " + student.calculateAvgScore(courseList));
            }
        }
//        System.out.println(studentList.toString());
    }

    public static void main(String[] args) throws IOException {
        String path = "E:\\Net programing\\dethi\\bai1\\students.txt";
        List<Student> studentList = new ArrayList<>();
        List<Course> courseList = new ArrayList<>();
        courseList.add(new Course("Hoa", 10));
        courseList.add(new Course("Sinh", 6));
        courseList.add(new Course("Vat Li", 9));
        studentList.add(new Student(18130060, "NGuyen Khanh Duy", courseList));
        studentList.add(new Student(18132222, "NGuyen Van Heo", courseList));
        FileHelper fileHelper = new FileHelper();
        final String charset = "UTF-8";
//        fileHelper.write(path, studentList, charset);
        fileHelper.read(path, charset);
    }
}
