package org.msh.dto.product;

import jakarta.persistence.*;
import lombok.*;
import org.msh.dto.file.FileDto;
import org.msh.entity.file.FileEnt;
import org.msh.entity.product.ColorEnt;
import org.msh.entity.product.ProductCategoryEnt;
import org.msh.entity.product.SizeEnt;

import java.time.LocalDateTime;
import java.util.List;
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
