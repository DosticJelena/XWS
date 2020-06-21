package xml.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ReportRequestDTO {

    private Long vId;
    private Long rId;
    private double distance;
    private String additionalInfo;
}
