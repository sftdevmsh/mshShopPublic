package org.msh.controller.panel.site;

import org.msh.config.annotation.MyAutenticationAnnotation;
import org.msh.controller.panel.myGenerics.MyGenericController;
import org.msh.enums.MyHttpStatus;
import org.msh.exceptions.MyExc;
import org.msh.dto.site.NavDto;
import org.msh.service.site.NavService;
import org.msh.wrapper.PanelApiResponseWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/panel/nav")
public class NavPanelController implements MyGenericController<NavDto> {
    //
    private final NavService navService;

    @Autowired
    public NavPanelController(NavService navService) {
        this.navService = navService;
    }



    @MyAutenticationAnnotation("nav_list , nav_info")
    @Override
    public PanelApiResponseWrapper<NavDto> findByIdCtrl(Long id) {
        return PanelApiResponseWrapper
                .<NavDto>builder()
                .tdata(navService.findByIdSrv(id))
                .msg("")
                .status(MyHttpStatus.Success)
                .build();
    }

    @MyAutenticationAnnotation("nav_list")
    @Override
    public PanelApiResponseWrapper<List<NavDto>> findAllCtrl(Integer page, Integer size) {
        {
            PanelApiResponseWrapper<List<NavDto>> res;
            try {
                Page<NavDto> data = navService.findAllSrv(page,size);
                res = PanelApiResponseWrapper
                        .<List<NavDto>>builder()
                        .tdata(data.toList())
                        .msg("")
                        .status(MyHttpStatus.Success)
                        .totalCount((int) data.getTotalElements())
                        .totalCount(data.getTotalPages())
                        .build();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                res = PanelApiResponseWrapper
                        .<List<NavDto>>builder()
                        .tdata(null)
                        .msg(e.getMessage())
                        .status(MyHttpStatus.Failed)
                        .build();
            }
            return res;
        }
    }


    @MyAutenticationAnnotation("nav_del")
    @Override
    public PanelApiResponseWrapper<Boolean> deleteByIdCtrl(Long id) {
        return PanelApiResponseWrapper
                .<Boolean>builder()
                .tdata(navService.deleteByIdSrv(id))
                .msg("")
                .status(MyHttpStatus.Success)
                .build();
    }

    @MyAutenticationAnnotation("nav_add")
    @Override
    public PanelApiResponseWrapper<NavDto> addCtrl(NavDto navDto) throws MyExc {
        return PanelApiResponseWrapper
                .<NavDto>builder()
                .tdata(navService.addSrv(navDto))
                .msg("")
                .status(MyHttpStatus.Success)
                .build();
    }

    @MyAutenticationAnnotation("nav_edit")
    @Override
    public PanelApiResponseWrapper<NavDto> updateCtrl(NavDto navDto) throws MyExc {
        return PanelApiResponseWrapper
                .<NavDto>builder()
                .tdata(navService.updateSrv(navDto))
                .msg("")
                .status(MyHttpStatus.Success)
                .build();
    }
}
