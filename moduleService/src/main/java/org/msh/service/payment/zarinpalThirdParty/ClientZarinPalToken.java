package org.msh.service.payment.zarinpalThirdParty;

import org.msh.service.payment.zarinpalThirdParty.http.RequestZarinPalToken;
import org.msh.service.payment.zarinpalThirdParty.http.ResponseZarinPalToken;
import org.msh.service.payment.zarinpalThirdParty.wrapper.WrapperResponseZarinPalToken;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;


@Component
public class ClientZarinPalToken {

    //@Value("${payment.baseUrl}")
    //private String baseUrl;
    @Value("${payment.zarinpal.tokenUrl}")
    private String tokenUrl;


    public ResponseZarinPalToken gotoPay(RequestZarinPalToken requestZarinPalToken)
    {
        //String url = baseUrl+"/v4/payment/request.json";
        String url = tokenUrl;
        //
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<RequestZarinPalToken> heReq = new HttpEntity<>(requestZarinPalToken,headers);
        //
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<WrapperResponseZarinPalToken> responseWrapper = restTemplate.postForEntity(url,heReq, WrapperResponseZarinPalToken.class);
        //
        return  Objects.requireNonNull(responseWrapper.getBody()).getData();//return it if not noll
    }
}
