package org.msh.dto.site;

import jakarta.persistence.*;
import lombok.*;
import org.msh.entity.file.FileEnt;
import org.msh.enums.BlogStatus;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BlogDto {

    private Long id;

    private String title;

    private String subTitle;

    private LocalDateTime publishDate;

    private Integer visitCount;

    private FileEnt img;

    private String description;

    private BlogStatus blogStatus;

}
