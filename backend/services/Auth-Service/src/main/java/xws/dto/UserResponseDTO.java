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
    private Long id;

    public UserResponseDTO(ApplicationUser au) {
        this.username = au.getUsername();
        this.status = au.getStatus();
        this.id = au.getId();
    }
}
