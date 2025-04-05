package org.msh.entity.order;

import jakarta.persistence.*;
import lombok.*;
import org.msh.enums.OrderStatus;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity(name = "invoice")
@Table(name = "tbl_invoice")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceEnt {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id_invoice")
    private Long id;

    @Column(name = "invoice_status")
    private OrderStatus orderStatus;

    @Column(name = "create_date")
    private LocalDateTime createDate;

    @Column(name = "paid_date")
    private LocalDateTime paidDate;

    private Integer no;

    @OneToMany(mappedBy = "invoiceEnt")
    private Set<InvoiceItemEnt> invoiceItemEnts;


}
