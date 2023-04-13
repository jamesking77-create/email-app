package myEmailApp.dtos.controller;
import myEmailApp.dtos.request.ComposeMailRequest;
import myEmailApp.dtos.request.LoginRequest;
import myEmailApp.dtos.request.RegisterRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import myEmailApp.dtos.service.UserService;
import myEmailApp.dtos.service.UserServiceImpl;
import myEmailApp.dtos.util.UnregisteredUserException;
import myEmailApp.dtos.util.WrongInfoError;


@RestController
public class UserMailController {
private final UserService userService = new UserServiceImpl();

    @PostMapping("/user/registerUser")
    public ResponseEntity<?> registerUser(@RequestBody RegisterRequest registerRequest){
        try {
           return new ResponseEntity<>(userService.register(registerRequest), HttpStatus.CREATED) ;
        } catch (WrongInfoError e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.SERVICE_UNAVAILABLE) ;
        }

    }

    @PostMapping("/user/loginUser")
    public ResponseEntity<?> login( @RequestBody LoginRequest loginRequest){
        try {
            return  new ResponseEntity<>(userService.login(loginRequest), HttpStatus.CREATED);
        }catch ( WrongInfoError | UnregisteredUserException e){
            return new ResponseEntity<>( e.getMessage(), HttpStatus.SERVICE_UNAVAILABLE);
        }
    }

    @PostMapping("/user/sendMail")
    public ResponseEntity<?> sendMail(@RequestBody ComposeMailRequest mailRequest){
        try {
            return  new ResponseEntity<>(userService.sendMail(mailRequest),HttpStatus.CREATED);
        }catch (WrongInfoError e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.SERVICE_UNAVAILABLE);
        }
    }


    @GetMapping("/user/viewInbox/{emailAddress}")
    public String viewInbox(@PathVariable String emailAddress){
        try {
            return userService.viewInbox(emailAddress);
        }catch ( WrongInfoError e){
            return e.getMessage();
        }
    }


    @GetMapping("/user/viewOutBox/{emailAddress}")
    public Object viewOutBox(@PathVariable String emailAddress){
        try {
            return userService.viewOutBox(emailAddress);
        }catch ( WrongInfoError e){
            return e.getMessage();
        }
    }

    @GetMapping("/user/viewAllBox/{emailAddress}")
    public Object viewAllBox( @PathVariable String emailAddress){
        try {
            return userService.viewAllMail(emailAddress);
        }catch (WrongInfoError e){
            return e.getMessage();
        }
    }

    @GetMapping("/user/viewSentBox/{emailAddress}")
    public Object viewSentBox( @PathVariable String emailAddress){
        try {
            return userService.viewSentBox(emailAddress);
        }catch (WrongInfoError e){
            return e.getMessage();
        }
    }

    @GetMapping("/user/ viewTrash/{emailAddress}")
    public Object viewTrash( @PathVariable String emailAddress){
        try {
            return userService.viewTrash(emailAddress);
        }catch (WrongInfoError e){
            return e.getMessage();
        }
    }
}
