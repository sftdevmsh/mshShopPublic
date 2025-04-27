package org.msh.serv.dto.payment;


import lombok.*;
import org.msh.repo.enums.PaymentGateway;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GotoPaymentDto {

    //UserDto
    private String username;
    private String password;
    private String mobile;
    private String email;

    //customerDto
    private String firstname;
    private String lastname;
    private String tel;
    private String address;
    private String postalCode;

    private PaymentGateway paymentGateway;//gateway

    //InvoiceDto
    private List<BasketItem> basketItems;
    //
    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class BasketItem
    {
        private Long productId;
        private Long colorId;
        private Long sizeId;
        private Integer quantity;
    }

}
