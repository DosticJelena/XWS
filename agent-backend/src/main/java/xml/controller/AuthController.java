package xml.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import xml.dto.request.LoginDTO;
import xml.dto.request.MailDTO;
import xml.dto.request.RegisterDTO;
import xml.model.Person;
import xml.service.AuthService;
import xml.util.QueueProducer;

@RestController
@RequestMapping(value = "auth", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private QueueProducer queueProducer;

    @RequestMapping(consumes = "application/json", produces= "application/json", value = "login", method = RequestMethod.POST)
    public ResponseEntity<?> loginUser(@RequestBody LoginDTO loginDto) {
        Person au = authService.findByUsername(loginDto.getUsername());

        if(au != null) {
            if (!au.getPassword().equals(loginDto.getPassword())){
                return new ResponseEntity<>(au, HttpStatus.BAD_REQUEST);
            }

            return new ResponseEntity<>(au, HttpStatus.OK);
        }

        return new ResponseEntity<>(au, HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(consumes = "application/json", produces= "application/json", value = "register", method = RequestMethod.POST)
    public ResponseEntity<?> registerUser(@RequestBody RegisterDTO dto) {
        Person au = authService.findByUsername(dto.getUsername());

        if(au == null) {
            System.out.println("Slanje emaila...");

            MailDTO mail = new MailDTO();
            mail.setUsername(dto.getUsername());
            mail.setName(dto.getFirstName());
            try {
                queueProducer.produce(mail);
            } catch (Exception e) {
                System.out.println("Nisam poslao agent mail");
                e.printStackTrace();
            }

            return new ResponseEntity<>(authService.save(dto), HttpStatus.CREATED);
        }

        return new ResponseEntity<>(0, HttpStatus.BAD_REQUEST);
    }

}
