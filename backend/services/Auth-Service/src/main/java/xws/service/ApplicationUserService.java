package xws.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xws.model.ApplicationUser;
import xws.repository.ApplicationUserRepository;

import java.util.List;

@Service
public class ApplicationUserService {

    @Autowired
    private ApplicationUserRepository applicationUserRepository;

    public List<ApplicationUser> findAll() { return applicationUserRepository.findAll(); }
}
