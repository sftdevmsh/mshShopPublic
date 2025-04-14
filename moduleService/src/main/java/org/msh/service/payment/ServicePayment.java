package org.msh.service.payment;

import org.modelmapper.ModelMapper;
import org.msh.dto.payment.GotoPaymentDto;
import org.msh.entity.invoice.InvoiceEnt;
import org.msh.entity.payment.TransactionEnt;
import org.msh.entity.user.UserEnt;
import org.msh.exceptions.MyExc;
//import org.msh.repositoryJpa.payment.PaymentRepositoryJpa;
import org.msh.repositoryJpa.payment.TransactionRepositoryJpa;
import org.msh.service.invoice.InvoiceService;
import org.msh.service.payment.zarinpalThirdParty.ServiceZarinPal;
import org.msh.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ServicePayment {

//    private final PaymentRepositoryJpa paymentRepositoryJpa;
    private final ServiceZarinPal serviceZarinPal;
    //
    private final TransactionRepositoryJpa transactionRepositoryJpa;
    //
    private final UserService userService;
    private final InvoiceService invoiceService;
    //
    private final ModelMapper modelMapper;

    @Autowired
    public ServicePayment(UserService userService
            , TransactionRepositoryJpa transactionRepositoryJpa
                          //, PaymentRepositoryJpa paymentRepositoryJpa
            , ServiceZarinPal serviceZarinPal
            , InvoiceService invoiceService
            , ModelMapper modelMapper)
    {
        this.userService = userService;
        this.transactionRepositoryJpa = transactionRepositoryJpa;
//        this.paymentRepositoryJpa = paymentRepositoryJpa;
        this.serviceZarinPal = serviceZarinPal;
        this.invoiceService = invoiceService;
        this.modelMapper = modelMapper;
    }


    //Note: @Transactional in paymentController
    @Transactional
    public String gotoPayment(GotoPaymentDto gotoPaymentDto) throws MyExc {
        //todo: complete validate
        validate(gotoPaymentDto);
        //
        //CustomerEnt customerEnt = customerService.saveAndCreate(gotoPaymentDto); //done in UserService
        UserEnt userEnt = userService.saveAndCreate(gotoPaymentDto);
        InvoiceEnt invoiceEnt = invoiceService.saveAndCreate(gotoPaymentDto);
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
                res = serviceZarinPal.gotoPay(transactionEnt);
            }
            case CardToCard -> {
            }
            case MellatBank -> {
            }
            case TejaratBank -> {
            }
        }
        transactionRepositoryJpa.save(transactionEnt);
        return res;
    }

    private static void validate(GotoPaymentDto gotoPaymentDto) throws MyExc {
        if(gotoPaymentDto.getUsername()==null || gotoPaymentDto.getUsername().isEmpty())
            throw new MyExc("wrong username");
        if(gotoPaymentDto.getPaymentGateway()==null)
            throw new MyExc("wrong gateway");
    }


    public String verify(String authority, String status)
    {
        return "";
    }


}
