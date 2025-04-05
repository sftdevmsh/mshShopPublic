package org.msh.service.order;

import org.modelmapper.ModelMapper;
import org.msh.repositoryJpa.order.InvoiceItemRepositoryJpa;
import org.msh.repositoryJpa.order.InvoiceRepositoryJpa;
import org.msh.repositoryJpa.payment.PaymentRepositoryJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InvoiceService {

    private final InvoiceRepositoryJpa invoiceRepositoryJpa;
    private final InvoiceItemRepositoryJpa invoiceItemRepositoryJpa;

    private final ModelMapper modelMapper;

    @Autowired
    public InvoiceService(InvoiceRepositoryJpa invoiceRepositoryJpa
            , InvoiceItemRepositoryJpa invoiceItemRepositoryJpa
            , ModelMapper modelMapper)
    {
        this.invoiceRepositoryJpa = invoiceRepositoryJpa;
        this.invoiceItemRepositoryJpa = invoiceItemRepositoryJpa;
        this.modelMapper = modelMapper;
    }
}
