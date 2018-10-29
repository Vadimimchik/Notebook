package MyWebPhonebook.domain;

import MyWebPhonebook.domain.Person;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class PhoneNumber implements Serializable {
    @JsonIgnore
    @ManyToOne
    private Person person;

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "phoneNumber")
    String phoneNumber;

    public PhoneNumber() {}

    public PhoneNumber(Person person, String phoneNumber) {
        this.person = person;
        this.phoneNumber = phoneNumber;
    }

    public Person getPerson() {
        return person;
    }

    public Long getId() {
        return id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }


}
