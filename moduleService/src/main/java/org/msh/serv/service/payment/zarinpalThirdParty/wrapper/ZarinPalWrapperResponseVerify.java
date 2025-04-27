package org.msh.serv.service.payment.zarinpalThirdParty.wrapper;

import lombok.*;
import org.msh.serv.service.payment.zarinpalThirdParty.http.ZarinPalResponseVerify;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ZarinPalWrapperResponseVerify {
    private ZarinPalResponseVerify data;
    private Object[] errors;
}
