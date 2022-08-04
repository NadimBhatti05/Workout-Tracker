package workout.tracker.session;

import java.util.Vector;

public class Exercise {
    private String name;
    private Vector<Set> sets;

    public Exercise(String name) {
        this.name = name;
    }

    public void addSet(Set set){
        this.sets.add(set);
    }

    public void deleteSet(Set set){
        this.sets.remove(set);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
