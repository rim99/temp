package net.rim99.demo.account.support.service.repository;

import net.rim99.demo.account.support.guice.GlobalInjector;
import net.rim99.demo.account.support.service.repository.help.PersonRepository;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PersonRepositoryTests extends RepositoryTest{

    private PersonRepository personRepository;

    @Before
    public void setup() {
        personRepository = GlobalInjector.getInstance(PersonRepository.class);
    }

    @Test
    public void should_create_person() {
        assert personRepository != null;
        personRepository.createPerson(1, "Jason");
        assertEquals("Jason", personRepository.getNameById(1));
    }
}
