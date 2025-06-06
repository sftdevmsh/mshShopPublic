package org.msh.repo.entity.payment;

import jakarta.persistence.*;
import lombok.*;
import org.msh.repo.entity.invoice.InvoiceEnt;
import org.msh.repo.entity.user.UserEnt;
import org.msh.repo.enums.PaymentGateway;

@Entity(name = "TransactionEnt")
@Table(name = "tbl_transaction")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionEnt  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long amount;

    @ManyToOne
    private InvoiceEnt invoiceEnt;//totalAmount, invoiceItems/basketItems

    @ManyToOne
    private UserEnt userEnt;//customer

    //@ManyToOne
    //private GatewayEnt gatewayEnt;//gateway
    PaymentGateway paymentGateway;

    @Column(columnDefinition = "TEXT")
    private String description;


    //token resonse
    private String authority;
    private String code;
    private String resultMessage;


    //verify response
    private String verifyCode; //=> 100 means success, so show transaction reference (refId) to customer
    private String verifyResultMessage;
    private String refId;
    private String cardHash;
    private String cardPan;

}
