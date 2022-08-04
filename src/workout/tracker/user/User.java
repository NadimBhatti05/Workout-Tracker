package workout.tracker.user;
import workout.tracker.session.Workout;

import java.util.Vector;
import java.util.UUID;

public class User {

    private String uuid;
    private String username;
    private String password;
    private Vector<Workout> workouts;

    public User(String username, String password){
        this.uuid = UUID.randomUUID().toString();
        this.username = username;
        this.password = password;
    }

    public void addWorkout(Workout workout){
        this.workouts.add(workout);
    }

    public void deleteWorkout(Workout workout){
        this.workouts.remove(workout);
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
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
