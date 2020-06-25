package xws.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import xws.dto.RegisterDTO;
import xws.model.ApplicationUser;
import xws.model.Person;
import xws.repository.ApplicationUserRepository;

import java.util.List;

@Service
public class ApplicationUserService {

    @Autowired
    private ApplicationUserRepository applicationUserRepository;

    public List<ApplicationUser> findAll() { return applicationUserRepository.findAll(); }

    public ApplicationUser findOneById(Long id) { return applicationUserRepository.findOneById(id); }

    public ApplicationUser findOneByUsername(String username) { return applicationUserRepository.findOneByUsername(username); }

    public ApplicationUser save(RegisterDTO dto) {

        Person person = new Person();
        person.setStatus(ApplicationUser.Status.ACTIVE);
        person.setRole(ApplicationUser.Role.PERSON);
        person.setUsername(dto.getUsername());
        person.setPassword(dto.getPassword());
        person.setFirstName(dto.getFirstName());
        person.setLastName(dto.getLastName());

        return applicationUserRepository.save(person);

    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public int updateUserStatus(ApplicationUser.Status status, Long id) {
        ApplicationUser au = applicationUserRepository.findOneById(id);
        au.setStatus(status);
        applicationUserRepository.save(au);
        return 1;
    }
}
