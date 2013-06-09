/**
 * Created with IntelliJ IDEA.
 * User: prashant
 * Date: 26/5/13
 * Time: 9:34 PM
 * To change this template use File | Settings | File Templates.
 */
import org.prashant.eaco.Activity;
import org.prashant.eaco.Ant;
import org.prashant.eaco.Employee;

import javax.swing.*;
import java.util.*;

import static java.lang.System.out;
public class Main {
    static final int MAX_ACTIVITIES=10;
    static Set<Activity> activitySet=new LinkedHashSet<Activity>();
    static List<Employee> employeeList=new ArrayList<Employee>();
    static List<Thread> antList=new ArrayList<Thread>();

    public static void main(String... args){
        generateActivities();
        generateEmployee();
        populateAnts();
        out.println("##############");
        out.println("Ant list: "+antList);
        out.println("Activity Universe: "+activitySet);
        out.println("##############");

        for (Thread a:antList){
            a.start();
            try{
                a.join();
            }
            catch(Throwable t){
                out.println("Exception ================ "+t.getMessage());
            }
        }
    }

    public static void generateActivities(){
        String count=JOptionPane.showInputDialog(null,"Enter total number of Activities","EACO",JOptionPane.QUESTION_MESSAGE);
        Integer totalActivityCount=null;
        try{totalActivityCount=Integer.parseInt(count);}
        catch(Exception e){out.println(e.getMessage());System.exit(1);}
        for (int i=0;i<totalActivityCount;i++){
            activitySet.add(new Activity("activity"+i));
        }
    }

    public static void generateEmployee(){
        String count=JOptionPane.showInputDialog(null,"Enter total number of Employees","EACO",JOptionPane.QUESTION_MESSAGE);
        Integer totalEmployeeCount=null;
        try{totalEmployeeCount=Integer.parseInt(count);}
        catch(Exception e){out.println(e.getMessage());System.exit(1);}

        for (int i=0;i<totalEmployeeCount;i++){
            Employee employee=new Employee("employee"+i);
            employeeList.add(employee);

            JList list = new JList(activitySet.toArray());
            JOptionPane.showMessageDialog(null, list, "Select activities for employee", JOptionPane.PLAIN_MESSAGE);
            for(Object a:list.getSelectedValues()){
                employee.addActivity((Activity)a);
            }

            String time=JOptionPane.showInputDialog(null,"Enter total number of Employees","EACO",JOptionPane.QUESTION_MESSAGE);
            Double totalTime=null;
            try{totalTime=Double.parseDouble(time);}
            catch(Exception e){out.println(e.getMessage());System.exit(1);}
            employee.setTotalTime(totalTime);
        }
    }

    public static void populateEmployeeWithActivities(){
        List<Activity> activityList=new ArrayList<Activity>(activitySet);
        for (Employee e:employeeList){
            int limit=1+(int)(Math.random() * MAX_ACTIVITIES);
            for (int i=0;i< limit;i++){
                int idx=(int)((Math.random()* activityList.size()));
                e.addActivity(activityList.get(idx));
            }
        }
    }

    public static void populateAnts(){
        int i=0;
        for (Employee e:employeeList){
            antList.add(new Thread(new Ant(employeeList,employeeList.get(i),"Ant"+i),"Ant"+i));
            i++;
        }
    }
}
