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
        populateEmployeeWithActivities();
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
        for (int i=0;i<10;i++){
            activitySet.add(new Activity("activity"+i));
        }
    }

    public static void generateEmployee(){
        for (int i=0;i<10;i++){
            employeeList.add(new Employee("employee"+i));
        }
    }

    public static void populateEmployeeWithActivities(){
        List<Activity> activityList=new ArrayList<Activity>(activitySet);
        for (Employee e:employeeList){
            int limit=1+(int)(Math.random() * MAX_ACTIVITIES);
            for (int i=0;i< limit;i++){
                int idx=(int)((Math.random()* activityList.size()));
                e.addActivityAndTime(activityList.get(idx), 1 + (int) (Math.random() * 10));
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
