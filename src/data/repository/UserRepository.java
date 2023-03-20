package data.repository;

import data.model.User;

import java.util.List;

public interface UserRepository {
    User saveUser(User user);
    User findById(int id);
    List<User> findAllUser();
    User findUserByEmailAddress(String emailAddress);
    void deleteUserById(int id);
    long countUser();
    void deleteAllUser();

}
