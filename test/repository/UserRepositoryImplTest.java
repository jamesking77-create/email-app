package repository;

import myEmailApp.dtos.data.model.User;
import myEmailApp.dtos.data.repository.UserRepository;
import myEmailApp.dtos.data.repository.UserRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserRepositoryImplTest {
    private User user;
    private User user2;
    private UserRepository userRepository;
    @BeforeEach public void setUp(){
        userRepository = new UserRepositoryImpl();
        user = new User();
        user2 = new User();
        user.setFirstName("james");
        user.setLastName("king");
        user.setPhoneNumber("09088776655");
    }

    @Test public void saveOneUser_countIsOneTest(){
        userRepository.saveUser(user);
        assertEquals(1, userRepository.countUser());
    }
    @Test public  void saveOneUser_userIdIsOneTest(){
        User savedUser = userRepository.saveUser(user);
        assertEquals(1, savedUser.getId());
    }

    @Test public void saveOneUser_FindUserByIdTest(){
        User savedUser = userRepository.saveUser(user);
        assertEquals(1, savedUser.getId());
        User foundUser = userRepository.findById(1);
        assertEquals(savedUser, foundUser);
    }

    @Test public void saveTwoUser_FindAllUserTest(){
        User savedUser = userRepository.saveUser(user);
        assertEquals(1, savedUser.getId());
        userRepository.saveUser(user2);
        assertEquals(2, user2.getId() );
        userRepository.findAllUser();
        assertEquals(2, userRepository.findAllUser().size() );
    }
    @Test public void saveTwoUser_deleteAllUser(){
        User savedUser = userRepository.saveUser(user);
        assertEquals(1, savedUser.getId());
        userRepository.saveUser(user2);
        assertEquals(2, user2.getId() );
        userRepository.deleteAllUser();
        assertEquals(0, userRepository.countUser());
    }
    @Test public void saveTwoUser_FindUserByIdTest(){
        User savedUser = userRepository.saveUser(user);
        assertEquals(1, savedUser.getId());
        userRepository.saveUser(user2);
        assertEquals(2, user2.getId() );
        User foundUser = userRepository.findById(1);
        assertEquals(savedUser, foundUser);
    }

    @Test public void saveTwoUser_deleteUserByIdTest(){
        User savedUser = userRepository.saveUser(user);
        assertEquals(1, savedUser.getId());
        userRepository.saveUser(user2);
        assertEquals(2, user2.getId() );
        userRepository.deleteUserById(1);
        assertEquals(1,userRepository.countUser());
    }
}