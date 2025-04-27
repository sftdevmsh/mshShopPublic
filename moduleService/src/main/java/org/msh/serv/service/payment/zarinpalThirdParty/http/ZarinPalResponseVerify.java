package org.msh.serv.service.payment.zarinpalThirdParty.http;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ZarinPalResponseVerify {
    String code;
    String message;
    String ref_id;
    String card_pan;
    String card_hash;
    String fee_type;
    Integer fee;
}
