package xml.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MailDTO {

    private Long id;

    private String role;

    private String username;

    private String firstName;

    private String lastName;

    private String name;
}