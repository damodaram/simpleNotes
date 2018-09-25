package com.simple.notes.repository.contract;

import com.simple.notes.entities.Notes;
import com.simple.notes.entities.User;
import com.simple.notes.simpleNotes.SimpleNotesApplication;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.*;

/**
 * Created by damodaram.setti on 9/24/2018.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SimpleNotesApplication.class)
@ContextConfiguration
public class NoteRepositoryTest {

    @Autowired
    private NoteRepository noteRepository;

    @Test
    public void saveGetDeleteTest() throws Exception {
        Notes notes = Notes.builder().id(UUID.randomUUID().toString()).title("Java first class").description("oops concepts").createdOn(new Date()).lastModifiedOn(new Date()).build();
        noteRepository.save(notes);
        notes = noteRepository.get("Java first class");
        noteRepository.delete(notes);
    }

    @Test
    public void findAllCountDeleteAllTest() throws Exception {
        Notes notes = Notes.builder().id(UUID.randomUUID().toString()).title("Java first class").description("oops concepts").createdOn(new Date()).lastModifiedOn(new Date()).build();
        noteRepository.save(notes);
        List<Notes> notees = noteRepository.findAll();
        assertEquals(notees.size(),1);
        notes = Notes.builder().id(UUID.randomUUID().toString()).title("Java first class").description("oops concepts").createdOn(new Date()).lastModifiedOn(new Date()).build();
        noteRepository.save(notes);
        assertEquals(noteRepository.count(),2);
        noteRepository.deleteAll();
        assertEquals(noteRepository.count(),0);
    }

}