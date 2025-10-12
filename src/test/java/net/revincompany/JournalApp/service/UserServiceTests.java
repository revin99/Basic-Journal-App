package net.revincompany.JournalApp.service;

import net.revincompany.JournalApp.entity.User;
import net.revincompany.JournalApp.repository.UserRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Disabled
public class UserServiceTests {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testFindByUserName(){

        //assertEquals(4,2+2);
        assertNotNull(userRepository.findByUserName("revin"));
    }

    @Disabled
    @Test
    public void testCheckJournalEntries(){
        User user = userRepository.findByUserName("revin");
        assertTrue(user.getJournalEntries().isEmpty());
    }

    @Disabled
    @ParameterizedTest
    @CsvSource({
            "1,1,2",
            "2,10,12",
            "3,3,9"
    })
    public void testAdd(int a, int b, int expected)
    {
        assertEquals(expected,a+b);
    }


    @ParameterizedTest
    @ValueSource(strings={
            "ram",
            "revin",
            "shyam"
    })
    public void testFindByUserNameParam(String name)
    {
        assertNotNull(userRepository.findByUserName(name));
    }
}
