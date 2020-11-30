package dethi.bai2;

import java.io.*;
import java.nio.Buffer;

public class FileHelper {

    public boolean pack(String sFolder, String dFile) throws IOException {
        File file = new File(sFolder);
        if (!file.exists()) {
            System.out.println("Folder khong ton tai");
            return false;
        }
        if (file.isFile()) {
            System.out.println("Chi nhan vao folder");
            return false;
        }
        int countFile = file.listFiles().length;
        File[] subFiles = file.listFiles();
        RandomAccessFile raf = new RandomAccessFile(dFile, "rw");
        raf.writeInt(countFile);
        for (File f : subFiles) {
            raf.writeUTF(f.getName());
            raf.writeLong(f.length());
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(f));
            int data;
            while ((data = bis.read()) != -1) {
                raf.write(data);
            }
            bis.close();
        }
        raf.close();
        return true;
    }

    public void unPack(String sFile, String fileName, String dPath) throws IOException {
        RandomAccessFile raf = new RandomAccessFile(sFile, "rw");
        int countFile = raf.readInt();
        for (int i = 0; i < countFile; i++) {
            String fName = raf.readUTF();
            long fSize = raf.readLong();
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(dPath + "\\" + fName));
            for (int j = 0; j < fSize; j++) {
                bos.write(raf.read());
            }
            bos.close();
        }
        raf.close();
    }

    public static void main(String[] args) throws IOException {
        FileHelper fileHelper = new FileHelper();
        final String sFolder = "E:\\Net programing\\dethi\\bai2";
        final String dFile = "E:\\Net programing\\dethi\\result\\pack.txt";
        final String dPaht = "E:\\Net programing\\dethi";
        fileHelper.pack(sFolder, dFile);
        fileHelper.unPack(dFile, null, dPaht);

    }
}
