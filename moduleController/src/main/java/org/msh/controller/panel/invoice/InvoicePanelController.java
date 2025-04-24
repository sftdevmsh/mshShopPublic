package org.msh.controller.panel.invoice;

import org.msh.config.annotation.MyAutenticationAnnotation;
import org.msh.config.filter.MyJwtFilter;
import org.msh.controller.panel.myGenerics.MyGenericController;
import org.msh.dto.invoice.InvoiceDto;
import org.msh.dto.user.UserDto;
import org.msh.entity.invoice.InvoiceEnt;
import org.msh.enums.MyHttpStatus;
import org.msh.exceptions.MyExc;
import org.msh.repositoryJpa.invoice.InvoiceRepositoryJpa;
import org.msh.service.invoice.InvoiceService;
import org.msh.wrapper.PanelApiResponseWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/panel/invoice")
public class InvoicePanelController implements MyGenericController<InvoiceDto> {
    private final InvoiceService invoiceService;
    private final InvoiceRepositoryJpa invoiceRepositoryJpa;

    @Autowired
    public InvoicePanelController(InvoiceService invoiceService, InvoiceRepositoryJpa invoiceRepositoryJpa) {
        this.invoiceService = invoiceService;
        this.invoiceRepositoryJpa = invoiceRepositoryJpa;
    }


    @MyAutenticationAnnotation("invoice_order_inf")
    @Override
    public PanelApiResponseWrapper<InvoiceDto> findByIdCtrl(@PathVariable("id") Long id) {
        return PanelApiResponseWrapper
                .<InvoiceDto>builder()
                .tdata(invoiceService.findByIdSrv(id))
                .msg("")
                .status(MyHttpStatus.Success)
                .build();
    }

    @MyAutenticationAnnotation("invoice_order_lst")
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


    @MyAutenticationAnnotation("invoice_order_del")
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
    @MyAutenticationAnnotation("invoice_order_add")
    @Override
    public PanelApiResponseWrapper<InvoiceDto> addCtrl(InvoiceDto invoiceDto) throws MyExc {
        return PanelApiResponseWrapper
                .<InvoiceDto>builder()
                .tdata(null)
                .msg("not applicable, upload instead")
                .status(MyHttpStatus.Success)
                .build();
    }

    @MyAutenticationAnnotation("invoice_order_upd")
    @Override
    public PanelApiResponseWrapper<InvoiceDto> updateCtrl(InvoiceDto invoiceDto) throws MyExc {
        return PanelApiResponseWrapper
                .<InvoiceDto>builder()
                .tdata(invoiceService.updateSrv(invoiceDto))
                .msg("")
                .status(MyHttpStatus.Success)
                .build();
    }







    @MyAutenticationAnnotation("invoice_order_inf_mine")
    @GetMapping("/get/mine/{id}")
    public PanelApiResponseWrapper<InvoiceDto> findMineCtrl(@PathVariable("id") Long id, HttpRequest request) throws MyExc {
        InvoiceDto invoiceDto = findByIdCtrl(id).getTdata();
        UserDto userDto = (UserDto) request.getAttributes().get(MyJwtFilter.Attr_CURRENT_USER);
        if(invoiceDto.getUserEnt() == null || !invoiceDto.getUserEnt().getId().equals(userDto.getId()))
            throw new MyExc("Access denied to this invoice");
        //
        return PanelApiResponseWrapper
                .<InvoiceDto>builder()
                .tdata(invoiceDto)
                .msg("")
                .status(MyHttpStatus.Success)
                .build();
    }

    @MyAutenticationAnnotation("invoice_order_lst_mine")
    @GetMapping("/get/mine/list")
    public PanelApiResponseWrapper<List<InvoiceDto>> findListMineCtrl(HttpRequest request) {
        UserDto dto = (UserDto) request.getAttributes().get(MyJwtFilter.Attr_CURRENT_USER);
        return PanelApiResponseWrapper
                .<List<InvoiceDto>>builder()
                .tdata(invoiceService.findListMineSrv(dto))
                .msg("")
                .status(MyHttpStatus.Success)
                .build();
    }

}
