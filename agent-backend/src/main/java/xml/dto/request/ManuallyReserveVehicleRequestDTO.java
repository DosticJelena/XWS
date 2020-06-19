package xml.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ManuallyReserveVehicleRequestDTO {
    private Long vehicleId;
    private String startDate;
    private String endDate;

}