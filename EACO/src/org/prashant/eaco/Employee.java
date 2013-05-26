package org.prashant.eaco;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: prashant
 * Date: 26/5/13
 * Time: 9:23 PM
 * To change this template use File | Settings | File Templates.
 */
public class Employee {
    Map<Activity,Integer> activityMap;
    String name;
    int totalTime;
    public Employee(String name){
        activityMap=new LinkedHashMap<Activity, Integer>();
        this.name=name;
    }

    public void addActivityAndTime(Activity activity,Integer time){
        this.activityMap.put(activity,time);
    }

    public Set<Activity> getActivities(){
        return this.activityMap.keySet();
    }

    public int getTotalTime(){
        int totalTime=0;
        for(Integer i:this.activityMap.values()){
            totalTime+=i;
        }
        return totalTime;
    }

    public String toString(){
        return this.name;
    }
}
