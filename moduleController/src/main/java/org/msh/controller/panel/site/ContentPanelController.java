package org.msh.controller.panel.site;

import org.msh.config.annotation.MyAutenticationAnnotation;
import org.msh.controller.panel.myGenerics.MyGenericController;
import org.msh.dto.site.ContentDto;
import org.msh.enums.MyHttpStatus;
import org.msh.exceptions.MyExc;
import org.msh.service.site.ContentService;
import org.msh.wrapper.PanelApiResponseWrapper;
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



    @MyAutenticationAnnotation("content_list , content_info")
    @Override
    public PanelApiResponseWrapper<ContentDto> findByIdCtrl(Long id) {
        return PanelApiResponseWrapper
                .<ContentDto>builder()
                .tdata(contentService.findByIdSrv(id))
                .msg("")
                .status(MyHttpStatus.Success)
                .build();
    }


    @MyAutenticationAnnotation("content_list")
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

    @MyAutenticationAnnotation("content_edit")
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
