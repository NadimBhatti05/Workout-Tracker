package workout.tracker.user;
import workout.tracker.model.Workout;

import java.util.Vector;

public class User {

    private String userUUID;
    private String username;
    private String password;
    private Vector<Workout> workouts;

    public User(){
        workouts = new Vector<>();
    }

    public Vector<Workout> getWorkouts() {
        return workouts;
    }

    public void setWorkouts(Vector<Workout> workouts) {
        this.workouts = workouts;
    }

    public String getUuid() {
        return userUUID;
    }

    public void setUuid(String uuid) {
        this.userUUID = uuid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
