package xml.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ReportRequestDTO {

    private Long vid;
    private Long rid;
    private double distance;
    private String additionalInfo;

}
