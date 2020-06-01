package xws.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Getter
@Setter
@DiscriminatorValue("A")
public class Admin extends ApplicationUser {

    @Column
    private String firstName;

    @Column
    private String lastName;
}
