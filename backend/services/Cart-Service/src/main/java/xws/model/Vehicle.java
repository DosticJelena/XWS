package xws.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column()
    private Long ownerId;

    @JsonBackReference
    @OneToMany(mappedBy = "vehicle",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Set<CartVehicle> vehiclesInCart = new HashSet<>();



}
