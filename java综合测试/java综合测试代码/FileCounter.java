package ����;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FileCounter {
    //���ڴ����ҵ���ÿһ���ļ�
    ArrayList<File> fileList;
    //��Ŀ¼
    File root;

    public FileCounter(String pathName) {
        root = new File(pathName);
        fileList = new ArrayList<>();
    }

    //�ݹ��㷨�����ļ�
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

    //ͳ���ļ��������ܵĴ�С
    public void countFiles() throws IOException {
        long totalSize = 0;
    	FileWriter fw = new FileWriter("C:\\Users\\Angest\\Desktop\\code\\eclipse\\eclipse-workspace\\result\\result1.txt");
        System.out.println("�ļ���:" + fileList.size());
        fw.write("�ļ���:" + fileList.size()+"\n");
        for (int i = 0; i < fileList.size(); i++) {
            totalSize += fileList.get(i).length();//�����ɴ��ļ��ĳ��ȣ��ֽ�Ϊ��λ
        }
        System.out.println("�ļ����ֽ���:" + totalSize +"Byte");
        fw.write("�ļ����ֽ���:" + totalSize +"Byte");
        fw.close();
    }

    
    //����
    public static void main(String[] args) throws IOException {
        String pathName = "C:\\Users\\Angest\\Desktop\\code";
        FileCounter counter = new FileCounter(pathName);
        counter.searchFiles();
        counter.countFiles();
    }

}
