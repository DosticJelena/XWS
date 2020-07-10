package xml.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xml.dto.request.RegisterDTO;
import xml.model.Person;
import xml.repository.PersonRepository;

@Service
public class AuthService {

    @Autowired
    private PersonRepository personRepository;

    public Person findByUsername(String username) {
        return personRepository.findByUsername(username);
    }

    public Person save(RegisterDTO dto) {

        Person person = new Person();
        person.setUsername(dto.getUsername());
        person.setPassword(dto.getPassword());
        person.setFirstName(dto.getFirstName());
        person.setLastName(dto.getLastName());
        Person createdUser = personRepository.save(person);

        return createdUser;
    }

}
