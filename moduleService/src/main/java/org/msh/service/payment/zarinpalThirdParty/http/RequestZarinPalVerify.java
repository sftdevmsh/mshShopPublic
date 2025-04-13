package org.msh.service.payment.zarinpalThirdParty.http;


import lombok.*;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestZarinPalVerify {
    String merchant_id;
    String authority;
    Long amount;
}
