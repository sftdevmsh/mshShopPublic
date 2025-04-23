package org.msh.controller.panel.invoice;

import org.msh.config.annotation.MyAutenticationAnnotation;
import org.msh.controller.panel.myGenerics.MyGenericController;
import org.msh.dto.invoice.InvoiceDto;
import org.msh.enums.MyHttpStatus;
import org.msh.exceptions.MyExc;
import org.msh.service.invoice.InvoiceService;
import org.msh.wrapper.PanelApiResponseWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/panel/invoice")
public class InvoicePanelController implements MyGenericController<InvoiceDto> {
    private final InvoiceService invoiceService;

    @Autowired
    public InvoicePanelController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }


    @MyAutenticationAnnotation("invoice_lst , invoice_inf")
    @Override
    public PanelApiResponseWrapper<InvoiceDto> findByIdCtrl(Long id) {
        return PanelApiResponseWrapper
                .<InvoiceDto>builder()
                .tdata(invoiceService.findByIdSrv(id))
                .msg("")
                .status(MyHttpStatus.Success)
                .build();
    }

    @MyAutenticationAnnotation("invoice_lst")
    @Override
    public PanelApiResponseWrapper<List<InvoiceDto>> findAllCtrl(Integer page, Integer size) {
        {
            PanelApiResponseWrapper<List<InvoiceDto>> res;
            try {
                Page<InvoiceDto> data = invoiceService.findAllSrv(page,size);
                res = PanelApiResponseWrapper
                        .<List<InvoiceDto>>builder()
                        .tdata(data.toList())
                        .msg("")
                        .status(MyHttpStatus.Success)
                        .totalCount((int) data.getTotalElements())
                        .totalCount(data.getTotalPages())
                        .build();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                res = PanelApiResponseWrapper
                        .<List<InvoiceDto>>builder()
                        .tdata(null)
                        .msg(e.getMessage())
                        .status(MyHttpStatus.Failed)
                        .build();
            }
            return res;
        }
    }


    @MyAutenticationAnnotation("invoice_del")
    @Override
    public PanelApiResponseWrapper<Boolean> deleteByIdCtrl(Long id) {
        return PanelApiResponseWrapper
                .<Boolean>builder()
                .tdata(invoiceService.deleteByIdSrv(id))
                .msg("")
                .status(MyHttpStatus.Success)
                .build();
    }

    //todo: use upload instead
    @MyAutenticationAnnotation("invoice_add")
    @Override
    public PanelApiResponseWrapper<InvoiceDto> addCtrl(InvoiceDto invoiceDto) throws MyExc {
        return PanelApiResponseWrapper
                .<InvoiceDto>builder()
                .tdata(null)
                .msg("not applicable, upload instead")
                .status(MyHttpStatus.Success)
                .build();
    }

    @MyAutenticationAnnotation("invoice_upd")
    @Override
    public PanelApiResponseWrapper<InvoiceDto> updateCtrl(InvoiceDto invoiceDto) throws MyExc {
        return PanelApiResponseWrapper
                .<InvoiceDto>builder()
                .tdata(invoiceService.updateSrv(invoiceDto))
                .msg("")
                .status(MyHttpStatus.Success)
                .build();
    }







    @MyAutenticationAnnotation("invoice_lst , invoice_inf")
    @GetMapping("/get/user/{uid}")
    public PanelApiResponseWrapper<List<InvoiceDto>> findByUserEnt_IdCtrl(@PathVariable("uid") Long uid) {
        return PanelApiResponseWrapper
                .<List<InvoiceDto>>builder()
                .tdata(invoiceService.findAllByUserEnt_IdSrv(uid))
                .msg("")
                .status(MyHttpStatus.Success)
                .build();
    }

}
