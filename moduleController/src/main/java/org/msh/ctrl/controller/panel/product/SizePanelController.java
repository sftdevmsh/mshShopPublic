package org.msh.ctrl.controller.panel.product;

import org.msh.ctrl.config.annotation.MyAutenticationAnnotation;
import org.msh.ctrl.controller.panel.myGenerics.MyGenericController;
import org.msh.serv.dto.product.SizeDto;
import org.msh.ctrl.enums.MyHttpStatus;
import org.msh.common.exceptions.MyExc;
import org.msh.serv.service.product.SizeService;
import org.msh.ctrl.wrapper.PanelApiResponseWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

import java.util.List;

@RestController
@RequestMapping("/api/panel/size/")
public class SizePanelController 
        implements MyGenericController<SizeDto> {

    private final SizeService sizeService;
    private final RestClient.Builder builder;

    @Autowired
    public SizePanelController(SizeService sizeService, RestClient.Builder builder) {
        this.sizeService = sizeService;
        this.builder = builder;
    }

//    @GetMapping("{id}")
//    @MyAutenticationAnnotation("size_info") //authentication
//    public ApiResponseWrapper<SizeDto> getById(@PathVariable("id") Long id, HttpServletRequest httpServletRequest)
//    {
//        return  ApiResponseWrapper
//                .<SizeDto>builder()
//                .status(MyHttpStatus.Success)
//                .msg("")
//                .tdata(sizeService.findByIdSrv(id))
//                .build();
//    }



    @MyAutenticationAnnotation("size_lst , size_inf")
    @Override
    public PanelApiResponseWrapper<SizeDto> findByIdCtrl(Long id) {
        return  PanelApiResponseWrapper
                .<SizeDto>builder()
                .status(MyHttpStatus.Success)
                .msg("")
                .tdata(sizeService.findByIdSrv(id))
                .build();
    }

    @MyAutenticationAnnotation("size_lst")
    @Override
    public PanelApiResponseWrapper<List<SizeDto>> findAllCtrl(Integer page, Integer size) {
        return  PanelApiResponseWrapper
                .<List<SizeDto>>builder()
                .status(MyHttpStatus.Success)
                .msg("")
                .tdata(sizeService.findAllSrv(page,size).toList())
                .build();
    }

    @MyAutenticationAnnotation("size_del")
    @Override
    public PanelApiResponseWrapper<Boolean> deleteByIdCtrl(Long id) {
        return  PanelApiResponseWrapper
                .<Boolean>builder()
                .status(MyHttpStatus.Success)
                .msg("")
                .tdata(sizeService.deleteByIdSrv(id))
                .build();
    }

    @MyAutenticationAnnotation("size_add")
    @Override
    public PanelApiResponseWrapper<SizeDto> addCtrl(SizeDto sizeDto) throws MyExc {
        return  PanelApiResponseWrapper
                .<SizeDto>builder()
                .status(MyHttpStatus.Success)
                .msg("")
                .tdata(sizeService.addSrv(sizeDto))
                .build();
    }

    @MyAutenticationAnnotation("size_upd")
    @Override
    public PanelApiResponseWrapper<SizeDto> updateCtrl(SizeDto sizeDto) throws MyExc {
        return  PanelApiResponseWrapper
                .<SizeDto>builder()
                .status(MyHttpStatus.Success)
                .msg("")
                .tdata(sizeService.updateSrv(sizeDto))
                .build();
    }
}
