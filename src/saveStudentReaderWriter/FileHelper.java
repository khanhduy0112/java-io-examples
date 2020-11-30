package saveStudentReaderWriter;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileHelper {

    public static void exportStudents(String desPath, List<Student> students, String charset) throws IOException {
        File file = new File(desPath);
        PrintWriter writer = new PrintWriter(file, charset);
        Student student;
        for (int i = 0; i < students.size(); i++) {
            student = students.get(i);
            writer.print(student.getId() + "\t" + student.getName() + "\t" + student.getAvg());
            writer.print("\n");
        }
        writer.flush();
        writer.close();
    }

    public static void importStudents(String path, String charset) throws IOException {
        File file = new File(path);
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), charset));
        String student;
        while ((student = reader.readLine()) != null) {
            System.out.println(student);
        }
    }

    public static void main(String[] args) throws IOException {
        List<Student> studentList = new ArrayList<>();
        studentList.add(new Student(1812323, "Nguyen Van B", 9.8));
        studentList.add(new Student(3292323, "Le Van An", 5.6));
        studentList.add(new Student(2398899, "Bui Van Can", 9.5));

        final String charset = "UTF-8";
        final String desPath = "E:\\Net programing\\lab\\bai16\\students.txt";
        FileHelper.exportStudents(desPath, studentList, charset);
        FileHelper.importStudents(desPath, charset);
    }

}
