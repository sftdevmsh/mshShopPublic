package org.msh.entity.payment;

import jakarta.persistence.*;
import lombok.*;
import org.msh.entity.order.InvoiceItemEnt;
import org.msh.enums.OrderStatus;

import java.time.LocalDateTime;
import java.util.Set;

@Entity(name = "payment")
@Table(name = "tbl_payment")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentEnt {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id_payment")
    private Long id;

    @Column(length = 1000, nullable = false)
    private String name;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String description;

}
