package org.msh.dto.site;

import lombok.*;
import org.msh.entity.file.FileEnt;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SingleBlogDto {

    private Long id;

    private String title;

    private String subTitle;

    private String description;

    private LocalDateTime publishDate;

    private Integer visitCount;

    private FileEnt img;

}
