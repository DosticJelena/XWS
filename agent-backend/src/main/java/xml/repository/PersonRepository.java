package xml.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xml.model.Person;

public interface PersonRepository extends JpaRepository<Person,Long> {

    Person findByUsername(String username);
    Person findOneById(Long id);

    Person save(Person p);

}
