/**
 * Created with IntelliJ IDEA.
 * User: deepanker
 * Date: 9/4/13
 * Time: 12:14 AM
 * To change this template use File | Settings | File Templates.
 */

import edu.usit.deepanker.interfaces.ReplaceablePattern;
import edu.usit.deepanker.replaceablepatterns.*;

import javax.swing.*;
import static java.lang.System.out;
import java.io.*;
import java.util.*;
import java.util.regex.*;

public class Main {

    private static String immutableFileData=null;
    private static Map<String,ReplaceablePattern> patternMap=null;
    private static File mutationDirectory=null;
    private static Date startTime;
    private static Date endTime;


    public static void main(String... args){
        patternMap=populatePatterns();
        immutableFileData=getFileData();
        Long totalMutantCount=0L;
//        JOptionPane.showMessageDialog(null,"The source you had entered: "+immutableFileData,"Mutation source",JOptionPane.INFORMATION_MESSAGE);
        if (!immutableFileData.isEmpty()){
            List<ReplaceablePattern> matcherList=new ArrayList<ReplaceablePattern>(patternMap.values());
            startTime=new Date();
            for(ReplaceablePattern rp:matcherList){
                Long count=0L;
                out.println("Using: "+rp.getPatternName());
                Matcher matcher=rp.getPattern().matcher(immutableFileData);

                while (matcher.find()){
                    for(String replacementString:rp.getReplacements()){
                        StringBuffer stringBuffer=new StringBuffer(immutableFileData);
                        stringBuffer.replace(matcher.start(),matcher.end(),"/* mutation */ "+replacementString);
                        FileOutputStream mutation;
                        try{
                            mutation=new FileOutputStream(new File(mutationDirectory+"/mutation_"+rp.getPatternName()+"_"+count));
                            mutation.write(stringBuffer.toString().getBytes());
                            mutation.flush();
                            mutation.close();
                        }catch (Exception e){
                            out.println("Error: "+e.getMessage());
                        }

                        count++;
                        totalMutantCount++;
                    }
                }
            }
            endTime=new Date();
            JOptionPane.showMessageDialog(null,"Total mutants generated: "+totalMutantCount+" in "+(endTime.getTime()-startTime.getTime())+"ms","MutGen",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private static Map<String,ReplaceablePattern> populatePatterns(){
        Map<String,ReplaceablePattern> map=new HashMap<String, ReplaceablePattern>();
        map.put("addition",new AddArithmeticOperator());
        map.put("subtraction",new SubtractArithmeticOperator());
        map.put("multiplication",new MultiplicationArithmeticOperator());
        map.put("division",new DivisionArithmeticOperator());
        map.put("this",new ThisKeyword());
        return map;
    }

    public static String getFileData(){
        StringBuilder fileData=new StringBuilder();

        File file=null;
        File createMutationDirectory=null;
        file=getSourceFile();

        if(file != null){
            BufferedInputStream bufferedInputStream;
            try{
                out.println("File Name: "+file.getAbsolutePath());
                /* create or update directory for generating mutations */
                mutationDirectory=getMutationDirectory(file);
                bufferedInputStream=new BufferedInputStream(new FileInputStream(file));
                int ch;
                while ((ch=bufferedInputStream.read()) != -1){
                    fileData.append((char)ch);
                }
            }catch (Throwable t){
                t.printStackTrace();
            }
        }
        return fileData.toString();
    }

    public static File getSourceFile(){
        JFileChooser jFileChooser=new JFileChooser();
        jFileChooser.showDialog(null,"Choose a source file to generate mutations");
        return jFileChooser.getSelectedFile();
    }

    public static File getMutationDirectory(File file){
        File createMutationDirectory=new File(file.getAbsolutePath()+"MutationTests");
        out.println("Exist? "+createMutationDirectory.exists());
        if(!createMutationDirectory.exists()){
            out.println("creating mutation directory: "+createMutationDirectory.mkdir());
        }
        return createMutationDirectory;
    }
}
