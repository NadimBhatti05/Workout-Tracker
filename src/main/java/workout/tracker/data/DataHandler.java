package workout.tracker.data;


import workout.tracker.session.Exercise;
import workout.tracker.session.Workout;
import workout.tracker.user.User;

import java.io.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.xml.crypto.Data;
import java.util.List;
import java.util.Vector;

public class DataHandler {
    private static List<User> userList;
    private static List<Workout> workoutList;
    private static List<Exercise> exerciseList;

    public static void initializeLists(){
        DataHandler.setUserList(null);
        DataHandler.setWorkoutList(null);
        DataHandler.setExerciseList(null);
    }

    private static void writeUserJSON(){
        JSONArray allUsers = new JSONArray();
        for (int i = 0; i < getUserList().size(); i++) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("userUUID", getUserList().get(i).getUuid());
            jsonObject.put("username", getUserList().get(i).getUsername());
            jsonObject.put("password", getUserList().get(i).getPassword());
            JSONArray workouts = new JSONArray();
            for (int j = 0; j < getUserList().get(i).getWorkouts().size(); j++) {
                workouts.add(getUserList().get(i).getWorkouts().get(j));
            }
            jsonObject.put("workoutsUUID", workouts);
            allUsers.add(jsonObject);
        }
        try(FileWriter file = new FileWriter("C:/Users/bhatt/OneDrive/projects/Java/Workout-Tracker/src/main/java/JSON/usersTest.json")) {
            file.write(allUsers.toJSONString());
            file.flush();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private static void readUserJSON(){
        JSONParser jsonParser = new JSONParser();
        try(FileReader reader = new FileReader("C:/Users/bhatt/OneDrive/projects/Java/Workout-Tracker/src/main/java/JSON/users.json")){
            Object obj = jsonParser.parse(reader);
            JSONArray allUsers = (JSONArray) obj;
            allUsers.forEach( user -> parseUserObject((JSONObject) user) );

        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }catch (ParseException e){
            e.printStackTrace();
        }
    }

    private static void parseUserObject(JSONObject user){
        String uuid = (String) user.get("userUUID");
        String username = (String) user.get("username");
        String password = (String) user.get("password");
        JSONArray arr = (JSONArray) user.get("workoutsUUID");
        Vector<String> workouts = new Vector<>();
        for (Object object : arr){
            workouts.add(object.toString());
        }
        User user1 = new User(username, password, uuid);
        getUserList().add(user1);
        //user1.setWorkouts(readWorkoutsByUUID(workouts));
    }

    public static Vector<Workout> readWorkoutsByUUID(Vector<String> UUIDs){
        Vector<Workout> workouts = new Vector<>();
        for (int i = 0; i < getWorkoutList().size(); i++) {
            for (int j = 0; j < UUIDs.size(); j++) {
                if(getWorkoutList().get(i).equals(UUIDs.get(j))){
                    workouts.add(getWorkoutList().get(i));
                }
            }
        }
        return workouts;
    }

    public static void main(String[] args) {
        writeUserJSON();
    }

    public static List<User> getUserList() {
        if(DataHandler.userList == null){
            DataHandler.setUserList(new Vector<>());
            readUserJSON();
        }
        return userList;
    }

    public static void setUserList(List<User> userList) {
        DataHandler.userList = userList;
    }

    public static List<Workout> getWorkoutList() {
        return workoutList;
    }

    public static void setWorkoutList(List<Workout> workoutList) {
        DataHandler.workoutList = workoutList;
    }

    public static List<Exercise> getExerciseList() {
        return exerciseList;
    }

    public static void setExerciseList(List<Exercise> exerciseList) {
        DataHandler.exerciseList = exerciseList;
    }
}