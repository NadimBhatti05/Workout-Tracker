import workout.tracker.session.Workout;

public class App {

    public static void main(String[] args) {
        Workout workout = new Workout("Push");
        System.out.println(workout.getDate());
    }

}
