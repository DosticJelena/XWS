package xws.dto;

import lombok.Getter;
import xws.model.ApplicationUser;

@Getter
public class UpdateUserStatusDTO {
    private Long id;
    private ApplicationUser.Status status;
}
