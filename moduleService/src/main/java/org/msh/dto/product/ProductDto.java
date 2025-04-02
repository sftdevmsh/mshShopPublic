package org.msh.dto.product;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    private Long id;
    private String SKU;
    private String brand;
    private String model;
    private Long price;
    //@JsonIgnore : use it in dto, preferably
    private List<ColorDto> lstColorDto;
}
