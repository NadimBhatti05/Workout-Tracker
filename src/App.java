import workout.tracker.gui.MainFrame;
import workout.tracker.session.Workout;

import java.util.UUID;

public class App {

    public static void main(String[] args) {
        Workout workout = new Workout("Push", UUID.randomUUID().toString());
        System.out.println(workout.getDate() + "\n" + workout.getUuid());
    }
}
