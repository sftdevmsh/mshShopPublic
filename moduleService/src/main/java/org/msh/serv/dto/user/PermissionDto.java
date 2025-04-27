package org.msh.serv.dto.user;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PermissionDto {
    private Long id;
    private String name;
    private String description;
    private PermissionDto parent;
//    private List<PermissionDto> children = new ArrayList<>();
}
