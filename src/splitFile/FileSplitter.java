package splitFile;

import java.io.*;

public class FileSplitter {

    public static void split(String filePath, long size) throws IOException {
        File file = new File(filePath);
        System.out.println(file.getAbsolutePath());
        int count = (int) (file.length() / size);
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
        for (int i = 0; i < count; i++) {
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(filePath + i + ".txt"));
            for (int j = 0; j < size; j++) {
                bos.write(bis.read());
            }
            bos.close();
        }
        int remain = (int) (file.length() - (count * size));
        System.out.println(count * size);
        System.out.println(remain);
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(filePath + (count + 1) + ".txt"));
        for (int i = 0; i < remain; i++) {
            bos.write(bis.read());
        }
        bos.close();
        bis.close();
    }

    public static void main(String[] args) throws IOException {
        final String path = "E:\\Net programing\\lab\\split\\tet.txt";
        FileSplitter.split(path, 3*1024);

    }

}
