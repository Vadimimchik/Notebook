package MyWebPhonebook.Web;

import MyWebPhonebook.domain.Person;
import MyWebPhonebook.domain.PersonRepository;
import MyWebPhonebook.domain.PhoneNumber;
import MyWebPhonebook.domain.PhoneNumberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/demo")

public class PhoneBookRestController {
    private final PhoneNumberRepository phoneNumberRepository;
    private final PersonRepository personRepository;

    @Autowired // this means to get the beans called phoneNumberRepository
    // and personRepository, which are auto-generated by Spring;
    // one will use them to handle the data:
    public PhoneBookRestController(PhoneNumberRepository phoneNumberRepository, PersonRepository personRepository) {
        this.phoneNumberRepository = phoneNumberRepository;
        this.personRepository = personRepository;
    }

    @RequestMapping(method = RequestMethod.GET, path="/{personName}/add_person")
    // @ResponseBody means the returned String is the response, not a view name
    public @ResponseBody String addNewPerson(
            // @RequestParam means it is a parameter from the GET request
            @PathVariable String personName)
    {
        Person person = new Person(personName);
        personRepository.save(person);

        return personName + " added to phone book!!!";
    }

    @RequestMapping(method = RequestMethod.GET, path="/get_all_people")
    public @ResponseBody Iterable<Person> getAllPeople() {
        // returns a JSON or XML with the people
        return personRepository.findAll();
    }

    @RequestMapping(method = RequestMethod.GET, path="/{personName}/add_phone_number") //?number=
    // @ResponseBody means the returned String is the response, not a view name
    public @ResponseBody String addNewPhoneNumber(
            // @RequestParam means it is a parameter from the GET request
            @PathVariable String personName,
            @RequestParam String number)
    {
        this.validatePerson(personName);

        Person person = personRepository.findByName(personName);
        PhoneNumber phoneNumber = new PhoneNumber(person, number);
        phoneNumberRepository.save(phoneNumber);
        return "New number added!!!";
    }

    @RequestMapping(method = RequestMethod.GET, path="/get_all_numbers")
    public @ResponseBody Iterable<PhoneNumber> getAllNumbers() {
        // returns a JSON or XML with the authors
        return phoneNumberRepository.findAll();
    }

    // map HTTP GET requests to serving method:
    @RequestMapping(method = RequestMethod.GET, path="/{personName}/get_all_numbers")
    public @ResponseBody Collection<PhoneNumber> getAllnumbersByPerson(@PathVariable String personName) {
        return phoneNumberRepository.findByPersonName(personName);
    }

//    @RequestMapping(method = RequestMethod.GET,
//            path = "/get_number_by_id")
//    public @ResponseBody PhoneNumber getNumberById(@RequestParam Long id) {
//        return phoneNumberRepository.findOne(id);
//    }
//
    private void validatePerson(String name) {
        if(personRepository.findByName(name) == null)
            throw new PersonNotFoundException(name);
    }
}
