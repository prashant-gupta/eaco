/**
 * Created with IntelliJ IDEA.
 * User: localhome
 * Date: 26/5/13
 * Time: 9:34 PM
 * To change this template use File | Settings | File Templates.
 */
import org.prashant.eaco.Activity;
import org.prashant.eaco.Employee;

import java.util.ArrayList;
import java.util.List;

import static java.lang.System.out;
public class Main {

    static List<Activity> activityList=new ArrayList<Activity>();
    static List<Employee> employeeList=new ArrayList<Employee>();

    public static void main(String... args){
        generateActivities();

        out.println("hello world"+activityList);
    }

    public static void generateActivities(){
        for (int i=0;i<10;i++){
            activityList.add(new Activity("activity"+i));
        }
    }

    public static void generateEmployee(){
        for (int i=0;i<10;i++){
            employeeList.add(new Employee());
        }
    }
}
