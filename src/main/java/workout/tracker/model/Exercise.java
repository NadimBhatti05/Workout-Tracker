package workout.tracker.model;

public class Exercise {

    private String exerciseUUID;
    private String name;
    //private Vector<Set> sets;
    private double[] weights;
    private int[] reps;



    public Exercise() {}

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

    public String getExerciseUUID() {
        return exerciseUUID;
    }

    public void setExerciseUUID(String exerciseUUID) {
        this.exerciseUUID = exerciseUUID;
    }

    public double[] getWeights() {
        return weights;
    }

    public void setWeights(double[] weights) {
        this.weights = weights;
    }

    public int[] getReps() {
        return reps;
    }

    public void setReps(int[] reps) {
        this.reps = reps;
    }
}
