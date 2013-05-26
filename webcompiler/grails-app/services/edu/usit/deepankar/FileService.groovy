package edu.usit.deepankar

class FileService {


    void writeToFile(String data,String fileName,String dirName){
        byte[] bytes=data.getBytes();
        try{
            FileOutputStream fos=new FileOutputStream(new File(dirName+"/"+fileName+".java"));
            fos.write(bytes);
        }catch(Throwable e){e.printStackTrace();}
    }

    String readFileToString(String fileName){
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

    String getSourceFileNameFromSource(String source){
        return source.substring(source.indexOf("class")+5,source.indexOf('{')).trim();
    }
    def createDir(){
        Calendar cal=Calendar.getInstance();
        String dirName="a_"+cal.get(cal.DAY_OF_MONTH)+"_"+cal.get(cal.MONTH)+"_"+cal.get(cal.YEAR)+"_"+UUID.randomUUID().toString().replace('-','_');
        File d=new File(dirName);
        //out.println(d.isDirectory());
        d.mkdir();
        return dirName
    }
}
