package net.revincompany.JournalApp.controller;

import net.revincompany.JournalApp.entity.JournalEntry;
import net.revincompany.JournalApp.entity.User;
import net.revincompany.JournalApp.service.JournalEntryService;
import net.revincompany.JournalApp.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


//controller -> service -> repo

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAll();
    }

    @PostMapping
    public void createUser(@RequestBody User user){
        userService.saveEntry(user);
    }

    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody User user){
        User byUserName = userService.findbyUserName(user.getUserName());
        if(byUserName!=null){
            byUserName.setUserName(user.getUserName());
            byUserName.setPassword(user.getPassword());
            userService.saveEntry(byUserName);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
