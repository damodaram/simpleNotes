package com.simple.notes.repository.contract;

import com.simple.notes.entities.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by damodaram.setti on 9/23/2018.
 */
public interface UserRepository extends Repository<User, Long> {

    User save(User user);

    @Query("from User u where u.emailId=?1")
    User get(String emailId);

    @Query("from User u where u.emailId=?1 and u.password=?2")
    User get(String emailId, String password);

    Iterable<User> findAll();

    long count();

    void delete(User var1);

    void deleteAll();
}
