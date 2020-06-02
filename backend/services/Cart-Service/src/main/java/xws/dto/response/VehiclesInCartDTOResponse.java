package xws.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import xws.model.VehicleCart;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class VehiclesInCartDTOResponse {
    private Long userId;
    private Long cartId;
    private Set<VehicleCart> vehicles = new HashSet<>();

}
