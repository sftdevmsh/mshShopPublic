//package org.msh.service.payment.zarinpalThirdParty;
//
//import org.msh.service.payment.zarinpalThirdParty.http.ZarinPalRequestVerify;
//import org.msh.service.payment.zarinpalThirdParty.http.ZarinPalResponseVerify;
//import org.springframework.stereotype.Component;
//import java.util.Random;
//
//
//@Component
//public class ClientZarinPalVerifyMock {
//
//    public static ZarinPalResponseVerify verify(ZarinPalRequestVerify requestZarinPalVerify)
//    {
//        return ZarinPalResponseVerify
//                .builder()
//                .code("100")
//                .message("verifiying...")
//                .card_hash("1EBE3EBEBE35C7EC0F8D6EE4F2F859107A87822CA179BC9528767EA7B5489B69")
//                .card_pan("502229******5995")
//                .ref_id("201")
//                .fee_type("Merchant")
//                .fee(0)
//                .build();
//    }
//
//}
