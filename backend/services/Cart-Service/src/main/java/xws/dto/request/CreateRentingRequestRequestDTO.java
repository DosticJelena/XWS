package xws.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreateRentingRequestRequestDTO {
    private Long userId;
    private String startDate;
    private String endDate;
}
