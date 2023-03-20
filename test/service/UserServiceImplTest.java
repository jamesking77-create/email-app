package service;

import data.model.User;
import data.repository.UserRepository;
import dtos.request.LoginRequest;
import dtos.request.RegisterRequest;
import dtos.response.FindLoginResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceImplTest {
    private UserService userService;
    RegisterRequest registerRequest;
    LoginRequest loginRequest;
    @BeforeEach public void setUp(){
        userService = new UserServiceImpl();
        registerRequest = new RegisterRequest();
        loginRequest = new LoginRequest();
        registerRequest.setFirstName("moyinoluwa");
        registerRequest.setLastName("micheal");
        registerRequest.setUserName("moyex");
        registerRequest.setPassword("6789");
        registerRequest.setEmailAddress("moyex33@gmail.com");
        loginRequest.setEmailAddress(registerRequest.getEmailAddress());
        loginRequest.setPassword(registerRequest.getPassword());

    }
    @Test  public void registerOneUser_userIdIsOne(){
       User savedUser =  userService.register(registerRequest);
        assertEquals(1, savedUser.getId());
    }

    @Test public void loginUserWithoutRegistrationThrowsException(){
        User savedUser =  userService.register(registerRequest);
        assertEquals(1, savedUser.getId());
        String loginUser = userService.login(loginRequest);
        assertEquals("moyex33@gmail.com WELCOME" , loginUser);

    }


}