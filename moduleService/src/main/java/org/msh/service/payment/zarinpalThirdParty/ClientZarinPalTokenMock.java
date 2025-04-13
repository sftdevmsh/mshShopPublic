package org.msh.service.payment.zarinpalThirdParty;

import org.msh.service.payment.zarinpalThirdParty.http.RequestZarinPalToken;
import org.msh.service.payment.zarinpalThirdParty.http.ResponseZarinPalToken;
import org.springframework.stereotype.Component;

import java.util.Random;


@Component
public class ClientZarinPalTokenMock {


    public static ResponseZarinPalToken gotoPay(RequestZarinPalToken requestZarinPalToken)
    {
        return ResponseZarinPalToken
                .builder()
                .authority("/Mocking_Authority_" + new Random().nextInt(100000,999999))
                .code("100")
                .message("Mocking...")
                .build();
    }

}
