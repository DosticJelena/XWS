package xml.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterDTO {

    private String username;
    private String password;
    private String firstName;
    private String lastName;

}
