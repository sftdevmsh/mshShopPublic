package org.msh.serv.dto.product;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductCategoryDto {

    private Long id;

    private String title;

    private String description;

}
