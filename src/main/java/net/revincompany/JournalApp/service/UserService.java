package net.revincompany.JournalApp.service;

import net.revincompany.JournalApp.entity.User;
import net.revincompany.JournalApp.repository.UserRepository;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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


    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    public void saveNewEntry(User user) {
        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRoles(Arrays.asList("USER"));
            userRepository.save(user);
        }catch (Exception e){
            logger.error("User already exists {} :",user.getUserName(),e);
        }
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public void saveAdminEntry(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("USER","ADMIN"));
        userRepository.save(user);
    }

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
