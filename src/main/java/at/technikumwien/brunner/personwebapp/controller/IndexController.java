package at.technikumwien.brunner.personwebapp.controller;

import at.technikumwien.brunner.personwebapp.persistence.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IndexController {

    private final PersonRepository personRepository;

    public IndexController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @RequestMapping("/")
    public String index(Model model, @RequestParam(defaultValue = "false") boolean all){
        var persons = all ? personRepository.findAll() : personRepository.findAllByActiveTrue();
        model.addAttribute("personList", persons);
        if(all){
            model.addAttribute("all", true);
        }
        return "index";
    }
}
