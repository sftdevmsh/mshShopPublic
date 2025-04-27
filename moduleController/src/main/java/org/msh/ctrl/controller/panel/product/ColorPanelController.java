package org.msh.ctrl.controller.panel.product;

import org.msh.ctrl.config.annotation.MyAutenticationAnnotation;
import org.msh.ctrl.controller.panel.myGenerics.MyGenericController;
import org.msh.serv.dto.product.ColorDto;
import org.msh.ctrl.enums.MyHttpStatus;
import org.msh.common.exceptions.MyExc;
import org.msh.serv.service.product.ColorService;
import org.msh.ctrl.wrapper.PanelApiResponseWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

import java.util.List;

@RestController
@RequestMapping("/api/panel/color/")
public class ColorPanelController 
        implements MyGenericController<ColorDto> {

    private final ColorService colorService;
    private final RestClient.Builder builder;

    @Autowired
    public ColorPanelController(ColorService colorService, RestClient.Builder builder) {
        this.colorService = colorService;
        this.builder = builder;
    }

//    @GetMapping("{id}")
//    @MyAutenticationAnnotation("color_info") //authentication
//    public ApiResponseWrapper<ColorDto> getById(@PathVariable("id") Long id, HttpServletRequest httpServletRequest)
//    {
//        return  ApiResponseWrapper
//                .<ColorDto>builder()
//                .status(MyHttpStatus.Success)
//                .msg("")
//                .tdata(colorService.findByIdSrv(id))
//                .build();
//    }



    @MyAutenticationAnnotation("color_lst , color_inf")
    @Override
    public PanelApiResponseWrapper<ColorDto> findByIdCtrl(Long id) {
        return  PanelApiResponseWrapper
                .<ColorDto>builder()
                .status(MyHttpStatus.Success)
                .msg("")
                .tdata(colorService.findByIdSrv(id))
                .build();
    }

    @MyAutenticationAnnotation("color_lst")
    @Override
    public PanelApiResponseWrapper<List<ColorDto>> findAllCtrl(Integer page, Integer size) {
        return  PanelApiResponseWrapper
                .<List<ColorDto>>builder()
                .status(MyHttpStatus.Success)
                .msg("")
                .tdata(colorService.findAllSrv(page,size).toList())
                .build();
    }

    @MyAutenticationAnnotation("color_del")
    @Override
    public PanelApiResponseWrapper<Boolean> deleteByIdCtrl(Long id) {
        return  PanelApiResponseWrapper
                .<Boolean>builder()
                .status(MyHttpStatus.Success)
                .msg("")
                .tdata(colorService.deleteByIdSrv(id))
                .build();
    }

    @MyAutenticationAnnotation("color_add")
    @Override
    public PanelApiResponseWrapper<ColorDto> addCtrl(ColorDto colorDto) throws MyExc {
        return  PanelApiResponseWrapper
                .<ColorDto>builder()
                .status(MyHttpStatus.Success)
                .msg("")
                .tdata(colorService.addSrv(colorDto))
                .build();
    }

    @MyAutenticationAnnotation("color_upd")
    @Override
    public PanelApiResponseWrapper<ColorDto> updateCtrl(ColorDto colorDto) throws MyExc {
        return  PanelApiResponseWrapper
                .<ColorDto>builder()
                .status(MyHttpStatus.Success)
                .msg("")
                .tdata(colorService.updateSrv(colorDto))
                .build();
    }
}
