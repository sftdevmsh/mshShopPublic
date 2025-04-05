package org.msh.entity.product;


import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity(name = "color")
@Table(name = "tbl_color")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ColorEnt {
    @Id
    @Column(name="id_color")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name", length = 1000)
    private String name;

    @Column(length = 8)
    private String hex;

}
