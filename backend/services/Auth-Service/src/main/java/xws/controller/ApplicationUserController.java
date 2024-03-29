package xws.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;
import xws.Exceptions.UserCantBeCreated;
import xws.dto.*;
import xws.model.Agent;
import xws.model.ApplicationUser;
import xws.model.Company;
import xws.repository.ApplicationUserRepository;
import xws.service.ApplicationUserService;
//import xws.util.QueueProducer;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
public class ApplicationUserController {
    Logger logger = LoggerFactory.getLogger(ApplicationUserController.class);

    @Autowired
    private ApplicationUserService applicationUserService;

    @Autowired
    private ApplicationUserRepository applicationUserRepository;
  
    //@Autowired
    //private QueueProducer queueProducer;


    @RequestMapping(value = "users", method = RequestMethod.GET)
    public ResponseEntity<?> getAll() {
        List<ApplicationUser> users = applicationUserService.findAll();
        List<UserResponseDTO> response = new ArrayList<>();

        for(ApplicationUser au : users) {
            if(!au.getStatus().equals(ApplicationUser.Status.DELETED)) {
                UserResponseDTO ur = new UserResponseDTO();
                ur.setUsername(au.getUsername());
                ur.setStatus(au.getStatus());
                ur.setId(au.getId());
                response.add(ur);
            }
        }

        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "users/{id}", method = RequestMethod.GET)
    public String getById(@PathVariable Long id) {
        return applicationUserService.findOneById(id).getUsername();
    }


    @RequestMapping(value = "blocked/{id}", method = RequestMethod.GET)
    public Boolean getBlocked(@PathVariable Long id) {
        return applicationUserService.findOneById(id).getStatus().equals(ApplicationUser.Status.BLOCKED);
    }

    @RequestMapping(consumes = "application/json", value = "user/status", method = RequestMethod.PUT)
    public ResponseEntity<?> updateUserStatus(@RequestBody UpdateUserStatusDTO updateUserStatusDTO) {
        ApplicationUser au = applicationUserService.findOneById(updateUserStatusDTO.getId());

        if(au.getId() != null) {
            applicationUserService.updateUserStatus(updateUserStatusDTO.getStatus(), updateUserStatusDTO.getId());
            au = applicationUserService.findOneById(updateUserStatusDTO.getId());

            return new ResponseEntity<>(new UserResponseDTO(au), HttpStatus.ACCEPTED);
        }

        return new ResponseEntity<>(new UserResponseDTO(), HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(consumes = "application/json", produces= "application/json", value = "login", method = RequestMethod.POST)
    public ResponseEntity<?> loginUser(@RequestBody LoginDTO loginDto) {
        ApplicationUser au = applicationUserService.findOneByUsername(loginDto.getUsername());

        if(au != null) {
            if (!au.getPassword().equals(loginDto.getPassword())){
                return new ResponseEntity<>(new UserResponseDTO(), HttpStatus.BAD_REQUEST);
            }

            return new ResponseEntity<>(au, HttpStatus.OK);
        }

        return new ResponseEntity<>(new UserResponseDTO(), HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(consumes = "application/json", produces= "application/json", value = "register", method = RequestMethod.POST)
    public ResponseEntity<?> registerUser(@RequestBody RegisterDTO dto) {
        ApplicationUser au = applicationUserService.findOneByUsername(dto.getUsername());

        if(au == null) {

            try {
                applicationUserService.save(dto);
            } catch (UserCantBeCreated userCantBeCreated) {
                return new ResponseEntity<>(userCantBeCreated.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
            }
            /*System.out.println("Slanje emaila...");

            MailDTO mail = new MailDTO();
            mail.setUsername(dto.getUsername());
            mail.setName(dto.getFirstName());
            try {
                queueProducer.produce(mail);
            } catch (Exception e) {
                System.out.println("Nisam poslao agent mail");
                e.printStackTrace();
            }*/
            return new ResponseEntity<>(1, HttpStatus.OK);
        }

        return new ResponseEntity<>(0, HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(consumes = "application/json", produces= "application/json", value = "register-agent", method = RequestMethod.POST)
    public ResponseEntity<?> registerAgent(@RequestBody RegisterAgentDTO dto) {
        ApplicationUser au = applicationUserService.findOneByUsername(dto.getUsername());

        if(au == null) {
            Agent a = new Agent();
            a.setUsername(dto.getUsername());
            a.setAddress(dto.getAddress());
            a.setFirstName(dto.getFirstName());
            a.setLastName(dto.getLastName());
            a.setPassword("123");
            a.setRole(ApplicationUser.Role.AGENT);
            a.setStatus(ApplicationUser.Status.ACTIVE);
            a.setPIB(dto.getPIB());
            applicationUserRepository.save(a);
            return new ResponseEntity<>(a, HttpStatus.OK);
        }

        return new ResponseEntity<>(0, HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(consumes = "application/json", produces= "application/json", value = "register-company", method = RequestMethod.POST)
    public ResponseEntity<?> registerCompany(@RequestBody RegisterCompanyDTO dto) {
        ApplicationUser au = applicationUserService.findOneByUsername(dto.getUsername());

        if(au == null) {
            Company a = new Company();
            a.setUsername(dto.getUsername());
            a.setAddress(dto.getAddress());
            a.setCompanyName(dto.getCompanyName());
            a.setPassword("123");
            a.setRole(ApplicationUser.Role.COMPANY);
            a.setStatus(ApplicationUser.Status.ACTIVE);
            a.setPIB(dto.getPIB());
            applicationUserRepository.save(a);
            return new ResponseEntity<>(a, HttpStatus.OK);
        }

        return new ResponseEntity<>(0, HttpStatus.BAD_REQUEST);
    }
}
