package org.msh.dto.invoice;

import jakarta.persistence.*;
import lombok.*;
import org.msh.enums.OrderStatus;

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

}
