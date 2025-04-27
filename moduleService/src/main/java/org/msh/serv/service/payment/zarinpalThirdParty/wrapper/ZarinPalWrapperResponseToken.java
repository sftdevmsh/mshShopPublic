package org.msh.serv.service.payment.zarinpalThirdParty.wrapper;

import lombok.*;
import org.msh.serv.service.payment.zarinpalThirdParty.http.ZarinPalResponseToken;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ZarinPalWrapperResponseToken {
    private ZarinPalResponseToken data;
    private Object[] errors;
}
