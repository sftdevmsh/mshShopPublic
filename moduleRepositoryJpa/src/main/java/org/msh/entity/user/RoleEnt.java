package org.msh.entity.user;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity(name = "RoleEnt")
@Table(name = "tbl_role")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoleEnt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_role")
    private Long id;

    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @ManyToMany
    @JoinTable(name = "rel_role_permission"
                    ,joinColumns = @JoinColumn(name = "id_role")
                    ,inverseJoinColumns = @JoinColumn(name = "id_permission"))
    private Set<PermissionEnt> permissionEnts;
}
