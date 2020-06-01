package xws.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

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
    private String type; //enum

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


}
