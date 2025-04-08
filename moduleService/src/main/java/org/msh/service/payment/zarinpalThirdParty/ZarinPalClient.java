package org.msh.service.payment.zarinpalThirdParty;

import org.msh.service.payment.zarinpalThirdParty.http.ZarinPalRequest;
import org.msh.service.payment.zarinpalThirdParty.http.ZarinPalResponse;
import org.msh.service.payment.zarinpalThirdParty.wrapper.ZarinPalResponseWrapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;


@Component
public class ZarinPalClient {


    public ZarinPalResponse gotoPay(ZarinPalRequest zarinPalRequest)
    {
        String url = StaticVars.baseUrl+"/v4/payment/request.json";
        //
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<ZarinPalRequest> heReq = new HttpEntity<>(zarinPalRequest,headers);
        //
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<ZarinPalResponseWrapper> responseWrapper = restTemplate.postForEntity(url,heReq,ZarinPalResponseWrapper.class);
        //
        return  Objects.requireNonNull(responseWrapper.getBody()).getData();//return it if not noll
    }
}
