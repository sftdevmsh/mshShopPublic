package org.msh.service.payment.zarinpalThirdParty.http;


import lombok.*;

import java.util.ArrayList;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ZarinPalRequest {
    String merchant_id;
    String callback_url;
    String authority;
    String description;
    Integer amount;
    String currency;
    Metadata metadata;

    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public class Metadata
    {
        String mobile;
        String email;
        String order_id;
    }
}
