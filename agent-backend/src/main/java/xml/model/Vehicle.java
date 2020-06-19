package xml.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long owner_id;

    @Column
    private String location;

    @Column
    private String fuel_type;

    @Column
    private String brand;

    @Column
    private String model;

    @Column
    private String transmission;

    @Column
    private String vehicle_type;

    @Column
    private double price;

    @Column
    private double distance;

    @Column
    private double distancePerRent;

    @Column
    private String distancePerRentStatus;

    @Column
    private double additionalPricePerKm;

    @Column
    private String CDWStatus;

    @Column
    private int childrenSeats;

    @JsonBackReference
    @OneToMany(mappedBy = "vehicle",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Set<Picture> pictures = new HashSet<>();
}
