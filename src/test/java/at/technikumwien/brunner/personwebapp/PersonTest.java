package at.technikumwien.brunner.personwebapp;

// unit test

import at.technikumwien.brunner.personwebapp.model.Person;
import at.technikumwien.brunner.personwebapp.model.Sex;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PersonTest {

    private Person person;

    @BeforeEach
    public void setup(){
        person = new Person(Sex.Female, "Mar", "Ga", LocalDate.of(2019,1,1), true);
    }

    @Test
    public void testGetName(){
        assertEquals("Mar Ga", person.getName());
    }

    @Test
    public void testGetNameWithFirstNameNull(){
        person.setFirstname(null);
        assertThrows(IllegalArgumentException.class, () -> person.getName());
    }

    @Test
    public void testGetNameWithFirstNameBlank(){
        person.setFirstname("");
        assertThrows(IllegalArgumentException.class, () -> person.getName());
    }

    // TODO add more tests here
}
