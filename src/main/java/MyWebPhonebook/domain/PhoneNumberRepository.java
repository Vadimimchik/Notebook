package MyWebPhonebook.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface PhoneNumberRepository extends JpaRepository<PhoneNumber, Long> {
    Collection<PhoneNumber> findByPersonId(Long id); //query: from PhoneNumber pn WHERE pn.person.id = :id
    Collection<PhoneNumber> findByPersonName(String name); //query: from PhoneNumber pn WHERE pn.person.name = :name
}
