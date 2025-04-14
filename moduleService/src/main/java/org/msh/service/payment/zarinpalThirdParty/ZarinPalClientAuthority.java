package org.msh.service.payment.zarinpalThirdParty;

import org.msh.service.payment.zarinpalThirdParty.http.ZarinPalRequestToken;
import org.msh.service.payment.zarinpalThirdParty.http.ZarinPalResponseToken;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Random;


@Component
public class ZarinPalClientAuthority {

    @Value("${payment.zarinpal.tokenUrl}")
    private String tokenUrl;


    public ZarinPalResponseToken gotoPay(ZarinPalRequestToken zarinPalRequestToken)
    {
        /*
        String url = tokenUrl;
        //
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<ZarinPalRequestToken> heReq = new HttpEntity<>(zarinPalRequestToken,headers);
        //
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<ZarinPalWrapperResponseToken> responseWrapper = restTemplate.postForEntity(url,heReq, ZarinPalWrapperResponseToken.class);
        //
        //return  Objects.requireNonNull(responseWrapper.getBody()).getData();//not nUll
        */
        //todo: undo_mocking
        return ZarinPalResponseToken
                .builder()
                .authority("Mocking_Authority_" + new Random().nextInt(100000,999999))
                .code("100")
                .fee_type("Merchant")
                .message("Mocking...")
                .build();
    }
}
