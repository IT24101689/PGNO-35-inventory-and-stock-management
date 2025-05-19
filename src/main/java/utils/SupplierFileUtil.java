package utils;

import model.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SupplierFileUtil {

    private static final String USERS_DIR = System.getProperty("catalina.home") + "/bin/users";
    private static final String FILE_PATH = USERS_DIR + "/supplier.txt";

    static {
        // Create users directory if it doesn't exist
        File directory = new File(USERS_DIR);
        if (!directory.exists()) {
            directory.mkdirs();
        }
    }

    public static boolean saveUser(User user) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            writer.write("name:" + user.getName());
            writer.newLine();
            writer.write("password:" + user.getPassword());
            writer.newLine();
            writer.write("companyName:" + user.getCompanyName());
            writer.newLine();
            writer.write("category:" + user.getCategory());
            writer.newLine();
            writer.write("------------------------------------------------------");
            writer.newLine();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static List<User> readUsers() {
        List<User> users = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            User user = null;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("name:")) {
                    user = new User();
                    user.setName(line.substring(5));
                } else if (line.startsWith("password:") && user != null) {
                    user.setPassword(line.substring(9));
                } else if (line.startsWith("companyName:") && user != null) {
                    user.setCompanyName(line.substring(12));
                } else if (line.startsWith("category:") && user != null) {
                    user.setCategory(line.substring(9));
                    users.add(user);
                    user = null;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return users;
    }

    public static boolean updateUser(User updatedUser) {
        List<User> users = readUsers();
        boolean found = false;
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getName().equals(updatedUser.getName())) {
                users.set(i, updatedUser);
                found = true;
                break;
            }
        }
        if (!found) {
            return false;
        }
        return writeAllUsers(users);
    }

    public static boolean deleteUser(String name) {
        List<User> users = readUsers();
        users.removeIf(user -> user.getName().equals(name));
        return writeAllUsers(users);
    }

    private static boolean writeAllUsers(List<User> users) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (User user : users) {
                writer.write("name:" + user.getName());
                writer.newLine();
                writer.write("password:" + user.getPassword());
                writer.newLine();
                writer.write("companyName:" + user.getCompanyName());
                writer.newLine();
                writer.write("category:" + user.getCategory());
                writer.newLine();
                writer.write("------------------------------------------------------");
                writer.newLine();
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}