package org.msh.dto.site;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ContentDto {

    private Long id;

    private String title;

    private String value;

}
