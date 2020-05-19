package xws.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RentingRequestVehicle {

    //privremeno samo da ima nesto
    //potrebno dodati jos polja jer ovo nije sve sto treba
    public enum Status {
        PENDING,PAID
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long carId;

    @Column(nullable = false)
    private LocalDateTime startDate;

    @Column(nullable = false)
    private LocalDateTime endDate;

    @Column(nullable = false)
    private Status status;

    @Column(nullable = false)
    private Long price;

    @Column(nullable = false)
    private Long distance;

}
