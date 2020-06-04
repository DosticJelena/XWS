package xws.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import xws.model.RentingRequestVehicle;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class RentingRequestResponseDTO {
    public Long id;
    public Long userId;
    public Set<RentingRequestVehicle> vehicles;
}
