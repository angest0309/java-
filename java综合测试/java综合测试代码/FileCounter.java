package 面试;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FileCounter {
    //用于储存找到的每一个文件
    ArrayList<File> fileList;
    //根目录
    File root;

    public FileCounter(String pathName) {
        root = new File(pathName);
        fileList = new ArrayList<>();
    }

    //递归算法查找文件
    public void searchFiles() {
        File[] files = root.listFiles();
        int length = files.length;
        for (int i = 0; i < length; i++) {
            if (files[i].isDirectory()) {
                root = files[i];
                searchFiles();
            } else {
                fileList.add(files[i]);
            }
        }
    }

    //统计文件个数和总的大小
    public void countFiles() throws IOException {
        long totalSize = 0;
    	FileWriter fw = new FileWriter("C:\\Users\\Angest\\Desktop\\code\\eclipse\\eclipse-workspace\\result\\result1.txt");
        System.out.println("文件数:" + fileList.size());
        fw.write("文件数:" + fileList.size()+"\n");
        for (int i = 0; i < fileList.size(); i++) {
            totalSize += fileList.get(i).length();//返回由此文件的长度，字节为单位
        }
        System.out.println("文件总字节数:" + totalSize +"Byte");
        fw.write("文件总字节数:" + totalSize +"Byte");
        fw.close();
    }

    
    //测试
    public static void main(String[] args) throws IOException {
        String pathName = "C:\\Users\\Angest\\Desktop\\code";
        FileCounter counter = new FileCounter(pathName);
        counter.searchFiles();
        counter.countFiles();
    }

}
