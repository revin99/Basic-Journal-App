package net.revincompany.JournalApp.controller;

import net.revincompany.JournalApp.api_response.WeatherResponse;
import net.revincompany.JournalApp.entity.User;
import net.revincompany.JournalApp.repository.UserRepository;
import net.revincompany.JournalApp.service.UserService;
import net.revincompany.JournalApp.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


//controller -> service -> repo

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

//    @GetMapping
//    public List<User> getAllUsers() {
//        return userService.getAll();
//    }



    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody User user){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();

        User userInDb = userService.findbyUserName(userName);
        userInDb.setUserName(user.getUserName());
        userInDb.setPassword(user.getPassword());
        userService.saveNewEntry(userInDb);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteUserByIs(){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        userRepository.deleteByUserName(authentication.getName());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<?> greeting() throws IOException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        WeatherService.getWeather("Mumbai");
//        String greeting ="";
//        if(weatherResponse!= null){
//            greeting=",Weather feels like " + weatherResponse.getCurrent().getFeelslike();
//        }
        return new ResponseEntity<>("HI " +authentication.getName(),HttpStatus.OK);
    }

}
