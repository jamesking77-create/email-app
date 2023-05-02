package myEmailApp.data.repository;

import myEmailApp.data.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserRepository extends MongoRepository<User, String > {

     User findUserByEmailAddress(String emailAddress);




}
