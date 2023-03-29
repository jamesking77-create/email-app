package data.repository;

import data.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UserRepositoryImpl implements UserRepository{

    private final List<User> users = new ArrayList<>();
    private int count;
    @Override
    public User saveUser(User user) {
        boolean userIsNotSaved = user.getId() == 0;
        if (userIsNotSaved){
            saveNewUser(user);
            users.add(user);
            count++;
        }
        return user;
    }

    private void saveNewUser(User user) {
        user.setId(generateId());
    }

    private int generateId() {
        return count + 1;
    }

    @Override
    public User findById(int id) {
        for (User user: users) {
            if (id == user.getId()) return user;
        }
        return null;
    }

    @Override
    public List<User> findAllUser() {
        return users;
    }

    @Override
    public User findUserByEmailAddress(String emailAddress) {
        for (User user: users) {
            if (Objects.equals(emailAddress, user.getEmailAddress())) return user;

        }
        return null;
    }

    @Override
    public void deleteUserById(int id) {
        users.removeIf(user -> id == user.getId());
        count--;
    }

    @Override
    public long countUser() {
        return count;
    }

    @Override
    public void deleteAllUser() {
        users.clear();
        count = 0;
    }
}
