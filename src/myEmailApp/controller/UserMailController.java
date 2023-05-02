package myEmailApp.controller;
import myEmailApp.exceptions.UnregisteredUserException;
import myEmailApp.exceptions.WrongInfoError;
import myEmailApp.dtos.request.ComposeMailRequest;
import myEmailApp.dtos.request.LoginRequest;
import myEmailApp.dtos.request.RegisterRequest;
import myEmailApp.service.UserService;
import myEmailApp.service.UserServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class UserMailController {
private final UserService userService = new UserServiceImpl();

    @PostMapping("/user/registerUser")
    public ResponseEntity<?> registerUser(@RequestBody RegisterRequest registerRequest){
        try {
           return new ResponseEntity<>(userService.register(registerRequest), HttpStatus.CREATED) ;
        } catch (WrongInfoError e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST) ;
        }

    }

    @PostMapping("/user/loginUser")
    public ResponseEntity<?> login( @RequestBody LoginRequest loginRequest){
        try {
            return  new ResponseEntity<>(userService.login(loginRequest), HttpStatus.CREATED);
        }catch (WrongInfoError | UnregisteredUserException e){
            return new ResponseEntity<>( e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/user/sendMail")
    public ResponseEntity<?> sendMail(@RequestBody ComposeMailRequest mailRequest){
        try {
            return  new ResponseEntity<>(userService.sendMail(mailRequest),HttpStatus.CREATED);
        }catch (WrongInfoError e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST   );
        }
    }


    @GetMapping("/user/viewInbox/{emailAddress}")
    public ResponseEntity<?> viewInbox(@PathVariable String emailAddress){
        try {
            return new ResponseEntity<>(userService.viewInbox(emailAddress), HttpStatus.OK);
        }catch ( WrongInfoError e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST );
        }
    }


    @GetMapping("/user/viewOutBox/{emailAddress}")
    public ResponseEntity<?> viewOutBox(@PathVariable String emailAddress){
        try {
            return new ResponseEntity<>(userService.viewOutBox(emailAddress), HttpStatus.OK);
        }catch ( WrongInfoError e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/user/viewAllBox/{emailAddress}")
    public ResponseEntity<?> viewAllBox( @PathVariable String emailAddress){
        try {
            return new ResponseEntity<>( userService.viewAllMail(emailAddress), HttpStatus.OK);
        }catch (WrongInfoError e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/user/viewSentBox/{emailAddress}")
    public ResponseEntity<?> viewSentBox( @PathVariable String emailAddress){
        try {
            return new ResponseEntity<>(userService.viewSentBox(emailAddress), HttpStatus.OK);
        }catch (WrongInfoError e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/user/deleteMailById")
    public ResponseEntity<?> deleteMailById(String id, String email){
        try {
            return  new ResponseEntity<>(userService.deleteMail(id, email), HttpStatus.OK);
        } catch ( WrongInfoError e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }


    @DeleteMapping( value = "/user/deleteMailBySubject")
    public ResponseEntity<?> deleteMailBySubject(String subject, String email){
        try {
            return  new ResponseEntity<>(userService.deleteMail(subject, email), HttpStatus.OK);
        } catch (WrongInfoError e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/user/deleteAllMailBySubject")
    public ResponseEntity<?> deleteAllMailBySubject(String email){
        try {
            return  new ResponseEntity<>(userService.deleteAllMail(email), HttpStatus.OK);
        } catch (WrongInfoError e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/user/viewTrash/{emailAddress}")
    public ResponseEntity <?> viewTrash( @PathVariable String emailAddress){
        try {
            return new ResponseEntity<>( userService.viewTrash(emailAddress), HttpStatus.OK);
        }catch (WrongInfoError e){
            return new ResponseEntity<>( e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/user/restoreMailByIdt")
    public ResponseEntity<?> restoreMailById(@RequestBody String email, String  id){
        try {
            return new ResponseEntity<>(userService.restoreMail(email, id), HttpStatus.OK);
        } catch (WrongInfoError e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


}
