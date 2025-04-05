package org.msh.entity.product;


import jakarta.persistence.*;
import lombok.*;

@Entity(name = "size")
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
    private String sizeName;

    @Column(length = 1000)
    private String description;

}
