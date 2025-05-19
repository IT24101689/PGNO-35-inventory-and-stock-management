package services;

import model.User;
import utils.BuyerFileUtil;

import java.util.ArrayList;
import java.util.List;

public class BuyerService {

    public boolean createUser(User user) {
        return BuyerFileUtil.saveUser(user);
    }

    public boolean loginUser(User user) {
        List<User> users = BuyerFileUtil.readUsers();
        return users.stream().anyMatch(u -> u.getName().equals(user.getName()) && u.getPassword().equals(user.getPassword()));
    }

    public boolean updateUser(User user) {
        return BuyerFileUtil.updateUser(user);
    }

    public boolean deleteUser(User user) {
        return BuyerFileUtil.deleteUser(user.getName());
    }

    public User getUserByName(String name) {
        List<User> users = BuyerFileUtil.readUsers();
        return users.stream().filter(u -> u.getName().equals(name)).findFirst().orElse(null);
    }

    public boolean validateName(String name) {
        List<User> users = BuyerFileUtil.readUsers();
        return users.stream().anyMatch(u -> u.getName().equals(name));
    }

    public ArrayList<User> getAllUsers() {
        List<User> users = BuyerFileUtil.readUsers();
        return new ArrayList<>(users);
    }
}