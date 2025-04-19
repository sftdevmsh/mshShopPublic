package org.msh.dto.site;

import jakarta.persistence.*;
import lombok.*;
import org.msh.entity.file.FileEnt;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SliderDto {

    private Long id;

    private String title;

    private String link;

    private FileEnt img;

    private Integer orderNumber;

}
