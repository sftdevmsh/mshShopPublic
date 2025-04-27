package org.msh.serv.dto.site;

import lombok.*;
import org.msh.repo.entity.file.FileEnt;

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
