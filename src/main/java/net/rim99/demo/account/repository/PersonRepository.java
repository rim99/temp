package net.rim99.demo.account.repository;

public interface PersonRepository {

    void createPerson(int id, String name);

    String getNameById(int id);
}
