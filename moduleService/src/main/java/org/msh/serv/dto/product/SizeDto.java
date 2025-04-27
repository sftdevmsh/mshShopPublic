package org.msh.serv.dto.product;


import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SizeDto {
    private Long id;
    private String name;
    private String description;
}
