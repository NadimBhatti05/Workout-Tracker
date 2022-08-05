package workout.tracker.session;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Vector;


public class Workout {

    private String uuid;
    private String date;
    private String name;

    public Workout(String name, String uuid){
        this.uuid = uuid;
        this.name = name;
        LocalDate today = LocalDate.now();
        this.date = today.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG));
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
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
