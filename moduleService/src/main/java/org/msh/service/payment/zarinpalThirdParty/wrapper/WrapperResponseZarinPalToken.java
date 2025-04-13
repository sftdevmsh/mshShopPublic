package org.msh.service.payment.zarinpalThirdParty.wrapper;

import lombok.*;
import org.msh.service.payment.zarinpalThirdParty.http.ResponseZarinPalToken;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WrapperResponseZarinPalToken {
    private ResponseZarinPalToken data;
    private Object[] errors;
}
