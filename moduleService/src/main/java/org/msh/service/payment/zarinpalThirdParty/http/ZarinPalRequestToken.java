package org.msh.service.payment.zarinpalThirdParty.http;


import lombok.*;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ZarinPalRequestToken {
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
    public static class Metadata
    {
        String mobile;
        String email;
        String order_id;
    }
}
