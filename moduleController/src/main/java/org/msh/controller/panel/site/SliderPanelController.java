package org.msh.controller.panel.site;

import org.msh.config.annotation.MyAutenticationAnnotation;
import org.msh.controller.panel.myGenerics.MyGenericController;
import org.msh.dto.site.SliderDto;
import org.msh.enums.MyHttpStatus;
import org.msh.exceptions.MyExc;
import org.msh.service.site.SliderService;
import org.msh.wrapper.PanelApiResponseWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/panel/slider")
public class SliderPanelController implements MyGenericController<SliderDto> {
    private final SliderService sliderService;

    @Autowired
    public SliderPanelController(SliderService sliderService) {
        this.sliderService = sliderService;
    }


    @MyAutenticationAnnotation("slider_list , slider_info")
    @Override
    public PanelApiResponseWrapper<SliderDto> findByIdCtrl(Long id) {
        return PanelApiResponseWrapper
                .<SliderDto>builder()
                .tdata(sliderService.findByIdSrv(id))
                .msg("")
                .status(MyHttpStatus.Success)
                .build();
    }

    @MyAutenticationAnnotation("slider_list")
    @Override
    public PanelApiResponseWrapper<List<SliderDto>> findAllCtrl(Integer page, Integer size) {
        {
            PanelApiResponseWrapper<List<SliderDto>> res;
            try {
                Page<SliderDto> data = sliderService.findAllSrv(page,size);
                res = PanelApiResponseWrapper
                        .<List<SliderDto>>builder()
                        .tdata(data.toList())
                        .msg("")
                        .status(MyHttpStatus.Success)
                        .totalCount((int) data.getTotalElements())
                        .totalCount(data.getTotalPages())
                        .build();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                res = PanelApiResponseWrapper
                        .<List<SliderDto>>builder()
                        .tdata(null)
                        .msg(e.getMessage())
                        .status(MyHttpStatus.Failed)
                        .build();
            }
            return res;
        }
    }


    @MyAutenticationAnnotation("slider_del")
    @Override
    public PanelApiResponseWrapper<Boolean> deleteByIdCtrl(Long id) {
        return PanelApiResponseWrapper
                .<Boolean>builder()
                .tdata(sliderService.deleteByIdSrv(id))
                .msg("")
                .status(MyHttpStatus.Success)
                .build();
    }

    @MyAutenticationAnnotation("slider_add")
    @Override
    public PanelApiResponseWrapper<SliderDto> addCtrl(SliderDto sliderDto) throws MyExc {
        return PanelApiResponseWrapper
                .<SliderDto>builder()
                .tdata(sliderService.addSrv(sliderDto))
                .msg("")
                .status(MyHttpStatus.Success)
                .build();
    }

    @MyAutenticationAnnotation("slider_edit")
    @Override
    public PanelApiResponseWrapper<SliderDto> updateCtrl(SliderDto sliderDto) throws MyExc {
        return PanelApiResponseWrapper
                .<SliderDto>builder()
                .tdata(sliderService.updateSrv(sliderDto))
                .msg("")
                .status(MyHttpStatus.Success)
                .build();
    }
}
