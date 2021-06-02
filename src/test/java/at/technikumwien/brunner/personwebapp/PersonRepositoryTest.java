package at.technikumwien.brunner.personwebapp;

import at.technikumwien.brunner.personwebapp.model.Person;
import at.technikumwien.brunner.personwebapp.model.Sex;
import at.technikumwien.brunner.personwebapp.persistence.PersonRepository;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest()
@ActiveProfiles("test")
@Transactional // roll backs transaction after each test method
// auf einzelene Methoden anwendbar
// es gibt auch @Commit und @Rollback um das für jede Methode einzeln zu konfigurieren
@Tag("extended")
public class PersonRepositoryTest {
    @Autowired
    private PersonRepository personRepository;

    @Test
    public void testSave(){
        Person person = new Person(Sex.Female, "Mar", "Ga", LocalDate.of(2019,1,1), true);
        assertNull(person.getId());
        person = personRepository.save(person);
        assertNotNull(person.getId());
        assertEquals(person, personRepository.findById(person.getId()).get());
    }

    @Test
    public void testFindAllByActiveTrue(){
        var persons = personRepository.findAllByActiveTrue();
        assertEquals(2, persons.size());
        assertEquals(4, personRepository.findAll().size());
    }

}
