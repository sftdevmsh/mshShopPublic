package org.msh.service.payment;

import lombok.SneakyThrows;
import org.modelmapper.ModelMapper;
import org.msh.dto.product.ColorDto;
import org.msh.entity.product.ColorEnt;
import org.msh.repositoryJpa.payment.PaymentRepositoryJpa;
import org.msh.repositoryJpa.product.ColorRepositoryJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService {

    private final PaymentRepositoryJpa paymentRepositoryJpa;
    private final ModelMapper modelMapper;

    @Autowired
    public PaymentService(PaymentRepositoryJpa paymentRepositoryJpa, ModelMapper modelMapper)
    {
        this.paymentRepositoryJpa = paymentRepositoryJpa;
        this.modelMapper = modelMapper;
    }
}
