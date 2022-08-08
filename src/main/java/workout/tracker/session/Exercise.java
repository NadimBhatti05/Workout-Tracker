package workout.tracker.session;

import java.util.Vector;

public class Exercise {

    private String exerciseUUID;
    private String name;
    //private Vector<Set> sets;
    private double[] weights;
    private int[] reps;



    public Exercise(String name, String uuid) {
        this.name = name;
        this.exerciseUUID = uuid;
    }

    public String getUuid() {
        return exerciseUUID;
    }

    public void setUuid(String uuid) {
        this.exerciseUUID = uuid;
    }

    /*public Vector<Set> getSets() {
        return sets;
    }

    public void setSets(Vector<Set> sets) {
        this.sets = sets;
    }
     */

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
