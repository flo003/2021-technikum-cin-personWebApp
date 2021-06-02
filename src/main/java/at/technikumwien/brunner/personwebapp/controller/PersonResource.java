package at.technikumwien.brunner.personwebapp.controller;

import at.technikumwien.brunner.personwebapp.model.Person;
import at.technikumwien.brunner.personwebapp.persistence.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/resources/persons")
public class PersonResource {

    private final PersonRepository personRepository;

    public PersonResource(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @GetMapping
    public List<Person> retrieveAll(@RequestParam(defaultValue = "false") boolean all){
        if(all){
            return personRepository.findAll();
        }
        return personRepository.findAllByActiveTrue();
    }

    @GetMapping("/{id}")
    public Person retrieve(@PathVariable long id){
        return personRepository.findById(id).get();
    }
}
