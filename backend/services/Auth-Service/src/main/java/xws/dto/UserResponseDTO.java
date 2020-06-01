package xws.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import xws.model.ApplicationUser;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDTO {
    private String username;
    private ApplicationUser.Status status;

    public UserResponseDTO(ApplicationUser au) {
        this.username = au.getUsername();
        this.status = au.getStatus();
    }
}
