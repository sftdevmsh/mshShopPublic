package org.msh.dto.product;


import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ColorDto {
    private Long id;
    private String color;
    private String hex;
}
