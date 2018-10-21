package net.rim99.demo.account.repository;

import net.rim99.demo.account.support.repository.Repositories;
import org.junit.Test;


import static org.junit.Assert.assertEquals;

public class PersonRepositoryTests extends RepositoryTest{

    PersonRepository personRepository = Repositories.getFactory().get(PersonRepository.class);

    @Test
    public void should_create_person() {
        assert personRepository != null;
        personRepository.createPerson(1, "Jason");
        assertEquals("Jason", personRepository.getNameById(1));
    }
}
