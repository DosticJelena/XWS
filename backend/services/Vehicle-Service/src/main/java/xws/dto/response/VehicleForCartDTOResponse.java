package xws.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VehicleForCartDTOResponse {

    private Long id;
    private Long owner_id;
    private String owner_name;
    private String location;
    private String fuel_type;
    private String brand;
    private String model;
    private String transmission;
    private String vehicle_type;
    private double price;
    private double distance;
    private double distancePerRent;
    private String distancePerRentStatus;
    private double additionalPricePerKm;
    private String CDWStatus;
    private int childrenSeats;
}
