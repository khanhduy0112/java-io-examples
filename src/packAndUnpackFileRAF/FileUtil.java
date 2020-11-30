package packAndUnpackFileRAF;

import java.io.*;

public class FileUtil {

    public static void pack(String pathFolder, String pathDes) throws IOException {
        File folder = new File(pathFolder);
        if (!folder.exists()) {
            System.out.println("Path does not exists!");
            return;
        }
        if (folder.isFile()) {
            System.out.println("Expected folder");
            return;
        }
        File[] files = folder.listFiles();
        if (files.length > 0) {
            RandomAccessFile raf = new RandomAccessFile(pathDes, "rw");
            raf.writeInt(files.length);
            int data;
            for (File f : files) {
                System.out.println(f);
                raf.writeUTF(f.getName());
                raf.writeLong(f.length());
                BufferedInputStream bis = new BufferedInputStream(new FileInputStream(f));
                while ((data = bis.read()) != -1) {
                    raf.write(data);
                }
                bis.close();
            }
            raf.close();
        }
        System.out.println("Completed!");
    }

    public static void unPack(String pathSourceFile, String pathFolder, String name) throws IOException {
        RandomAccessFile raf = new RandomAccessFile(pathSourceFile, "rw");
        int countFile = raf.readInt();
        for (int i = 0; i < countFile; i++) {
            String fName = raf.readUTF();
            long fSize = raf.readLong();
            long filePointer = raf.getFilePointer();
            if (name.equals(fName)) {
                BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(pathFolder + "\\" + fName + ".txt"));
                //importaint thing
                for (int k = 0; k < fSize; k++) {
                    bos.write(raf.read());
                }
                bos.close();
            } else {
                raf.seek(filePointer + fSize);
            }
        }
        raf.close();

    }

    public static void main(String[] args) throws IOException {
        final String pathFolder = "E:\\Net programing\\lab\\pack un pack";
        final String pathDes = "E:\\Net programing\\lab\\result\\ltm.ltm";
        FileUtil.pack(pathFolder, pathDes);
        FileUtil.unPack(pathDes, pathFolder, "f.txt");
    }

}
