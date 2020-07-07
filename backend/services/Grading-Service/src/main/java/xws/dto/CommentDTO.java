package xws.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommentDTO {

    @Column
    private String userUsername;

    @Column
    private Long carId;

    @Column
    private String text;

    @Column
    private String status;

}
