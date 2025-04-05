//package org.msh.entity.product;
//
//import jakarta.persistence.*;
//import lombok.*;
//
//@Entity
//@Table(name = "tbl_rel_product_color")
//@Getter
//@Setter
//@Builder
//@AllArgsConstructor
//@NoArgsConstructor
//public class RelProductColor {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id_product_color")
//    private Long id;
//
//
//
//
//    @Column(length = 400
//            ,columnDefinition = "TEXT"
//            )
//    private String description;
//
//
//    @ManyToOne
//    @JoinColumn(name = "id_product")
//    private ProductEnt productEnt;
//
//    @ManyToOne
//    @JoinColumn(name = "id_color")
//    private ColorEnt colorEnt;
//
//}
