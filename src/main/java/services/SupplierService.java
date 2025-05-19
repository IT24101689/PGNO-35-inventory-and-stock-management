package services;

import model.User;
import utils.BuyerFileUtil;
import utils.SupplierFileUtil;

import java.util.ArrayList;
import java.util.List;

public class SupplierService {

    public boolean createUser(User user) {
        return SupplierFileUtil.saveUser(user);
    }

    public boolean loginUser(User user) {
        List<User> users = SupplierFileUtil.readUsers();
        return users.stream().anyMatch(u -> u.getName().equals(user.getName()) && u.getPassword().equals(user.getPassword()));
    }

    public boolean updateUser(User user) {
        return SupplierFileUtil.updateUser(user);
    }

    public boolean deleteUser(User user) {
        return SupplierFileUtil.deleteUser(user.getName());
    }

    public User getUserByName(String name) {
        List<User> users = SupplierFileUtil.readUsers();
        return users.stream().filter(u -> u.getName().equals(name)).findFirst().orElse(null);
    }

    public boolean validateName(String name) {
        List<User> users = SupplierFileUtil.readUsers();
        return users.stream().anyMatch(u -> u.getName().equals(name));
    }

    public ArrayList<User> getAllUsers() {
        List<User> users = SupplierFileUtil.readUsers();
        return new ArrayList<>(users);
    }
}