package MyWebPhonebook.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Person implements Serializable {
    @JsonIgnore
    @OneToMany(mappedBy = "phoneNumber") //name - the field name
    private final Set<PhoneNumber> phoneNumbers = new HashSet<>();

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    public Person() {}

    public Person(String name) {
        this.name = name;
    }

    public Set<PhoneNumber> getPhoneNumbers() {
        return phoneNumbers;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
