package org.msh.serv.service.payment;

import org.modelmapper.ModelMapper;
import org.msh.serv.dto.payment.GotoPaymentDto;
import org.msh.repo.entity.invoice.InvoiceEnt;
import org.msh.repo.entity.payment.TransactionEnt;
import org.msh.repo.entity.user.UserEnt;
import org.msh.repo.enums.PaymentGateway;
import org.msh.common.exceptions.MyExc;
//import org.msh.repositoryJpa.payment.PaymentRepositoryJpa;
import org.msh.repo.repositoryJpa.payment.TransactionRepositoryJpa;
import org.msh.serv.service.invoice.InvoiceService;
import org.msh.serv.service.payment.zarinpalThirdParty.ZarinPalService;
import org.msh.serv.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Service
public class ServicePayment
{
    //private final PaymentRepositoryJpa paymentRepositoryJpa;
    private final ZarinPalService zarinPalService;
    //
    private final TransactionRepositoryJpa transactionRepositoryJpa;
    //
    private final UserService userService;
    private final InvoiceService invoiceService;
    //
    private final ModelMapper modelMapper;


    @Value("${payment.zarinpal.merchantId}")
    private String merchantId;
    @Value("${payment.zarinpal.callBackUrl}")
    private String callBackUrl;
    @Value("${payment.zarinpal.toPayUrl}")
    private String toPayUrl;


    @Autowired
    public ServicePayment(UserService userService
            , TransactionRepositoryJpa transactionRepositoryJpa
                          //, PaymentRepositoryJpa paymentRepositoryJpa
            , ZarinPalService zarinPalService
            , InvoiceService invoiceService
            , ModelMapper modelMapper)
    {
        this.userService = userService;
        this.transactionRepositoryJpa = transactionRepositoryJpa;
//        this.paymentRepositoryJpa = paymentRepositoryJpa;
        this.zarinPalService = zarinPalService;
        this.invoiceService = invoiceService;
        this.modelMapper = modelMapper;
    }


    //Note: @Transactional in paymentController
    @Transactional
    public String gotoPayment(GotoPaymentDto gotoPaymentDto) throws MyExc {
        //todo: complete validate
        validate(gotoPaymentDto);
        //
        //CustomerEnt customerEnt = customerService.saveAndCreate(gotoPaymentDto); //done in UserInfSrv
        UserEnt userEnt = userService.saveAndCreate(gotoPaymentDto);
        InvoiceEnt invoiceEnt = invoiceService.saveAndCreate(gotoPaymentDto,userEnt);
        //
        TransactionEnt transactionEnt = TransactionEnt.builder().build();
        transactionEnt.setUserEnt(userEnt);//with customer inside user
        transactionEnt.setInvoiceEnt(invoiceEnt);
        transactionEnt.setAmount(invoiceEnt.getTotalAmount());//totalPrice extracted from basketItems/invoiceItems
        transactionEnt.setDescription("my custom info : " + userEnt.getId()+" - "+invoiceEnt.getId()); // not null
        //
        //todo: gateway db not needed =>done
        //GatewayEnt paymentGatewayEnt = paymentRepositoryJpa.myFindByPaymentGateway(gotoPaymentDto.getPaymentGateway()).orElseThrow();
        //transactionEnt.setGatewayEnt(paymentGatewayEnt);
        transactionEnt.setPaymentGateway(gotoPaymentDto.getPaymentGateway());
        //
        String res = "";
        switch(gotoPaymentDto.getPaymentGateway())
        {
            case ZarinPal -> {
                transactionEnt = zarinPalService.gotoPay(transactionEnt);
                if(transactionEnt.getAuthority().isEmpty())
                    throw new MyExc("transaction failed");
                transactionRepositoryJpa.save(transactionEnt);
                res = toPayUrl + "/" + transactionEnt.getAuthority();
            }
            case CardToCard -> {
            }
            case MellatBank -> {
            }
            case TejaratBank -> {
            }
        }
        return res;
    }
    //
    //
    private static void validate(GotoPaymentDto gotoPaymentDto) throws MyExc {
        if(gotoPaymentDto.getUsername()==null || gotoPaymentDto.getUsername().isEmpty())
            throw new MyExc("wrong username");
        if(gotoPaymentDto.getPaymentGateway()==null)
            throw new MyExc("wrong gateway");
    }



    public String verify(String authority, String status)
    {
        if(status == null || status.isEmpty() || status.equalsIgnoreCase("NOK"))
            return "NOK";
        if(status.equalsIgnoreCase("OK")) {
            //in previous step(gotoPayment), transaction was saved
            TransactionEnt trx = transactionRepositoryJpa.findFirstByAuthorityEqualsIgnoreCase(authority).orElseThrow();
            TransactionEnt trxVerified = zarinPalService.verify(trx);
            transactionRepositoryJpa.save(trxVerified);
            return "OK";
        }
        return "NOK";
    }


    public List<String> getAllPaymentGateways() {
        return Arrays.stream(PaymentGateway.values()).map(Enum::toString).toList();
    }
}
