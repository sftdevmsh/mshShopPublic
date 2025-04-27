package org.msh.repo.entity.invoice;

import jakarta.persistence.*;
import lombok.*;
import org.msh.repo.entity.product.ColorEnt;
import org.msh.repo.entity.product.ProductEnt;
import org.msh.repo.entity.product.SizeEnt;

@Entity(name = "InvoiceItemEnt")
@Table(name = "tbl_invoice_item")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceItemEnt {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id_invoice_item")
    private Long id;


    @ManyToOne
    private ProductEnt productEnt;

    @ManyToOne
    private InvoiceEnt invoiceEnt;

    @ManyToOne
    private ColorEnt colorEnt;

    @ManyToOne
    private SizeEnt sizeEnt;

    private Integer quantity;
    private Long price;
}
