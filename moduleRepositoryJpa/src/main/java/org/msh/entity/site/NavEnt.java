package org.msh.entity.site;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "nav")
@Table(name = "tbl_nav")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NavEnt {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id_nav")
    private Long id;

    @Column(length = 1000, nullable = false)
    private String title;

    @Column(length = 1000, nullable = false)
    private String link;

    private Integer orderNumber;

    private Boolean enabled = true;

}
