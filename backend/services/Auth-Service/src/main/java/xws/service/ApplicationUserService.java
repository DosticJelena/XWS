package xws.service;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import xws.Exceptions.UserCantBeCreated;
import xws.dto.NewCartDTO;
import xws.dto.RegisterDTO;
import xws.feignClients.CartServiceProxy;
import xws.model.ApplicationUser;
import xws.model.Person;
import xws.repository.ApplicationUserRepository;


import java.util.List;

@Service
public class ApplicationUserService {

    Logger logger = LoggerFactory.getLogger(ApplicationUserService.class);

    @Autowired
    private ApplicationUserRepository applicationUserRepository;

    @Autowired
    private CartServiceProxy cartServiceProxy;

    @Autowired
    private RabbitTemplate template;

    @Autowired
    private DirectExchange exchange;


    public List<ApplicationUser> findAll() { return applicationUserRepository.findAll(); }

    public ApplicationUser findOneById(Long id) { return applicationUserRepository.findOneById(id); }

    public ApplicationUser findOneByUsername(String username) { return applicationUserRepository.findOneByUsername(username); }

    @Transactional
    public ApplicationUser save(RegisterDTO dto) throws UserCantBeCreated {

        Person person = new Person();
        person.setStatus(ApplicationUser.Status.ACTIVE);
        person.setRole(ApplicationUser.Role.PERSON);
        person.setUsername(dto.getUsername());
        person.setPassword(dto.getPassword());
        person.setFirstName(dto.getFirstName());
        person.setLastName(dto.getLastName());
        ApplicationUser createdUser = applicationUserRepository.save(person);


        //cartServiceProxy.addCart(createdUser.getId());
        logger.info(" [x] Sending user info to make cart: '" + createdUser.getId() + "'");
        NewCartDTO req = new NewCartDTO(createdUser.getId());
        Boolean sucess = (Boolean) this.template.convertSendAndReceive(exchange.getName(),"rpc", new Gson().toJson(req));
        logger.info("Task status: " + sucess);
        if(sucess == true) {
             return createdUser;
        }else {
            throw new UserCantBeCreated("user cant be created");
        }


    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public int updateUserStatus(ApplicationUser.Status status, Long id) {
        ApplicationUser au = applicationUserRepository.findOneById(id);
        au.setStatus(status);
        applicationUserRepository.save(au);
        return 1;
    }
}
