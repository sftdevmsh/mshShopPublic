package org.msh.serv.dto.product;

import lombok.*;
import org.msh.repo.entity.file.FileEnt;
import org.msh.repo.entity.product.ProductCategoryEnt;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {

    private Long id;

    private String title;

    private String description;

    private Long price;

    private Integer visitCount;


    private LocalDateTime addDate;

    private FileEnt img;


    private Set<ColorDto> colorDtos;


    //@JsonIgnore : use it in dto, preferably
    private Set<SizeDto> sizeDtos;

    private ProductCategoryEnt productCategoryEnt;

}
