package org.msh.service.payment;

import jakarta.persistence.Table;
import lombok.SneakyThrows;
import org.hibernate.type.descriptor.sql.internal.Scale6IntervalSecondDdlType;
import org.modelmapper.ModelMapper;
import org.msh.dto.payment.GotoPaymentDto;
import org.msh.dto.product.ColorDto;
import org.msh.entity.payment.TransactionEnt;
import org.msh.entity.product.ColorEnt;
import org.msh.repositoryJpa.payment.PaymentRepositoryJpa;
import org.msh.repositoryJpa.payment.TransactionRepositoryJpa;
import org.msh.repositoryJpa.product.ColorRepositoryJpa;
import org.msh.service.payment.zarinpalThirdParty.ZarinPalProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService {

    private final TransactionRepositoryJpa transactionRepositoryJpa;
    private final PaymentRepositoryJpa paymentRepositoryJpa;
    private final ZarinPalProvider zarinPalProvider;
    //
    private final ModelMapper modelMapper;

    @Autowired
    public PaymentService(TransactionRepositoryJpa transactionRepositoryJpa, PaymentRepositoryJpa paymentRepositoryJpa, ZarinPalProvider zarinPalProvider, ModelMapper modelMapper)
    {
        this.transactionRepositoryJpa = transactionRepositoryJpa;
        this.paymentRepositoryJpa = paymentRepositoryJpa;
        this.zarinPalProvider = zarinPalProvider;
        this.modelMapper = modelMapper;
    }

    public String gotoPayment(GotoPaymentDto gotoPaymentDto) {
        TransactionEnt transactionEnt = TransactionEnt.builder().build();
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
}
