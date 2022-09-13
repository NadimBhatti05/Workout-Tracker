import workout.tracker.data.DataHandler;
import workout.tracker.gui.Login;
import workout.tracker.gui.MainFrame;
import workout.tracker.model.Workout;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.UUID;

public class App {

    public static void main(String[] args) {
        new Login();
    }
}
