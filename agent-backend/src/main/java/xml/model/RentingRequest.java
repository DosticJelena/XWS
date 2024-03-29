package xml.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class RentingRequest {

    //treba dodati prave statuse
    public enum Status {
        ACTIVE,PENDING,PAID,DECLINED
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long userId;

    @Column
    private Status status;

    @Column
    private LocalDateTime createdAt;

    @JsonBackReference
    @OneToMany(mappedBy = "rentingRequest",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    Set<RentingRequestVehicle> vehicles = new HashSet<>();
}