package org.msh.service.payment.zarinpalThirdParty.wrapper;

import lombok.*;
import org.msh.service.payment.zarinpalThirdParty.http.ZarinPalResponseToken;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ZarinPalWrapperResponseToken {
    private ZarinPalResponseToken data;
    private Object[] errors;
}
