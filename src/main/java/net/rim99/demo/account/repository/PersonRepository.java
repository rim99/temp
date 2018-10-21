package net.rim99.demo.account.repository;

import net.rim99.demo.account.support.repository.Respository;

public interface PersonRepository extends Respository {

    void createPerson(int id, String name);

    String getNameById(int id);
}
