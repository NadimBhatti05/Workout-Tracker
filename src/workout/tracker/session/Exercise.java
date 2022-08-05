package workout.tracker.session;

import java.util.Vector;

public class Exercise {

    private String uuid;
    private String name;
    //private Vector<Set> sets;
    private Vector<Double> weights;
    private Vector<Integer> reps;



    public Exercise(String name, String uuid) {
        this.name = name;
        this.uuid = uuid;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
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
