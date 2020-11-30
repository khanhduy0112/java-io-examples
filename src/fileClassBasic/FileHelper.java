package fileClassBasic;

import java.io.File;


public class FileHelper {

    public static void dirTree(String folderPath) {
        File file = new File(folderPath);
        File[] files = file.listFiles();
        for (File f : files) {
            if (f.isFile()) {
                System.out.println(f.getName());
            }
            if (f.isDirectory()) {
                System.out.println(f.getName() + "+");
                dirTree(f.getAbsolutePath());
            }
        }
    }

    public static void main(String[] args) {
        FileHelper.dirTree("E:\\test");
    }
}
