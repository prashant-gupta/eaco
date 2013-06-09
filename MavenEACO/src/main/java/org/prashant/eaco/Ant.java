package org.prashant.eaco;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: prashant
 * Date: 27/5/13
 * Time: 1:44 AM
 * To change this template use File | Settings | File Templates.
 */
import static java.lang.System.out;
public class Ant implements Runnable{
    ArrayList<Employee> employeeList;
    String name;
    Employee start;

    public Ant(List<Employee> employeeList,Employee start,String name){
        this.employeeList=new ArrayList<Employee>(employeeList);
        this.start=start;
        this.name=name;
    }

    public void run(){
        out.print("Ant: "+this.name);
        Collections.shuffle(employeeList);
        out.print("||Employee: "+this.start+"||Activities: " +this.start.getActivitySet().size() + "||TTime: " + this.start.getTotalTime());
        employeeList.remove(this.start);

        /*
        * Diving into the recursion call :-)
        * */

         walkAnt(employeeList.size()>0);

        out.println();
    }

    public void walkAnt(boolean flag){
        if (flag){
            Employee e=employeeList.remove(0);
            out.print("||Employee: "+e+"||Activities: " + e.getActivitySet().size() + "||TTime: " + e.getTotalTime());
            walkAnt(employeeList.size()>0);
        }
    }
}
