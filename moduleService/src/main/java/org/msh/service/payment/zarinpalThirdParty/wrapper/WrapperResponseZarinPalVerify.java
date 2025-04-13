package org.msh.service.payment.zarinpalThirdParty.wrapper;

import lombok.*;
import org.msh.service.payment.zarinpalThirdParty.http.ResponseZarinPalVerify;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WrapperResponseZarinPalVerify {
    private ResponseZarinPalVerify data;
    private Object[] errors;
}
