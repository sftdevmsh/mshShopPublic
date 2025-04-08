package org.msh.service.payment;

import org.modelmapper.ModelMapper;
import org.msh.dto.payment.GotoPaymentDto;
import org.msh.entity.invoice.InvoiceEnt;
import org.msh.entity.payment.GatewayEnt;
import org.msh.entity.payment.TransactionEnt;
import org.msh.entity.user.UserEnt;
import org.msh.exceptions.MyExc;
import org.msh.repositoryJpa.payment.PaymentRepositoryJpa;
import org.msh.repositoryJpa.payment.TransactionRepositoryJpa;
import org.msh.service.invoice.InvoiceService;
import org.msh.service.payment.zarinpalThirdParty.ZarinPalProvider;
import org.msh.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    private final PaymentRepositoryJpa paymentRepositoryJpa;
    private final ZarinPalProvider zarinPalProvider;
    //
    private final TransactionRepositoryJpa transactionRepositoryJpa;
    //
    private final UserService userService;
    private final InvoiceService invoiceService;
    //
    private final ModelMapper modelMapper;

    @Autowired
    public PaymentService(UserService userService, TransactionRepositoryJpa transactionRepositoryJpa, PaymentRepositoryJpa paymentRepositoryJpa, ZarinPalProvider zarinPalProvider, InvoiceService invoiceService, ModelMapper modelMapper)
    {
        this.userService = userService;
        this.transactionRepositoryJpa = transactionRepositoryJpa;
        this.paymentRepositoryJpa = paymentRepositoryJpa;
        this.zarinPalProvider = zarinPalProvider;
        this.invoiceService = invoiceService;
        this.modelMapper = modelMapper;
    }


    //Note: @Transactional in paymentController
    public String gotoPayment(GotoPaymentDto gotoPaymentDto) throws MyExc {
        //todo: complete validate
        validate(gotoPaymentDto);
        //
        UserEnt userEnt = userService.saveAndCreate(gotoPaymentDto);
        InvoiceEnt invoiceEnt = invoiceService.saveAndCreate(gotoPaymentDto);
        //
        TransactionEnt transactionEnt = TransactionEnt.builder().build();
        transactionEnt.setUserEnt(userEnt);//customer
        transactionEnt.setInvoiceEnt(invoiceEnt);
        transactionEnt.setAmount(invoiceEnt.getTotalAmount());
        //
        GatewayEnt paymentGatewayEnt = paymentRepositoryJpa
                .myFindByPaymentGateway(gotoPaymentDto.getPaymentGateway()).orElseThrow();
        transactionEnt.setGatewayEnt(paymentGatewayEnt);
        //
        String res = "";
        switch(gotoPaymentDto.getPaymentGateway())
        {
            case ZarinPal -> {
                res = zarinPalProvider.gotoPay(transactionEnt);
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
}
