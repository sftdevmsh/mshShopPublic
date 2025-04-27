package org.msh.repo.entity.user;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "PermissionEnt")
@Table(name = "tbl_permission")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PermissionEnt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_permission")
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name="description", columnDefinition = "TEXT")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_parent")
    private PermissionEnt parent;

    @OneToMany(mappedBy = "parent")
    private List<PermissionEnt> children = new ArrayList<PermissionEnt>();
}
