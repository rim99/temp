package net.rim99.demo.account.repository.impl;

import net.rim99.demo.account.repository.PersonRepository;
import net.rim99.demo.account.repository.mapper.PersonMapper;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class PersonRepositoryIml implements PersonRepository {

    @Inject
    PersonMapper mapper;

    @Override
    public void createPerson(int id, String name) {
        mapper.add(id, name);
    }

    @Override
    public String getNameById(int id) {
       return mapper.getNameById(id);
    }

}
