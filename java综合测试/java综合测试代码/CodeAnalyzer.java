package ����;

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
	
	public void searchFiles() {//�ݹ�����ļ�
		File[] files =  root.listFiles();
		int length = files.length;
		for(int i=0; i<length;i++)
		{
			if(files[i].isDirectory())//�ж��Ƿ�Ϊ�ļ���
			{
				root = files[i];
				searchFiles();
			}
			else
			{
				if(files[i].getName().endsWith(".java"))
				{
					fileList.add(files[i]);//���ļ���ӵ��ļ�������
				}
			}
		}
	}
	
	public void codeAnalyzer() throws IOException {
		//�ļ�������
		double rowsCount = 0;
		//ע������
		double commentsCount = 0;
		//�հ�����
		double blankCount = 0;
		//��������
		double codesCount = 0;
		//�ֽ���
		double ByteCount = 0;
		//��дĿ¼
		FileWriter fw = new FileWriter("C:\\Users\\Angest\\Desktop\\code\\eclipse\\eclipse-workspace\\result\\result.txt");
		DecimalFormat df = new DecimalFormat("#.##");
		for(File file: fileList) {
			try {
				//ͳ��������
				rowsCount = rowsCount + (int)countRows(file);
				//ͳ�ƿհ�����
				blankCount = blankCount + (int)countBlanks(file);
				//ͳ��ע������
				commentsCount = commentsCount + (int)countComments(file);
				//ͳ�ƴ�������
				codesCount = rowsCount - blankCount - commentsCount;
				ByteCount = ByteCount + countBytes(file);
				ByteCount += file.length();
				System.out.println("���ļ��ļ���Ϊ"+file.getName());
				fw.write("���ļ��ļ���Ϊ"+file.getName()+"\n");
				System.out.println("��ǰ�ļ���������"+countRows(file));
				fw.write("��ǰ�ļ���������"+countRows(file)+"\n");
				System.out.println("��ǰ�ļ��հ�������"+countBlanks(file));
				fw.write("��ǰ�ļ��հ�������"+countBlanks(file)+"\n");
				System.out.println("��ǰ�ļ��Ĵ�С��"+ file.length() + "(Byte)");
				fw.write("��ǰ�ļ��Ĵ�С��"+ file.length() + "(Byte)"+"\n");
				System.out.println("-----------------------------");
				fw.write("-----------------------------"+"\n");
			//	System.out.println("");//�ֽ���
			}catch(IOException e)
			{
				e.printStackTrace();
			}
		}
		System.out.println("Դ�����ļ���������"+(int)rowsCount);
		fw.write("Դ�����ļ���������"+(int)rowsCount+"\n");
		System.out.println("����������"+(int)codesCount+",ռ"+df.format(codesCount/rowsCount*100)+"%");
		fw.write("����������"+(int)codesCount+",ռ"+df.format(codesCount/rowsCount*100)+"%"+"\n");
		System.out.println("ע��������"+(int)commentsCount+"ռ"+df.format(commentsCount/rowsCount*100)+"%");
		fw.write("ע��������"+(int)commentsCount+"ռ"+df.format(commentsCount/rowsCount*100)+"%"+"\n");
		System.out.println("�հ�������"+(int)blankCount+"ռ"+df.format(blankCount/rowsCount*100)+"%");
		fw.write("�հ�������"+(int)blankCount+"ռ"+df.format(blankCount/rowsCount*100)+"%"+"\n");
		System.out.println("���ֽ�����"+(int)ByteCount);
		fw.write("���ֽ�����"+(int)ByteCount+"\n");
		fw.close();

	}

	//ͳ���ֽ����ĺ���
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

	//ͳ��ע�������ĺ���
	public int countComments(File file)  throws IOException{
		BufferedReader input = new BufferedReader(new FileReader(file));
		int comments = 0;
		String line = null;
		while((line = input.readLine())!=null)
		{
			line = line.trim();
			if(line.startsWith("//"))//��ͷΪ//�ж�Ϊע��
			{
				comments++;
			} else if (line.startsWith("/*"))//��ͷΪ/*�ж�Ϊע��
			{
				comments++;
				while(!line.startsWith("/*"))
				{
					line = input.readLine().trim();
					comments++;
				}
			}else if(line.contains("/*"))//����/*�ж�Ϊע��
			{
				line = input.readLine().trim();
				if(line.endsWith("*/"))//����Ϊ*/�ж�Ϊע��
				{
					comments++;
				}
			}
		}
		return comments;
	}

	//����հ������ĺ���
	public int countBlanks(File file)  throws IOException{
		BufferedReader input = new BufferedReader(new FileReader(file));
		int blanks = 0;
		String line = null;
		while((line = input.readLine())!=null)
		{
			if(line.trim().equals(""))//������Ϊ�����ж�Ϊ�հ���
			{
				blanks++;
			}
		}
		
		return blanks;
	}
	
	//�ж��������ĺ���
	public int countRows(File file) throws IOException {
		BufferedReader input = new BufferedReader(new FileReader(file));
		int rows = 0;
		while(input.readLine()!=null)//ÿ����һ������������һ
		{
			rows++;
		}
		return rows;
	}
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		System.out.println("��������Ҫ���ҵ�Ŀ¼");
		String pathName = sc.nextLine();
		//����Ŀ¼
		//String pathName = "C:\\Users\\Angest\\Desktop\\code\\eclipse\\eclipse-workspace";
		CodeAnalyzer analyzer = new CodeAnalyzer(pathName);
		analyzer.searchFiles();
		analyzer.codeAnalyzer();
		FileCounter counter = new FileCounter(pathName);
        counter.searchFiles();
        counter.countFiles();
	}
}
