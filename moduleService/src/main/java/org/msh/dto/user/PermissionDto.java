package org.msh.dto.user;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

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
