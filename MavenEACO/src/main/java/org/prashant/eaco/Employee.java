package org.prashant.eaco;

import org.apache.commons.lang.builder.HashCodeBuilder;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: prashant
 * Date: 26/5/13
 * Time: 9:23 PM
 * To change this template use File | Settings | File Templates.
 */
public class Employee {
    Set<Activity> activitySet;
    String name;
    Double totalTime;

    public Employee(){}

    public Employee(String name){
        activitySet=new LinkedHashSet<Activity>();
        this.name=name;
    }

    public Employee(String name, Double totalTime){
        activitySet=new LinkedHashSet<Activity>();
        this.name=name;
        this.totalTime=totalTime;
    }

    public void addActivity(Activity activity){
        this.activitySet.add(activity);
    }

    public Set<Activity> getActivitySet() {
        return activitySet;
    }

    public void setActivitySet(Set<Activity> activitySet) {
        this.activitySet = activitySet;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(Double totalTime) {
        this.totalTime = totalTime;
    }

    public String toString(){
        return this.name;
    }

    public int hashCode(){
        HashCodeBuilder hashCodeBuilder=new HashCodeBuilder();
        hashCodeBuilder.append(this.name);
        hashCodeBuilder.append(this.totalTime);
        return hashCodeBuilder.toHashCode();
    }

    public boolean equals(Object o){
        return (o instanceof Employee && o.hashCode() == this.hashCode());
    }
}
