package org.msh.service.payment.zarinpalThirdParty;

import org.msh.entity.payment.TransactionEnt;
import org.msh.exceptions.MyExc;
import org.msh.service.payment.zarinpalThirdParty.http.RequestZarinPalToken;
import org.msh.service.payment.zarinpalThirdParty.http.RequestZarinPalVerify;
import org.msh.service.payment.zarinpalThirdParty.http.ResponseZarinPalToken;
import org.msh.service.payment.zarinpalThirdParty.http.ResponseZarinPalVerify;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
public class ServiceZarinPal {

    private final ClientZarinPalToken clientZarinPalToken;
    private final ClientZarinPalVerify clientZarinPalVerify;

    @Value("${payment.merchantId}")
    private String merchantId;
    @Value("${payment.callBackUrl}")
    private String callBackUrl;
    @Value("${payment.toPayUrl}")
    private String toPayUrl;
    @Value("${payment.toPayUrlMock}")
    private String toPayUrlMock;



    @Autowired
    public ServiceZarinPal(ClientZarinPalToken clientZarinPalToken, ClientZarinPalVerify clientZarinPalVerify) {
        this.clientZarinPalToken = clientZarinPalToken;
        this.clientZarinPalVerify = clientZarinPalVerify;
    }


    public String gotoPay(TransactionEnt trx) throws MyExc {
        RequestZarinPalToken requestZarinPalToken = RequestZarinPalToken
                .builder()
                .merchant_id(merchantId)
                .callback_url(callBackUrl)
                //
                .amount(trx.getAmount().intValue())
                .currency("IRT")
                .description(trx.getDescription())
                .metadata(RequestZarinPalToken.Metadata
                        .builder()
                        .email(trx.getUserEnt()==null ? "" : trx.getUserEnt().getEmail())
                        .mobile(trx.getUserEnt()==null ? "" : trx.getUserEnt().getMobile())
                        .order_id(trx.getUserEnt()==null ? "" : trx.getInvoiceEnt().getId().toString())
                        .build())
                .build();
        //
        //todo: undo_Mocking
        //ZarinPalResponse zarinPalResponse = zarinPalClient.gotoPay(zarinPalRequest);
        ResponseZarinPalToken responseZarinPalToken = ClientZarinPalTokenMock.gotoPay(requestZarinPalToken);
        //
        //if (zarinPalResponse == null) {
        //    //todo: handle the error, redirecting user to some error page
        //    throw new MyExc("Must Not be Null : zarinPalResponse.getAuthority()");
        //}
        //
        if (responseZarinPalToken != null) {
            trx.setAuthority(requestZarinPalToken.getAuthority());
            trx.setCode(responseZarinPalToken.getCode());
            trx.setResultMessage(responseZarinPalToken.getMessage());
        }

        assert responseZarinPalToken != null;

        //todo: undo_Mocking
        return toPayUrlMock+ responseZarinPalToken.getAuthority();
        //return toPayUrl+zarinPalResponse.getAuthority();
        //https://payment.zarinpal.com/pg/StartPay/ . $result['data']["authority"]
        //returns the url of payment page,
        //so that the customer can enter their payment information(including bank card number, ...) and pay.
        //they will be redirected to callbackUrl page if payment was successful
    }


    public TransactionEnt verify(TransactionEnt transactionEnt)
    {
        RequestZarinPalVerify requestVerify = RequestZarinPalVerify
                .builder()
                .authority(transactionEnt.getAuthority())
                .amount(transactionEnt.getAmount())
                .merchant_id(merchantId)
                .build();

        ResponseZarinPalVerify responseVerify = clientZarinPalVerify.verify(requestVerify);
        //
        if(responseVerify != null)
        {
            transactionEnt.setCardHash(responseVerify.getCard_hash());
            transactionEnt.setCardPan(responseVerify.getCard_pan());
            transactionEnt.setVerifyCode(responseVerify.getCode());
            transactionEnt.setVerifyResultMessage(responseVerify.getMessage());
            transactionEnt.setRefId(responseVerify.getRef_id());
        }
        //
        return transactionEnt;
    }

}
