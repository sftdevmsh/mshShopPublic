package org.msh.service.payment.zarinpalThirdParty.wrapper;

import lombok.*;
import org.msh.service.payment.zarinpalThirdParty.http.ZarinPalResponseVerify;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ZarinPalWrapperResponseVerify {
    private ZarinPalResponseVerify data;
    private Object[] errors;
}
