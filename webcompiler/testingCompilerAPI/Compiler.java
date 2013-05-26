import static java.lang.System.out;
import java.io.*;
import java.util.*;

public class Compiler{

	public static void main(String... args){
		Calendar cal=Calendar.getInstance();
		String dirName=cal.get(cal.DAY_OF_MONTH)+"-"+cal.get(cal.MONTH)+"-"+cal.get(cal.YEAR)+"-"+UUID.randomUUID().toString();
			
		File d=new File(dirName);
		out.println(d.isDirectory());
		d.mkdir();
		out.println(d.isDirectory());
		writeToFile("class Source{public static void main(String... args){System.out.println(\"hello world\");}}",dirName+"/Sample.java");
		
		/*
		String s=readFileToString("Sample.java");
		String className=getSourceFileNameFromSource(s);
		out.println("=====class name===== "+className);
		out.println(s);
		*/
		/*
		if(compileSource()){
			runSource();
		}
		*/
	}
	
	static boolean compileSource(){
		//out.println("hello world");
		boolean status=false;
		try{
			Process p=Runtime.getRuntime().exec("javac Source.java");
			p.waitFor();
			InputStream in=(p.getInputStream());
			InputStream er=(p.getErrorStream());
			Calendar cal=Calendar.getInstance();
			String fileName=cal.get(cal.DAY_OF_MONTH)+"-"+cal.get(cal.MONTH)+"-"+cal.get(cal.YEAR)+"-"+UUID.randomUUID().toString();
			int c;
			if(p.exitValue() == 0){
					//out.println("inside success block "+fileName);
					while((c=in.read()) != -1)
					out.print((char)c);
					status = true;
				}
				else{
				out.println("inside error block "+fileName);
					while((c=er.read()) != -1)
					out.print((char)c);
					status = false;
				}
			}
			catch(Throwable e){
				e.printStackTrace();
				}
				return status;
	}
	
	static boolean runSource(){
		boolean status=false;
		//out.println("hello world");
		try{
			Process p=Runtime.getRuntime().exec("java Source");
			p.waitFor();
			InputStream in=(p.getInputStream());
			InputStream er=(p.getErrorStream());
			Calendar cal=Calendar.getInstance();
			String fileName=cal.get(cal.DAY_OF_MONTH)+"-"+cal.get(cal.MONTH)+"-"+cal.get(cal.YEAR)+"-"+UUID.randomUUID().toString();
			int c;
			if(p.exitValue() == 0){
					//out.println("inside success block "+fileName);
					while((c=in.read()) != -1)
					out.print((char)c);
					status=true;
				}
				else{
				out.println("inside error block "+fileName);
					while((c=er.read()) != -1)
					out.print((char)c);
					status=false;
				}
			}
			catch(Throwable e){
				e.printStackTrace();
				}
				return status;
	}
	static void writeToFile(String data,String fileName){
		byte[] bytes=data.getBytes();
		try{
			FileOutputStream fos=new FileOutputStream(new File(fileName));
			fos.write(bytes);
		}catch(Throwable e){e.printStackTrace();}
	}
	static String readFileToString(String fileName){
		String data="";
		try{
			FileInputStream fis=new FileInputStream(new File(fileName));
			int c;
			while((c=fis.read()) != -1){
				data=data+(char)c;
			}
		}catch(Throwable e){e.printStackTrace();}
			return data;
	}
	static String getSourceFileNameFromSource(String source){
		return source.substring(source.indexOf("class")+5,source.indexOf('{')).trim();
	}
}