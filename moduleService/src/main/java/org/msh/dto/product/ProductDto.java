package org.msh.dto.product;

import lombok.*;
import org.msh.dto.file.FileDto;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    private Long id;
    private Long price;
    //private Boolean enabled;
    private Boolean exist;
    //@JsonIgnore : use it in dto, preferably
    private FileDto img;
    private Set<ColorDto> colorDtos;
    private Set<SizeDto> sizeDtos;
}
