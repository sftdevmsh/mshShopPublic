package org.msh.dto.payment;


import lombok.*;
import org.msh.enums.PaymentGateway;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GotoPaymentDto {

    private String firstname;
    private String lastname;
    private String username;
    private String password;
    private String mobile;
    private String tel;
    private String address;
    private String postalCode;
    private List<BasketItem> basketItems;
    private PaymentGateway paymentGateway;//gateway


    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public class BasketItem
    {
        private Long productId;
        private Long colorId;
        private Long sizeId;
        private Integer quantity;
    }

}
