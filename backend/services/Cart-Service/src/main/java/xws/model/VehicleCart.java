package xws.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VehicleCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long ownerId;

    @JsonManagedReference
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            mappedBy = "vehicles")
    private Set<Cart> carts = new HashSet<>();

    @JsonBackReference
    @OneToMany(mappedBy = "vehicle",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    Set<RentingRequestVehicle> rentingRequests = new HashSet<>();





}
