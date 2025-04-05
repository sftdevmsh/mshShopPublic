package org.msh.dto.product;

import jakarta.persistence.*;
import lombok.*;
import org.msh.entity.file.FileEnt;

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
