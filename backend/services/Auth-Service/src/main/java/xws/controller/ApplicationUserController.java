package xws.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xws.dto.UpdateUserStatusDTO;
import xws.dto.UserResponseDTO;
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

    @RequestMapping(consumes = "application/json", value = "update-user-status", method = RequestMethod.POST)
    public ResponseEntity<?> updateUserStatus(@RequestBody UpdateUserStatusDTO updateUserStatusDTO) {
        ApplicationUser au = applicationUserService.findOneById(updateUserStatusDTO.getId());

        if(au.getId() != null) {
            applicationUserService.updateUserStatus(updateUserStatusDTO.getStatus(), updateUserStatusDTO.getId());
            au = applicationUserService.findOneById(updateUserStatusDTO.getId());

            return new ResponseEntity<>(new UserResponseDTO(au), HttpStatus.ACCEPTED);
        }

        return new ResponseEntity<>(new UserResponseDTO(), HttpStatus.BAD_REQUEST);
    }

}
