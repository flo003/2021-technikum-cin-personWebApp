package at.technikumwien.brunner.personwebapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "t_person")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private Sex sex;
    @Column(nullable = false, length = 50)
    private String firstname;
    @Column(nullable = false, length = 50)
    private String lastname;

    @Column(nullable = false)
    private LocalDate birthDate;

    @Column(nullable = false)
    private boolean active;

    public Person(Sex sex, String firstname, String lastname, LocalDate birthDate, boolean active) {
        this(null,sex,firstname, lastname, birthDate, active);
    }

    @JsonIgnore
    public String getName(){
        if(firstname == null || firstname.isBlank()){
            throw new IllegalArgumentException("firstname is null or blank");
        }
        if(lastname == null || lastname.isBlank()){
            throw new IllegalArgumentException("lastname is null or blank");
        }
        return firstname + " " + lastname;
    }
}
