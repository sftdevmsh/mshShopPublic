package org.msh.service.payment.zarinpalThirdParty;

import org.msh.entity.payment.TransactionEnt;
import org.msh.service.payment.zarinpalThirdParty.http.ZarinPalRequest;
import org.msh.service.payment.zarinpalThirdParty.http.ZarinPalResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ZarinPalProvider {

    private final ZarinPalClient zarinPalClient;


    @Autowired
    public ZarinPalProvider(ZarinPalClient zarinPalClient) {
        this.zarinPalClient = zarinPalClient;
    }


    public String gotoPay(TransactionEnt trx)
    {
        ZarinPalRequest zarinPalRequest = ZarinPalRequest
                .builder()
                .merchant_id(StaticVars.merchantId)
                .callback_url(StaticVars.callBackUrl)
                .amount(trx.getAmount().intValue())
                .currency("IRT")
                .description(trx.getDescription())
                .metadata(ZarinPalRequest.Metadata
                        .builder()
                        .email(trx.getUserEnt()==null ? "" : trx.getUserEnt().getEmail())
                        .mobile(trx.getUserEnt()==null ? "" : trx.getUserEnt().getMobile())
                        .order_id(trx.getUserEnt()==null ? "" : trx.getInvoiceEnt().getId().toString())
                        .build())
                .build();
        //
        ZarinPalResponse zarinPalResponse = zarinPalClient.gotoPay(zarinPalRequest);
        if (zarinPalResponse != null) {
            trx.setAuthority(zarinPalRequest.getAuthority());
            trx.setCode(zarinPalResponse.getCode());
            trx.setResultMessage(zarinPalResponse.getMessage());
        }


        return StaticVars.ipgUrl+zarinPalResponse.getAuthority();
        //https://payment.zarinpal.com/pg/StartPay/ . $result['data']["authority"]
    }

}
