package org.msh.repo.entity.product;

import jakarta.persistence.*;
import lombok.*;
import org.msh.repo.entity.file.FileEnt;

import java.time.LocalDateTime;
import java.util.Set;


@Entity(name = "ProductEnt")
@Table(name = "tbl_product")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductEnt {
    @Id
    @Column(name="id_product")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(length = 1000, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String description;

    private Long price;

    @Column(name = "visit_count")
    private Integer visitCount;

    private Boolean enabled = true;

    private Boolean exist = true;

    @Column(name = "add_date")
    private LocalDateTime addDate;

    @ManyToOne
    @JoinColumn(name="img_id_file", nullable = false)
    private FileEnt img;

    @ManyToOne
    @JoinColumn(name="id_product_category", nullable = false)
    private ProductCategoryEnt productCategoryEnt;



    @ManyToMany
    @JoinTable(name = "rel_product_color"
                , joinColumns = @JoinColumn(name = "id_product")
                , inverseJoinColumns = @JoinColumn(name= "id_color"))
    private Set<ColorEnt> colorEnts;


    @ManyToMany
    @JoinTable(name = "rel_product_size"
            , joinColumns = @JoinColumn(name = "id_product")
            , inverseJoinColumns = @JoinColumn(name= "id_size"))
    private Set<SizeEnt> sizeEnts;

}
