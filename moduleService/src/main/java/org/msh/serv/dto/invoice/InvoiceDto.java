package org.msh.serv.dto.invoice;

import lombok.*;
import org.msh.repo.entity.user.UserEnt;
import org.msh.repo.enums.OrderStatus;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceDto {

    private Long id;

    private OrderStatus orderStatus;

    private LocalDateTime createDate;

    private LocalDateTime paidDate;

    //private Integer no;
    private Long totalAmount;//totalAmount

    private Set<InvoiceItemDto> invoiceItemDtos;

    private UserEnt userEnt;

}
