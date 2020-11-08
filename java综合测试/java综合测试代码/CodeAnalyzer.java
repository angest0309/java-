package 面试;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.Buffer;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class CodeAnalyzer{
	ArrayList<File> fileList;
	File root;

	public CodeAnalyzer(String pathName) {
		root = new File(pathName);
		fileList = new ArrayList<>();
	}
	
	public void searchFiles() {//递归查找文件
		File[] files =  root.listFiles();
		int length = files.length;
		for(int i=0; i<length;i++)
		{
			if(files[i].isDirectory())//判断是否为文件夹
			{
				root = files[i];
				searchFiles();
			}
			else
			{
				if(files[i].getName().endsWith(".java"))
				{
					fileList.add(files[i]);//把文件添加到文件数组中
				}
			}
		}
	}
	
	public void codeAnalyzer() throws IOException {
		//文件总行数
		double rowsCount = 0;
		//注释行数
		double commentsCount = 0;
		//空白行数
		double blankCount = 0;
		//代码行数
		double codesCount = 0;
		//字节数
		double ByteCount = 0;
		//书写目录
		FileWriter fw = new FileWriter("C:\\Users\\Angest\\Desktop\\code\\eclipse\\eclipse-workspace\\result\\result.txt");
		DecimalFormat df = new DecimalFormat("#.##");
		for(File file: fileList) {
			try {
				//统计总行数
				rowsCount = rowsCount + (int)countRows(file);
				//统计空白行数
				blankCount = blankCount + (int)countBlanks(file);
				//统计注释行数
				commentsCount = commentsCount + (int)countComments(file);
				//统计代码行数
				codesCount = rowsCount - blankCount - commentsCount;
				ByteCount = ByteCount + countBytes(file);
				ByteCount += file.length();
				System.out.println("该文件文件名为"+file.getName());
				fw.write("该文件文件名为"+file.getName()+"\n");
				System.out.println("当前文件总行数："+countRows(file));
				fw.write("当前文件总行数："+countRows(file)+"\n");
				System.out.println("当前文件空白行数："+countBlanks(file));
				fw.write("当前文件空白行数："+countBlanks(file)+"\n");
				System.out.println("当前文件的大小："+ file.length() + "(Byte)");
				fw.write("当前文件的大小："+ file.length() + "(Byte)"+"\n");
				System.out.println("-----------------------------");
				fw.write("-----------------------------"+"\n");
			//	System.out.println("");//字节数
			}catch(IOException e)
			{
				e.printStackTrace();
			}
		}
		System.out.println("源程序文件总行数："+(int)rowsCount);
		fw.write("源程序文件总行数："+(int)rowsCount+"\n");
		System.out.println("代码行数："+(int)codesCount+",占"+df.format(codesCount/rowsCount*100)+"%");
		fw.write("代码行数："+(int)codesCount+",占"+df.format(codesCount/rowsCount*100)+"%"+"\n");
		System.out.println("注释行数："+(int)commentsCount+"占"+df.format(commentsCount/rowsCount*100)+"%");
		fw.write("注释行数："+(int)commentsCount+"占"+df.format(commentsCount/rowsCount*100)+"%"+"\n");
		System.out.println("空白行数："+(int)blankCount+"占"+df.format(blankCount/rowsCount*100)+"%");
		fw.write("空白行数："+(int)blankCount+"占"+df.format(blankCount/rowsCount*100)+"%"+"\n");
		System.out.println("总字节数："+(int)ByteCount);
		fw.write("总字节数："+(int)ByteCount+"\n");
		fw.close();

	}

	//统计字节数的函数
	public double countBytes(File file) throws IOException {
		BufferedReader input = new BufferedReader(new FileReader(file));
		int bytes = 0;
		String line= null;
		while((line = input.readLine())!=null)
		{
			line = line.trim();
			
		}
		return 0;
	}

	//统计注释行数的函数
	public int countComments(File file)  throws IOException{
		BufferedReader input = new BufferedReader(new FileReader(file));
		int comments = 0;
		String line = null;
		while((line = input.readLine())!=null)
		{
			line = line.trim();
			if(line.startsWith("//"))//开头为//判定为注释
			{
				comments++;
			} else if (line.startsWith("/*"))//开头为/*判定为注释
			{
				comments++;
				while(!line.startsWith("/*"))
				{
					line = input.readLine().trim();
					comments++;
				}
			}else if(line.contains("/*"))//包含/*判定为注释
			{
				line = input.readLine().trim();
				if(line.endsWith("*/"))//结束为*/判定为注释
				{
					comments++;
				}
			}
		}
		return comments;
	}

	//计算空白行数的函数
	public int countBlanks(File file)  throws IOException{
		BufferedReader input = new BufferedReader(new FileReader(file));
		int blanks = 0;
		String line = null;
		while((line = input.readLine())!=null)
		{
			if(line.trim().equals(""))//若该行为空则判断为空白行
			{
				blanks++;
			}
		}
		
		return blanks;
	}
	
	//判断总行数的函数
	public int countRows(File file) throws IOException {
		BufferedReader input = new BufferedReader(new FileReader(file));
		int rows = 0;
		while(input.readLine()!=null)//每读到一行则总行数加一
		{
			rows++;
		}
		return rows;
	}
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		System.out.println("请输入需要查找的目录");
		String pathName = sc.nextLine();
		//测试目录
		//String pathName = "C:\\Users\\Angest\\Desktop\\code\\eclipse\\eclipse-workspace";
		CodeAnalyzer analyzer = new CodeAnalyzer(pathName);
		analyzer.searchFiles();
		analyzer.codeAnalyzer();
		FileCounter counter = new FileCounter(pathName);
        counter.searchFiles();
        counter.countFiles();
	}
}
