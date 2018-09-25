package com.simple.notes.repository.contract;

import com.simple.notes.entities.User;
import com.simple.notes.simpleNotes.SimpleNotesApplication;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static org.junit.Assert.*;

/**
 * Created by damodaram.setti on 9/23/2018.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SimpleNotesApplication.class)
@ContextConfiguration
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void saveGetDeleteTest() throws Exception {
        User user = User.builder().id(UUID.randomUUID().toString()).emailId("abc@abc.com").firstName("abc").lastName("abc").phone("1234").password("abcd").build();
        userRepository.save(user);
        user = userRepository.get("abc@abc.com");
        userRepository.delete(user);
    }

    @Test
    public void findAllCountDeleteAllTest() throws Exception {
        User user = User.builder().id(UUID.randomUUID().toString()).emailId("abc@abc.com").firstName("abc").lastName("abc").phone("1234").password("abcd").build();
        userRepository.save(user);
        Iterable<User> users = userRepository.findAll();
        assertEquals(Lists.newArrayList(users).size(),1);

        user = User.builder().id(UUID.randomUUID().toString()).emailId("abc@abcd.com").firstName("abc").lastName("abc").phone("1234").password("abcd").build();
        userRepository.save(user);
        assertEquals(userRepository.count(),2);
        userRepository.deleteAll();
        assertEquals(userRepository.count(),0);
    }

}