package xws.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CreateRentingRequestRequestDTO {
    private Long userId;
    private String startDate;
    private String endDate;
}
