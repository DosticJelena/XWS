package xws.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NegativeOrZero;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NewVehicleSagaDTO {
    private Long ownerId;
}
