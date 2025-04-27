package org.msh.serv.service.payment.zarinpalThirdParty.http;


import lombok.*;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ZarinPalRequestVerify {
    String merchant_id;
    String authority;
    Long amount;
}
