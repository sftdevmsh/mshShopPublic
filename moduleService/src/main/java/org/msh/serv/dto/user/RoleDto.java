package org.msh.serv.dto.user;

import lombok.*;

import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoleDto {
    private Long id;
    private String name;
    private String description;
    private Set<PermissionDto> permissionDtos;
}
