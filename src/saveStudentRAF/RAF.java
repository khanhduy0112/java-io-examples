package saveStudentRAF;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

public class RAF {

    public void save(String destPath, List<Student> studentList) throws IOException {
        File file = new File(destPath);
        RandomAccessFile raf = new RandomAccessFile(file, "rw");
        raf.writeInt(studentList.size());
        for (int i = 0; i < studentList.size(); i++) {
            raf.writeLong(0);
        }

        long stPos = raf.getFilePointer();
        for (int i = 0; i < studentList.size(); i++) {
            raf.seek(4 + 8 * i);
            raf.writeLong(stPos);
            raf.seek(stPos);
            Student student = studentList.get(i);
            raf.writeInt(student.getId());
            raf.writeUTF(student.getName());
            raf.writeDouble(student.getAvg());
            stPos = raf.getFilePointer();
        }
        raf.close();
    }

    public List<Student> read(String desPath) throws IOException {
        File file = new File(desPath);
        RandomAccessFile raf = new RandomAccessFile(file, "rw");
        List<Student> studentList = new ArrayList<>();
        int countSt = raf.readInt();
        System.out.println(countSt);
        raf.seek(4 + 8 * countSt);
        for (int i = 0; i < countSt; i++) {
            Student student = new Student();
            student.setId(raf.readInt());
            student.setName(raf.readUTF());
            student.setAvg(raf.readDouble());
            studentList.add(student);
            System.out.println(student.toString());
        }
        raf.close();
        return studentList;
    }

    public Student find(String path, int pos) throws IOException {
        File file = new File(path);
        RandomAccessFile raf = new RandomAccessFile(file, "rw");
        Student student = null;
        try {
            raf.seek(4 + (8 * pos));
            long l = raf.readLong();
            raf.seek(l);
            student = new Student();
            student.setId(raf.readInt());
            student.setName(raf.readUTF());
            student.setAvg(raf.readDouble());
            System.out.println(student.toString());
            return student;
        } catch (IOException e) {
            throw new IOException("Khong tim thay sinh vien \n");
        }
    }

    public void update(String path, int pos, String name) throws IOException {
        List<Student> studentList = this.read(path);
        Student student = this.find(path, pos);
        student.setName(name);
        for (int i = 0; i < studentList.size(); i++) {
            if (studentList.get(i).getId() == student.getId()) {
                studentList.get(i).setName(student.getName());
            }
        }
        this.save(path, studentList);


    }

    public static void main(String[] args) throws IOException {
        final String desPath = "E:\\Net programing\\lab\\luyentap\\students.txt";
        List<Student> studentList = new ArrayList<>();
        studentList.add(new Student(18130060, "NGuyen Khanh Duy", 8.9));
        studentList.add(new Student(18189899, "NGuyen Van Kha", 2.2));
//        new RAF().save(desPath, studentList);
//        new RAF().read(desPath);
//        new RAF().find(desPath, 1);
        new RAF().update(desPath, 0, "moi update");
        System.out.println("_____________________");
        new RAF().read(desPath);
    }
}
