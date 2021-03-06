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
    static List<Thread> antThreadList =new ArrayList<Thread>();
    static List<Ant> antList=new ArrayList<Ant>();
    static Map<Set<Employee>,Double> pathMap;


    public static void main(String... args){
        generateActivities();
        generateEmployee();
        pathMap=generatePathMap(new LinkedHashSet<Employee>(employeeList));


        out.println("##############");
        out.println("Activity Universe: "+activitySet);
        out.println("Path Universe: "+ pathMap);
        out.println("##############");
        Long runTime=Long.parseLong(JOptionPane.showInputDialog("Enter maximum execution time"));
        Long startTime=System.currentTimeMillis();

        while (runTime>(System.currentTimeMillis()-startTime)){
            populateAnts();
            for (Thread a: antThreadList){
                a.start();
                try{
                    a.join();
                }
                catch(Throwable t){
                    out.println("Exception ================ "+t.getMessage());
                }
            }
            Collections.sort(antList,new Ant.OrderByTimeDuration());
            out.println("==== "+antList);
            out.println("==== "+antList.get(0).getAntPathMap());
            updatePheromones(pathMap,antList.get(0).getAntPathMap());
        }
        out.println();
        String showMapString="";
        for(Map.Entry e:pathMap.entrySet()){
            showMapString+=e+" \n";
        }
        out.println("Final path map : \n"+showMapString);
        JOptionPane.showMessageDialog(null,"Final path map: \n"+showMapString,"EACO",JOptionPane.INFORMATION_MESSAGE);
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

            String time=JOptionPane.showInputDialog(null,"Enter total time taken","EACO",JOptionPane.QUESTION_MESSAGE);
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
        antThreadList.clear();
        antList.clear();
        int i=0;
        for (Employee e:employeeList){
            Ant ant=new Ant(employeeList, employeeList.get(i), "Ant" + i, activitySet);
            antThreadList.add(new Thread(ant, "Ant" + i));
            antList.add(ant);
            i++;
        }
    }

    public static Map<Set<Employee>,Double> generatePathMap(Set <Employee> employeeSet){
        Map<Set<Employee>,Double> map=new LinkedHashMap<Set<Employee>, Double>();
        for ( Employee e1: employeeSet){
            for (Employee e2: employeeSet){
                if(!e2.equals(e1)){
                    Set<Employee> keySet=new LinkedHashSet<Employee>();
                    keySet.add(e1);
                    keySet.add(e2);
                    map.put(keySet,0.0);
                }
            }
        }
        return map;
    }

    public static Map<Set<Employee>,Boolean> generatePathMapForAnt(Set <Employee> employeeSet){
        Map<Set<Employee>,Boolean> map=new LinkedHashMap<Set<Employee>, Boolean>();
        for ( Employee e1: employeeSet){
            for (Employee e2: employeeSet){
                if(!e2.equals(e1)){
                    Set<Employee> keySet=new LinkedHashSet<Employee>();
                    keySet.add(e1);
                    keySet.add(e2);
                    map.put(keySet,Boolean.FALSE);
                }
            }
        }
        return map;
    }

    public static void updatePheromones(Map<Set<Employee>,Double> globalPathMap,Map<Set<Employee>,Boolean> antPathMap){
        for (Map.Entry<Set<Employee>,Boolean> antPath:antPathMap.entrySet()){
            if (antPath.getValue()){
                if (globalPathMap.containsKey(antPath.getKey())){
                    globalPathMap.put(antPath.getKey(),globalPathMap.get(antPath.getKey())+(1-(0.1*globalPathMap.get(antPath.getKey()))));
                }
            }else {
                if (globalPathMap.containsKey(antPath.getKey())){
                    globalPathMap.put(antPath.getKey(),globalPathMap.get(antPath.getKey())-(0.1*globalPathMap.get(antPath.getKey())));
                }
            }
        }
    }
}
