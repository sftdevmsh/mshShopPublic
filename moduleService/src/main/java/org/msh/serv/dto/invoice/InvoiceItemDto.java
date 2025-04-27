package org.msh.serv.dto.invoice;

import lombok.*;
import org.msh.serv.dto.product.ColorDto;
import org.msh.serv.dto.product.ProductDto;
import org.msh.serv.dto.product.SizeDto;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceItemDto {

    private Long id;
    private ProductDto productDto;
    //private InvoiceDto invoiceEnt;
    private ColorDto colorDto;
    private SizeDto sizeDto;
    private Integer quantity;
    private Long price;
}
