package org.msh.ctrl.controller.panel.site;

import org.msh.ctrl.config.annotation.MyAutenticationAnnotation;
import org.msh.ctrl.controller.panel.myGenerics.MyGenericController;
import org.msh.serv.dto.site.ContentDto;
import org.msh.ctrl.enums.MyHttpStatus;
import org.msh.common.exceptions.MyExc;
import org.msh.serv.service.site.ContentService;
import org.msh.ctrl.wrapper.PanelApiResponseWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/panel/content")
public class ContentPanelController implements MyGenericController<ContentDto> {


    private final ContentService contentService;

    @Autowired
    public ContentPanelController(ContentService contentService) {
        this.contentService = contentService;
    }



    @MyAutenticationAnnotation("content_lst , content_inf")
    @Override
    public PanelApiResponseWrapper<ContentDto> findByIdCtrl(Long id) {
        return PanelApiResponseWrapper
                .<ContentDto>builder()
                .tdata(contentService.findByIdSrv(id))
                .msg("")
                .status(MyHttpStatus.Success)
                .build();
    }


    @MyAutenticationAnnotation("content_lst")
    @Override
    public PanelApiResponseWrapper<List<ContentDto>> findAllCtrl(Integer page, Integer size) {
        {
            PanelApiResponseWrapper<List<ContentDto>> res;
            try {
                Page<ContentDto> data = contentService.findAllSrv(page,size);
                res = PanelApiResponseWrapper
                        .<List<ContentDto>>builder()
                        .tdata(data.toList())
                        .msg("")
                        .status(MyHttpStatus.Success)
                        .totalCount((int) data.getTotalElements())
                        .totalCount(data.getTotalPages())
                        .build();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                res = PanelApiResponseWrapper
                        .<List<ContentDto>>builder()
                        .tdata(null)
                        .msg(e.getMessage())
                        .status(MyHttpStatus.Failed)
                        .build();
            }
            return res;
        }
    }


    @MyAutenticationAnnotation("content_del")
    @Override
    public PanelApiResponseWrapper<Boolean> deleteByIdCtrl(Long id) {
        return PanelApiResponseWrapper
                .<Boolean>builder()
                .tdata(contentService.deleteByIdSrv(id))
                .msg("")
                .status(MyHttpStatus.Success)
                .build();
    }

    @MyAutenticationAnnotation("content_add")
    @Override
    public PanelApiResponseWrapper<ContentDto> addCtrl(ContentDto contentDto) throws MyExc {
        return PanelApiResponseWrapper
                .<ContentDto>builder()
                .tdata(contentService.addSrv(contentDto))
                .msg("")
                .status(MyHttpStatus.Success)
                .build();
    }

    @MyAutenticationAnnotation("content_upd")
    @Override
    public PanelApiResponseWrapper<ContentDto> updateCtrl(ContentDto contentDto) throws MyExc {
        return PanelApiResponseWrapper
                .<ContentDto>builder()
                .tdata(contentService.updateSrv(contentDto))
                .msg("")
                .status(MyHttpStatus.Success)
                .build();
    }
}
