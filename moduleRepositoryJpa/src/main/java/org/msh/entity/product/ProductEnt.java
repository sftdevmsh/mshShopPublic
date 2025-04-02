package org.msh.entity.product;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Entity(name = "product")
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

    @Column(name = "sku", nullable = false)
    private String SKU;

    private String brand;
    private String model;
    private Long price;

    @OneToMany(mappedBy = "productEnt")
    private List<RelProductColor> lstRelProductColor;

}
