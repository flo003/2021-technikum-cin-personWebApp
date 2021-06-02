package at.technikumwien.brunner.personwebapp.persistence;

import at.technikumwien.brunner.personwebapp.model.Person;
import at.technikumwien.brunner.personwebapp.model.Sex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;

import java.time.LocalDate;
import java.util.List;

@Configuration
@Profile("!test")
public class DBInitializer {

    private final PersonRepository personRepository;

    public DBInitializer(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void handleApplicationReady(){
        personRepository.saveAll(
                List.of(
                        new Person(Sex.Female, "Mar", "Ga", LocalDate.of(2019,1,1), true),
                        new Person(Sex.Male, "Nor", "Las", LocalDate.of(2020,2,1), true),
                        new Person(Sex.Intersex, "Haus", "Far", LocalDate.of(2020,3,3), false)
                ));

    }
}
