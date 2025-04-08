package org.msh.dto.invoice;

import jakarta.persistence.*;
import lombok.*;
import org.msh.dto.product.ColorDto;
import org.msh.dto.product.ProductDto;
import org.msh.dto.product.SizeDto;
import org.msh.entity.product.ColorEnt;
import org.msh.entity.product.ProductEnt;
import org.msh.entity.product.SizeEnt;

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
