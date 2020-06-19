package xml.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class NewVehicleRequestDTO {

    private Long owner_id;
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
    private List<String> pictures;
}