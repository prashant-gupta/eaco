package org.prashant.eaco;

public class Activity {
    String name;

    public int hashCode(){
        return this.name.hashCode();
    }

    public boolean equals(Object o){
        return (o instanceof Activity && ((Activity) o).name.equals(this.name));
    }
}
