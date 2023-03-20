package service;

import data.model.User;
import data.repository.UserRepository;
import data.repository.UserRepositoryImpl;
import dtos.request.LoginRequest;
import dtos.request.RegisterRequest;
import dtos.response.FindLoginResponse;
import util.Mapper;


public class UserServiceImpl implements UserService{
    UserRepository userRepository = new UserRepositoryImpl();
    @Override
    public User register(RegisterRequest registerRequest) {
        if (userExist(registerRequest.getEmailAddress())) System.err.println("username" + registerRequest.getEmailAddress()+"already taken");
        return userRepository.saveUser(Mapper.map(registerRequest));
    }

    private boolean userExist(String emailAddress) {
        User foundUser = userRepository.findUserByEmailAddress(emailAddress);
        return foundUser != null;
    }

    @Override
    public String login(LoginRequest loginRequest){
        FindLoginResponse loginResponse = new FindLoginResponse();
    boolean userHasRegistered = loginRequest.getEmailAddress().equals(userRepository.findUserByEmailAddress(loginRequest.getEmailAddress()).getEmailAddress());
    if (userHasRegistered) {
        Mapper.map(loginRequest, loginResponse);
        return loginResponse.getMessage();
//        openMail();
    }
    return "you do not have an account";

}
}
