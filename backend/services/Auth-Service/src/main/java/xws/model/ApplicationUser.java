package xws.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(discriminatorType = DiscriminatorType.STRING)
public abstract class ApplicationUser {

    //privremeno samo da ima nesto
    public enum Status {
        ACTIVE, DELETED, BLOCKED
    }

    public enum Role {
        ADMIN, PERSON, COMPANY
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private Status status;

    @Column(nullable = false)
    private Role role;

}
