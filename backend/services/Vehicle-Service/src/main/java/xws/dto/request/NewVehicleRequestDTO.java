package xws.dto.request;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class NewVehicleRequestDTO {

    private Long user_id;
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
