package org.prashant.eaco;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: prashant
 * Date: 27/5/13
 * Time: 1:44 AM
 * To change this template use File | Settings | File Templates.
 */
import static java.lang.System.out;
public class Ant implements Runnable{
    private ArrayList<Employee> employeeList;
    private Set<Activity> processedActivities=new LinkedHashSet<Activity>();
    private Set<Activity> totalActivities=new LinkedHashSet<Activity>();
    private String name;
    private Employee start;
    private Map<Set<Employee>,Boolean> antPathMap;
    private List<Employee> currentEmployeePair=new ArrayList<Employee>();
    private Long timeDuration;

    public Ant(List<Employee> employeeList,Employee start,String name,Set<Activity> totalActivities){
        this.employeeList=new ArrayList<Employee>(employeeList);
        this.start=start;
        this.name=name;
        this.setAntPathMap(generatePathMapForAnt(new LinkedHashSet<Employee>(employeeList)));
        this.totalActivities=totalActivities;
    }

    public Long getTimeDuration() {
        return timeDuration;
    }

    public void setTimeDuration(Long timeDuration) {
        this.timeDuration = timeDuration;
    }

    public Map<Set<Employee>, Boolean> getAntPathMap() {
        return this.antPathMap;
    }

    public void setAntPathMap(Map<Set<Employee>, Boolean> antPathMap) {
        this.antPathMap = antPathMap;
    }

    public void run(){
        out.print("Ant: "+this.name);
        Collections.shuffle(employeeList);
        Long startTime=System.nanoTime();
        out.print(" || Employee: "+this.start+"|Activities: " +this.start.getActivitySet() + "|TTime: " + this.start.getTotalTime());
        employeeList.remove(this.start);
        currentEmployeePair.add(this.start);
        processedActivities.addAll(this.start.getActivitySet());

        /*
        * Diving into the recursion call :-)
        * */

        walkAnt(employeeList.size() > 0);
        Long endTime=System.nanoTime();
        this.setTimeDuration(endTime-startTime);
        out.println();
    }

    public void walkAnt(boolean flag){
        if (flag){
            Employee e=employeeList.remove(0);
            out.print(" || Employee: "+e+"|Activities: " + e.getActivitySet()+ "|TTime: " + e.getTotalTime());
            currentEmployeePair.add(e);
            updateCurrentEmployeePairPath(currentEmployeePair, antPathMap);
            processedActivities.addAll(e.getActivitySet());
            if (processedActivities.containsAll(totalActivities)) return;
            walkAnt(employeeList.size() > 0);
        }
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

    public void updateCurrentEmployeePairPath(List<Employee> employeePairList,Map<Set<Employee>,Boolean> antPathMap){
        if (employeePairList.size()>=2){
            Set<Employee> employeeSet=new LinkedHashSet<Employee>(employeePairList);
            if (antPathMap.containsKey(employeeSet)){
                antPathMap.put(employeeSet,Boolean.TRUE);
            }
            employeePairList.remove(0);
        }
    }

    public String toString(){
        return this.name+" "+timeDuration;
    }

    public static class OrderByTimeDuration implements Comparator<Ant>{

        public int compare(Ant a1,Ant a2){
            if (a1.getTimeDuration().equals(a2.getTimeDuration())){
                return 0;
            }
            else{
                return (a1.getTimeDuration()>a2.getTimeDuration())?1:-1;
            }
        }
    }
}
