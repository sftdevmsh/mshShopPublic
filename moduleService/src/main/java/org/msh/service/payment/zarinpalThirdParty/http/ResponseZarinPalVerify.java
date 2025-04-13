package org.msh.service.payment.zarinpalThirdParty.http;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseZarinPalVerify {
    String code;
    String message;
    String ref_id;
    String card_pan;
    String card_hash;
    String fee_type;
    Integer fee;
}
