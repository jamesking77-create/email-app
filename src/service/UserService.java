package service;

import data.model.User;
import dtos.request.LoginRequest;
import dtos.request.RegisterRequest;
import dtos.response.FindLoginResponse;
import dtos.response.FindUserResponse;

public interface UserService {
    User register(RegisterRequest registerRequest);
    String login(LoginRequest loginRequest);

}
