package org.msh.service.payment.zarinpalThirdParty.http;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ZarinPalResponse {
    String code;
    String message;
    String authority;
    String fee_type;
    Integer fee;
}
