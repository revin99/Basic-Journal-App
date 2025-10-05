package net.revincompany.JournalApp.service;

import net.revincompany.JournalApp.entity.JournalEntry;
import net.revincompany.JournalApp.entity.User;
import net.revincompany.JournalApp.repository.JournalEntryRepository;
import net.revincompany.JournalApp.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public void saveEntry(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("USER"));
        userRepository.save(user);
    }

//    public void saveNewEntry(User user) {
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        user.setRoles(Arrays.asList("USER"));
//        userRepository.save(user);
//    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public Optional<User> findByID(ObjectId id) {
        return userRepository.findById(id);
    }

    public void deleteByID(ObjectId id) {
        userRepository.deleteById(id);
    }

    public User findbyUserName(String userName){
        return userRepository.findByUserName(userName);
    }


}
