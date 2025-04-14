//package org.msh.service.payment.zarinpalThirdParty;
//
//import org.msh.service.payment.zarinpalThirdParty.http.ZarinPalRequestToken;
//import org.msh.service.payment.zarinpalThirdParty.http.ZarinPalResponseToken;
//import org.springframework.stereotype.Component;
//
//import java.util.Random;
//
//
//@Component
//public class ClientZarinPalTokenMock {
//
//
//    public static ZarinPalResponseToken gotoPay(ZarinPalRequestToken requestZarinPalToken)
//    {
//        return ZarinPalResponseToken
//                .builder()
//                .authority("/Mocking_Authority_" + new Random().nextInt(100000,999999))
//                .code("100")
//                .message("Mocking...")
//                .build();
//    }
//
//}
