package at.technikumwien.brunner.personwebapp.persistence;

import at.technikumwien.brunner.personwebapp.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Long> {
    public List<Person> findAllByActiveTrue();
}
