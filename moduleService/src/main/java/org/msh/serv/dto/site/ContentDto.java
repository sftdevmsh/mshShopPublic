package org.msh.serv.dto.site;

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
