package org.msh.serv.service.payment.zarinpalThirdParty;

import org.msh.repo.entity.payment.TransactionEnt;
import org.msh.common.exceptions.MyExc;
import org.msh.serv.service.payment.zarinpalThirdParty.http.ZarinPalRequestToken;
import org.msh.serv.service.payment.zarinpalThirdParty.http.ZarinPalRequestVerify;
import org.msh.serv.service.payment.zarinpalThirdParty.http.ZarinPalResponseToken;
import org.msh.serv.service.payment.zarinpalThirdParty.http.ZarinPalResponseVerify;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
public class ZarinPalService {

    private final ZarinPalClientAuthority zarinPalClientAuthority;
    private final ZarinPalClientVerify zarinPalClientVerify;

    @Value("${payment.zarinpal.merchantId}")
    private String merchantId;
    @Value("${payment.zarinpal.callBackUrl}")
    private String callBackUrl;
    @Value("${payment.zarinpal.toPayUrl}")
    private String toPayUrl;


    @Autowired
    public ZarinPalService(ZarinPalClientAuthority zarinPalClientAuthority
            , ZarinPalClientVerify zarinPalClientVerify
    ) {
        this.zarinPalClientAuthority = zarinPalClientAuthority;
        this.zarinPalClientVerify = zarinPalClientVerify;
    }


    public TransactionEnt gotoPay(TransactionEnt trx) throws MyExc {
        ZarinPalRequestToken zarinPalRequestToken = ZarinPalRequestToken
                .builder()
                .merchant_id(merchantId)
                .callback_url(callBackUrl)
                //
                .amount(trx.getAmount().intValue())
                .currency("IRT")
                .description(trx.getDescription())
                .metadata(ZarinPalRequestToken.Metadata
                        .builder()
                        .email(trx.getUserEnt()==null ? "" : trx.getUserEnt().getEmail())
                        .mobile(trx.getUserEnt()==null ? "" : trx.getUserEnt().getMobile())
                        .order_id(trx.getUserEnt()==null ? "" : trx.getInvoiceEnt().getId().toString())
                        .build())
                .build();
        //
        ZarinPalResponseToken zarinPalResponseToken = zarinPalClientAuthority.gotoPay(zarinPalRequestToken);
        //
        //if (zarinPalResponse == null) {
        //    //todo: handle the error, redirecting user to some error page
        //    throw new MyExc("Must Not be Null : zarinPalResponse.getAuthority()");
        //}
        //

        assert zarinPalResponseToken != null;
        //if(zarinPalResponseToken != null) {
            trx.setAuthority(zarinPalResponseToken.getAuthority());
            trx.setCode(zarinPalResponseToken.getCode());
            trx.setResultMessage(zarinPalResponseToken.getMessage());
        //}
        return trx;
        //todo: undo_mocking
    }


    public TransactionEnt verify(TransactionEnt transactionEnt)
    {
        ZarinPalRequestVerify requestVerify = ZarinPalRequestVerify
                .builder()
                .authority(transactionEnt.getAuthority())
                .amount(transactionEnt.getAmount())
                .merchant_id(merchantId)
                .build();
        //
        ZarinPalResponseVerify responseVerify = zarinPalClientVerify.verify(requestVerify);
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
