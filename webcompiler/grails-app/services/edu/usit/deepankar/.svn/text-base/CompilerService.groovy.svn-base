package edu.usit.deepankar

class CompilerService {



    boolean compileJavaSource(String filePath){
        def output=""
        boolean status=false;
        try{
            Process p=Runtime.getRuntime().exec("javac "+filePath+".java");
            p.waitFor();
            InputStream inn=p.getInputStream();
            InputStream er=p.getErrorStream();
            Calendar cal=Calendar.getInstance();
            String fileName=cal.get(cal.DAY_OF_MONTH)+"-"+cal.get(cal.MONTH)+"-"+cal.get(cal.YEAR)+"-"+UUID.randomUUID().toString();
            int c;
            if(p.exitValue() == 0){
                //out.println("inside success block "+fileName);
                while((c=inn.read()) != -1)
                print((char)c);
                status = true;
            }
            else{
                println("inside error block "+fileName);
                while((c=er.read()) != -1)  {
                    //print((char)c);
                    output=output+(char)c
                }
                println("==== output inside compilation error block = "+output)
                status = false;
            }
        }
        catch(Throwable e){
            e.printStackTrace();
        }
        return status;
    }

    def runJavaSource(String fileName,String dirName){

        def status="";
        //out.println("hello world");
        try{
            println "========= inside runJavaSource ============"
            Process p=Runtime.getRuntime().exec("java "+dirName+"."+fileName);
            p.waitFor();
            int exitValue;
            println "===== exit value === "+(exitValue=p.exitValue());
            InputStream inn=(p.getInputStream());
            InputStream er=(p.getErrorStream());
            Calendar cal=Calendar.getInstance();
            //String fileName=cal.get(cal.DAY_OF_MONTH)+"-"+cal.get(cal.MONTH)+"-"+cal.get(cal.YEAR)+"-"+UUID.randomUUID().toString();
            int c;

            if(exitValue == 0){
                println("======inside success block runSource "+dirName+"/"+fileName);
                while((c=inn.read()) != -1)
                //out.print((char)c);
                status=status+(char)c;
            }
            else{
                println(" ===== inside error block of runSource "+dirName+"/"+fileName);
                while((c=er.read()) != -1)
                   // out.print((char)c);  d
                status=status+(char)c;
            }
        }
        catch(Throwable e){
            e.printStackTrace();
        }
        return status;
    }
}
