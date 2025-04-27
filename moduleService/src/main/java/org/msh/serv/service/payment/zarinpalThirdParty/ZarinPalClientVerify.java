package org.msh.serv.service.payment.zarinpalThirdParty;

import org.msh.serv.service.payment.zarinpalThirdParty.http.ZarinPalRequestVerify;
import org.msh.serv.service.payment.zarinpalThirdParty.http.ZarinPalResponseVerify;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public class ZarinPalClientVerify {

    //@Value("${payment.baseUrl}")
    //private String baseUrl;
    @Value("${payment.zarinpal.verifyUrl}")
    private String verifyUrl;


    public ZarinPalResponseVerify verify(ZarinPalRequestVerify zarinPalRequestVerify)
    {
        /*
        String url = verifyUrl;
        //
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<ZarinPalRequestVerify> heReq = new HttpEntity<>(zarinPalRequestVerify,headers);
        //
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<ZarinPalWrapperResponseVerify> responseWrapper = restTemplate.postForEntity(url,heReq, ZarinPalWrapperResponseVerify.class);
        //
        return  Objects.requireNonNull(responseWrapper.getBody()).getData();//return it if not noll
        */
        //todo: undo_mocking
        return ZarinPalResponseVerify
                .builder()
                .code("100")
                .message("verifiying...")
                .card_hash("1EBE3EBEBE35C7EC0F8D6EE4F2F859107A87822CA179BC9528767EA7B5489B69")
                .card_pan("502229******5995")
                .ref_id("201")
                .fee_type("Merchant")
                .fee(0)
                .build();
    }
}
