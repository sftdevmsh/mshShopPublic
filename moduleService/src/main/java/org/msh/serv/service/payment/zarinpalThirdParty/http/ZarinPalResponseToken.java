package org.msh.serv.service.payment.zarinpalThirdParty.http;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ZarinPalResponseToken {
    String code;
    String message;
    String authority;
    String fee_type;
    Integer fee;
}
