package org.msh.entity.product;


import jakarta.persistence.*;
import lombok.*;

@Entity(name = "SizeEnt")
@Table(name = "tbl_size")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SizeEnt {
    @Id
    @Column(name="id_size")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 1000)
    private String name;

    @Column(length = 1000)
    private String description;

}
