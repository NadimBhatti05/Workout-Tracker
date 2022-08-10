package workout.tracker.data;


import workout.tracker.model.Exercise;
import workout.tracker.model.Workout;
import workout.tracker.user.User;

import java.io.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.List;
import java.util.UUID;
import java.util.Vector;

public class DataHandler {
    private static List<User> userList;
    private static List<Workout> workoutList;
    private static List<Exercise> exerciseList;

    public DataHandler(){}

    //main method for testing purposes
    public static void main(String[] args) {}

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
                workouts.add(getUserList().get(i).getWorkouts().get(j).getUuid());
            }
            jsonObject.put("workoutsUUID", workouts);
            allUsers.add(jsonObject);
        }
        try(FileWriter file = new FileWriter("C:/Users/bhatt/OneDrive/projects/Java/Workout-Tracker/src/main/java/JSON/users.json")) {
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
        User user1 = new User();
        user1.setUsername(username);
        user1.setPassword(password);
        user1.setUuid(uuid);
        user1.setWorkouts(readWorkoutsByUUID(workouts));
        getUserList().add(user1);
    }

    public static Vector<Workout> readWorkoutsByUUID(Vector<String> UUIDs){
        Vector<Workout> workouts = new Vector<>();
        for (int i = 0; i < getWorkoutList().size(); i++) {
            for (int j = 0; j < UUIDs.size(); j++) {
                if(getWorkoutList().get(i).getUuid().equals(UUIDs.get(j))){
                    workouts.add(getWorkoutList().get(i));
                }
            }
        }
        return workouts;
    }

    public static User readUser(String username, String password){
        User user = null;
        for (User u : getUserList()) {
            if (u.getUsername().equals(username) &&
                    u.getPassword().equals(password)) {
                user = u;
            }
        }
        return user;
    }

    public static void insertUser(User user){
        getUserList().add(user);
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

    private static void writeWorkoutJSON(){
        JSONArray allWorkouts = new JSONArray();
        for (int i = 0; i < getWorkoutList().size(); i++) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("workoutUUID", getWorkoutList().get(i).getUuid());
            jsonObject.put("name", getWorkoutList().get(i).getName());
            jsonObject.put("date", getWorkoutList().get(i).getDate());
            JSONArray exercises = new JSONArray();
            for (int j = 0; j < getWorkoutList().get(i).getExercises().size(); j++) {
                exercises.add(getWorkoutList().get(i).getExercises().get(j).getUuid());
            }
            jsonObject.put("exercisesUUID", exercises);
            allWorkouts.add(jsonObject);
        }
        try(FileWriter file = new FileWriter("C:/Users/bhatt/OneDrive/projects/Java/Workout-Tracker/src/main/java/JSON/workouts.json")) {
            file.write(allWorkouts.toJSONString());
            file.flush();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private static void readWorkoutJSON(){
        JSONParser jsonParser = new JSONParser();
        try(FileReader reader = new FileReader("C:/Users/bhatt/OneDrive/projects/Java/Workout-Tracker/src/main/java/JSON/workouts.json")){
            Object obj = jsonParser.parse(reader);
            JSONArray allWorkouts = (JSONArray) obj;
            allWorkouts.forEach( workout -> parseWorkoutObject((JSONObject) workout) );

        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }catch (ParseException e){
            e.printStackTrace();
        }
    }

    private static void parseWorkoutObject(JSONObject workout){
        String uuid = (String) workout.get("workoutUUID");
        String name = (String) workout.get("name");
        String date = (String) workout.get("date");
        JSONArray arr = (JSONArray) workout.get("exercisesUUID");
        Vector<String> exercises = new Vector<>();
        for (Object object : arr){
            exercises.add(object.toString());
        }
        Workout workout1 = new Workout();
        workout1.setName(name);
        workout1.setDate(date);
        workout1.setUuid(uuid);
        workout1.setExercises(readExercisesByUUID(exercises));
        getWorkoutList().add(workout1);
    }

    public static Vector<Exercise> readExercisesByUUID(Vector<String> UUIDs){
        Vector<Exercise> exercises = new Vector<>();
        for (int i = 0; i < getExerciseList().size(); i++) {
            for (int j = 0; j < UUIDs.size(); j++) {
                if(getExerciseList().get(i).getUuid().equals(UUIDs.get(j))){
                    exercises.add(getExerciseList().get(i));
                }
            }
        }
        return exercises;
    }

    public static Workout readWorkout(String uuid){
        Workout workout = null;
        for (Workout w : getWorkoutList()) {
            if (w.getUuid().equals(uuid)) {
                workout = w;
            }
        }
        return workout;
    }

    public static void insertWorkout(Workout workout){
        getWorkoutList().add(workout);
        writeWorkoutJSON();
    }

    public static boolean deleteWorkout(String uuid) {
        Workout workout = readWorkout(uuid);
        if (workout != null) {
            getWorkoutList().remove(workout);
            writeWorkoutJSON();
            return true;
        } else {
            return false;
        }
    }

    public static List<Workout> getWorkoutList() {
        if(DataHandler.workoutList == null){
            DataHandler.setWorkoutList(new Vector<>());
            readWorkoutJSON();
        }
        return workoutList;
    }

    public static void setWorkoutList(List<Workout> workoutList) {
        DataHandler.workoutList = workoutList;
    }

    private static void writeExerciseJSON(){
        JSONArray allExercises = new JSONArray();
        for (int i = 0; i < getExerciseList().size(); i++) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("exerciseUUID", getExerciseList().get(i).getUuid());
            jsonObject.put("name", getExerciseList().get(i).getName());
            JSONArray weights = new JSONArray();
            JSONArray reps = new JSONArray();
            for (int j = 0; j < getExerciseList().get(i).getWeights().length; j++) {
                weights.add(getExerciseList().get(i).getWeights()[j]);
            }
            jsonObject.put("weights", weights);
            for (int j = 0; j < getExerciseList().get(i).getReps().length; j++) {
                reps.add(getExerciseList().get(i).getReps()[j]);
            }
            jsonObject.put("reps", reps);
            allExercises.add(jsonObject);
        }
        try(FileWriter file = new FileWriter("C:/Users/bhatt/OneDrive/projects/Java/Workout-Tracker/src/main/java/JSON/exercises.json")) {
            file.write(allExercises.toJSONString());
            file.flush();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private static void readExerciseJSON(){
        JSONParser jsonParser = new JSONParser();
        try(FileReader reader = new FileReader("C:/Users/bhatt/OneDrive/projects/Java/Workout-Tracker/src/main/java/JSON/exercises.json")){
            Object obj = jsonParser.parse(reader);
            JSONArray allWorkouts = (JSONArray) obj;
            allWorkouts.forEach( exercise -> parseExerciseObject((JSONObject) exercise) );

        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }catch (ParseException e){
            e.printStackTrace();
        }
    }

    private static void parseExerciseObject(JSONObject exercise){
        String uuid = (String) exercise.get("exerciseUUID");
        String name = (String) exercise.get("name");
        JSONArray weightsArr = (JSONArray) exercise.get("weights");
        JSONArray repsArr = (JSONArray) exercise.get("reps");
        double[] weights = new double[weightsArr.size()];
        int[] reps = new int[repsArr.size()];
        for (int i = 0; i < weights.length; i++) {
            weights[i] = Double.parseDouble(weightsArr.get(i).toString());
        }
        for (int i = 0; i < reps.length; i++) {
            reps[i] = Integer.parseInt(repsArr.get(i).toString());
        }
        Exercise exercise1 = new Exercise();
        exercise1.setName(name);
        exercise1.setUuid(uuid);
        exercise1.setWeights(weights);
        exercise1.setReps(reps);
        getExerciseList().add(exercise1);
    }

    public static Exercise readExercise(String uuid){
        Exercise exercise = null;
        for (Exercise e : getExerciseList()) {
            if (e.getUuid().equals(uuid)) {
                exercise = e;
            }
        }
        return exercise;
    }

    public static void insertExercise(Exercise exercise){
        getExerciseList().add(exercise);
        writeExerciseJSON();
    }

    public static boolean deleteExercise(String uuid) {
        Exercise exercise = readExercise(uuid);
        if (exercise != null) {
            getExerciseList().remove(exercise);
            writeExerciseJSON();
            return true;
        } else {
            return false;
        }
    }

    public static List<Exercise> getExerciseList() {
        if(DataHandler.exerciseList == null){
            DataHandler.setExerciseList(new Vector<>());
            readExerciseJSON();
        }
        return exerciseList;
    }

    public static void setExerciseList(List<Exercise> exerciseList) {
        DataHandler.exerciseList = exerciseList;
    }
}