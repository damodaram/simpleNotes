package com.simple.notes.repository;

import com.simple.notes.repository.contract.UserRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by damodaram.setti on 9/23/2018.
 */
@Transactional
public abstract class UserRepositoryImpl implements UserRepository{
}
