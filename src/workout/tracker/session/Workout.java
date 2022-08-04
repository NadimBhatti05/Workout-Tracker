package workout.tracker.session;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;


public class Workout {
    private String date;
    private String name;

    public Workout(String name){
        this.name = name;
        LocalDate today = LocalDate.now();
        this.date = today.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG));
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
