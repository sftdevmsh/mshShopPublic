package org.msh.entity.invoice;

import jakarta.persistence.*;
import lombok.*;
import org.msh.entity.user.UserEnt;
import org.msh.enums.OrderStatus;

import java.time.LocalDateTime;
import java.util.Set;

@Entity(name = "InvoiceEnt")
@Table(name = "tbl_invoice")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceEnt {

    @Column(name = "id_invoice")
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(name = "invoice_status")
    private OrderStatus orderStatus;

    @Column(name = "create_date")
    private LocalDateTime createDate;

    @Column(name = "paid_date")
    private LocalDateTime paidDate;

//    private Integer no;

    private Long totalAmount;//totalAmount

    @OneToMany(mappedBy = "invoiceEnt")
    private Set<InvoiceItemEnt> invoiceItemEnts;

    @ManyToOne
    private UserEnt userEnt;


}
