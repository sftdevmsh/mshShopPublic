package org.msh.service.payment.zarinpalThirdParty.wrapper;

import lombok.*;
import org.msh.service.payment.zarinpalThirdParty.http.ZarinPalResponse;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ZarinPalResponseWrapper {
    private ZarinPalResponse data;
    private Object[] errors;
}
