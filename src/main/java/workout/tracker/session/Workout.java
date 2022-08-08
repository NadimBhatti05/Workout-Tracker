package workout.tracker.session;
import workout.tracker.user.User;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Vector;


public class Workout {

    private String workoutUUID;
    private String name;
    private String date;
    private Vector<Exercise> exercises;

    public Workout(String name, String uuid){
        exercises = new Vector<>();
        this.workoutUUID = uuid;
        this.name = name;
        LocalDate today = LocalDate.now();
        this.date = today.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG));
    }



    public String getUuid() {
        return workoutUUID;
    }

    public void setUuid(String uuid) {
        this.workoutUUID = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }
}
