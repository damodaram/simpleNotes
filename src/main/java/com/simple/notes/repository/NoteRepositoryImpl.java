package com.simple.notes.repository;

import com.simple.notes.entities.Notes;
import com.simple.notes.repository.contract.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

/**
 * Created by damodaram.setti on 9/23/2018.
 */
@Transactional
public abstract class NoteRepositoryImpl implements NoteRepository{
}
