package saveStudentRAF;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileHelper {

    public static void write(String desFilePath, List<Student> studentList) throws IOException {
        RandomAccessFile raf = new RandomAccessFile(desFilePath, "rw");
        int countStudents = studentList.size();
        raf.writeInt(countStudents);
        for (int i = 0; i < countStudents; i++) {
            raf.writeLong(i);
        }
        //lay file pointer cua vi tri student dau tien
        long filePointer = raf.getFilePointer();
        for (int i = 0; i < countStudents; i++) {
            //tim kiem position index cua student trong header
            raf.seek(4 + 8 * i);
            //ghi lai file pointer luc nay
            raf.writeLong(filePointer);
            //quay lai file pointer cua student luc nay
            raf.seek(filePointer);
            Student student = studentList.get(i);
            raf.writeInt(student.getId());
            raf.writeUTF(student.getName());
            raf.writeDouble(student.getAvg());
            //update lai file pointer cua student 2
            filePointer = raf.getFilePointer();
        }
        raf.close();
        System.out.println("Completed!");
    }

    public static void read(String path, int studentPos) throws IOException {
        RandomAccessFile raf = new RandomAccessFile(path, "rw");
        raf.seek(4 + (8 * studentPos));
        long pos = raf.readLong();
        raf.seek(pos);
        int id = raf.readInt();
        String name = raf.readUTF();
        double avg = raf.readDouble();
        Student student = new Student(id, name, avg);
        System.out.println(student.toString());
        raf.close();
    }

    public static void main(String[] args) throws IOException {
        Student student = new Student(18130060, "Nguyen Khanh Duy", 10.0);
        Student student2 = new Student(1812420, "Nguyen  Duy", 7.0);
        List<Student> studentList = new ArrayList<>();
        studentList.add(student);
        studentList.add(student2);

        final String desFilePath = "E:\\Net programing\\lab\\raf students\\student.txt";

        FileHelper.write(desFilePath, studentList);
        FileHelper.read(desFilePath, 0);
    }
}
