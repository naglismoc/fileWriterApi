package org.example;

import com.google.gson.*;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static Gson gson;
    public static void main(String[] args) {
        gson = new Gson();
        User u = new User(1,"Vilhelmas","Rudys","https://reqres.in/img/faces/1-image.jpg","ne@tavo.reikalas");
        User u2 = new User(2,"Vilhelmas","Rudys","https://reqres.in/img/faces/1-image.jpg","vilhelmasvcs@vilniuscoding.lt");
//        addUser(u2);
//        List<User> users = getUsers();//        System.out.println(users);
      //  User user = getUser(1);
//        updateUser(u);
//        deleteUser(u2);
        API();
//        System.out.println(new Place());
        Scanner sc = new Scanner(System.in);
    //  uzd1 istraukti miesta is API, sukonstruoti jo java objektą ir atspausdinti consolėje su .toString();
        System.out.println("Pasirinkite miestą ir jo orus kuriuos norite matyti");
        String city = sc.nextLine();
        String url = "https://api.meteo.lt/v1/places/" + city ;

    //  uzd2 pagal įvestą miestą surasti:
        //  patį miestą, (iš jo atvaizduosime "administrativeDivision")
        //  jo dabartinius orus, sukonstruoti forecastTimestamp objektų masyvą ir savo nuožiūra
        // gražiai ir aiškiai atvaizduoti.

         url = "https://api.meteo.lt/v1/places/" + city + "/forcasts/long-term";


    }

    private static void API() {
        try {
            URL url = new URL("https://reqres.in/api/users");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream(), StandardCharsets.UTF_8));
            String response = "";
            String line;
            while (( line = reader.readLine()) != null){
                response += line;
            }
            JsonObject jsonResponse = gson.fromJson(response, JsonObject.class);
            JsonArray usersArray = jsonResponse.getAsJsonArray("data");
            User[] users = gson.fromJson(usersArray, User[].class);
            System.out.println(users);

            reader.close();
        } catch (Exception e) {
        }
    }

    public static void deleteUser(User user){
        List<User> users = getUsers();
        users.stream()
                .filter(u -> u.equals(user))
                .findFirst()
                .map(u -> users.remove(user))
                .orElseThrow(() -> new IllegalArgumentException("User with ID " + user.getId() + " not found."));
        updateJson(users);
    }
    public static void updateUser(User user){
        List<User> users = getUsers();
        users.stream()
                .filter(u -> u.equals(user))
                .findFirst()
                .map(u -> users.set(users.indexOf(u), user))
                .orElseThrow(() -> new IllegalArgumentException("User with ID " + user.getId() + " not found."));
        updateJson(users);
    }
    public static void addUser(User user){
        List<User> users = getUsers();
        users.add(user);
        updateJson(users);
    }
    public static void updateJson(List<User> users){
        try(FileWriter writer = new FileWriter("users.json")) {
            gson.toJson(users,writer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void addUserOld(User user){
        try(FileWriter writer = new FileWriter("user.json",true)) {
            gson.toJson(user,writer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static User getUser(long id){
        try(FileReader reader = new FileReader("users.json")){
            // Parse the JSON file
            JsonElement jsonElement = JsonParser.parseReader(reader);
            JsonArray jsonArray = jsonElement.getAsJsonArray();
            // Iterate through the JSON array
            for (JsonElement element : jsonArray) {
                JsonObject jsonObject = element.getAsJsonObject();
                // Extract fields from JSON object
                long userId = jsonObject.get("id").getAsLong();
                if(id == userId) {
                    String name = jsonObject.get("firstName").getAsString();
                    String surname = jsonObject.get("lastName").getAsString();
                    String avatar = jsonObject.get("avatar").getAsString();
                    String email = jsonObject.get("email").getAsString();
//                // Create User object and add to list
                    User user = new User();
                    user.setId(userId);
                    user.setFirstName(name);
                    user.setLastName(surname);
                    user.setEmail(email);
                    user.setAvatar(avatar);
                    return user;
                }
            }
        }catch (Exception e){
            System.out.println(e);
            System.out.println("kazkas nutiko");
        }
        return new User();
    }
    public static List<User> getUsers(){
        List<User> users = new ArrayList<>();
        try(FileReader reader = new FileReader("users.json")){
            // Parse the JSON file
            JsonElement jsonElement = JsonParser.parseReader(reader);
            JsonArray jsonArray = jsonElement.getAsJsonArray();
            // Iterate through the JSON array
            for (JsonElement element : jsonArray) {
                JsonObject jsonObject = element.getAsJsonObject();
                // Extract fields from JSON object
                long id = jsonObject.get("id").getAsLong();
                String name = jsonObject.get("firstName").getAsString();
                String surname = jsonObject.get("lastName").getAsString();
                String avatar = jsonObject.get("avatar").getAsString();
                String email = jsonObject.get("email").getAsString();
//                // Create User object and add to list
                User user = new User();
                user.setId(id);
                user.setFirstName(name);
                user.setLastName(surname);
                user.setEmail(email);
                user.setAvatar(avatar);
                users.add(user);
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return users;
    }
}