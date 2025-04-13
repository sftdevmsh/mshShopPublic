package org.msh.service.payment.zarinpalThirdParty;

import org.msh.service.payment.zarinpalThirdParty.http.RequestZarinPalVerify;
import org.msh.service.payment.zarinpalThirdParty.http.ResponseZarinPalVerify;
import org.msh.service.payment.zarinpalThirdParty.wrapper.WrapperResponseZarinPalVerify;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;


@Component
public class ClientZarinPalVerify {

    //@Value("${payment.baseUrl}")
    //private String baseUrl;
    @Value("${payment.zarinpal.verifyUrl}")
    private String verifyUrl;


    public ResponseZarinPalVerify verify(RequestZarinPalVerify requestZarinPalVerify)
    {
        String url = verifyUrl;
        //
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<RequestZarinPalVerify> heReq = new HttpEntity<>(requestZarinPalVerify,headers);
        //
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<WrapperResponseZarinPalVerify> responseWrapper = restTemplate.postForEntity(url,heReq, WrapperResponseZarinPalVerify.class);
        //
        return  Objects.requireNonNull(responseWrapper.getBody()).getData();//return it if not noll
    }
}
