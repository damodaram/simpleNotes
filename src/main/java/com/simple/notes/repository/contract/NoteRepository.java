package com.simple.notes.repository.contract;

import com.simple.notes.entities.Notes;
import com.simple.notes.entities.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by damodaram.setti on 9/23/2018.
 */
public interface NoteRepository extends Repository<Notes, String> {

    Notes save(Notes notes);

    @Query("from Notes u where u.title=?1")
    Notes get(String title);

    @Query("from Notes u where u.id=?1")
    Notes find(String id);

    List<Notes> findAll();

    long count();

    void delete(Notes var1);

    @Modifying
    @Transactional
    @Query("delete from Notes n where n.id=?1")
    void delete(String id);

    @Modifying
    @Transactional
    @Query("UPDATE Notes SET title=?1, description=?2, lastModifiedOn=?3 WHERE id=?4")
    void update(String title ,String description, Date lastModifiedOn, String id);

    void deleteAll();
}
