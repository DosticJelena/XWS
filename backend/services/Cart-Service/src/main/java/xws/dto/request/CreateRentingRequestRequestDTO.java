package xws.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreateRentingRequestRequestDTO {
    private Long cartId;
    private String startDate;
    private String endDate;
}
