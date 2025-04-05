package org.msh.dto.site;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NavDto {

    private Long id;

    private String title;

    private String link;

    private Integer orderNumber;

}
