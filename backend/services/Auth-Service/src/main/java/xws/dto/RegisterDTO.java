package xws.dto;

import lombok.Getter;
import xws.model.ApplicationUser;

import javax.persistence.Column;

@Getter
public class RegisterDTO {

    private String username;
    private String firstName;
    private String lastName;
    private String password;
}
