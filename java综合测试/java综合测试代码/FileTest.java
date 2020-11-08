package 面试;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;

public class FileTest {
    public static void main(String[] args) throws IOException {
        File f = new File("C:\\Users\\Angest\\Desktop\\code\\java");
        File[] fs1 = f.listFiles(); // 列出所有文件和子目录
        printFiles(fs1);
        File[] fs2 = f.listFiles(new FilenameFilter() {
			
			@Override
			public boolean accept(File dir, String name) {
				// TODO Auto-generated method stub
				return name.endsWith(".class");
			}
		});
        printFiles(fs2);
    }

    static void printFiles(File[] files) {
        System.out.println("==========");
        if (files != null) {
            for (File f : files) {
                System.out.println(f);
            }
        }
        System.out.println("==========");
    }
}