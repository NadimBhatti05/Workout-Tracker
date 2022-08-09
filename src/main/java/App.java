import workout.tracker.model.Workout;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.UUID;

public class App {

    public static void main(String[] args) {
        LocalDate today = LocalDate.now();
        Workout workout = new Workout("Push", UUID.randomUUID().toString(), today.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG)));
        System.out.println(workout.getDate() + "\n" + workout.getUuid());
    }
}
