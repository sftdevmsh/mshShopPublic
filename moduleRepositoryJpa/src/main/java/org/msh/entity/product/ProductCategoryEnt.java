package org.msh.entity.product;

import jakarta.persistence.*;
import lombok.*;
import org.msh.entity.file.FileEnt;


@Entity(name = "ProductCategoryEnt")
@Table(name = "tbl_product_category")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductCategoryEnt  {

    @Id
    @Column(name="id_product_category")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 1000, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String description;

    private Boolean enabled = true;

    @ManyToOne
    private FileEnt img;
}
