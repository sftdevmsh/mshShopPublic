package org.msh.entity.product;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;

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

    @Column(name = "color")
    private String color;

    @Column(name = "hex")
    private String hex;

    @OneToMany(mappedBy = "colorEnt")
    private List<RelProductColor> lstRelProductColor;
}
