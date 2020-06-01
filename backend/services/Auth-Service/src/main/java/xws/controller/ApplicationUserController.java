package xws.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import xws.model.ApplicationUser;
import xws.service.ApplicationUserService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "users", produces = MediaType.APPLICATION_JSON_VALUE)
public class ApplicationUserController {

    @Autowired
    private ApplicationUserService applicationUserService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<?> getAll() {
        List<ApplicationUser> users = applicationUserService.findAll();
        List<UserResponse> response = new ArrayList<>();

        for(ApplicationUser au : users) {
            if(!au.getStatus().equals(ApplicationUser.Status.DELETED)) {
                UserResponse ur = new UserResponse();
                ur.username = au.getUsername();
                ur.status = au.getStatus();
                response.add(ur);
            }
        }

        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    static class UserResponse {
        public String username;
        public ApplicationUser.Status status;
    }
}
