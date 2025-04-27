package org.msh.ctrl.controller.panel.site;

import org.msh.ctrl.config.annotation.MyAutenticationAnnotation;
import org.msh.ctrl.controller.panel.myGenerics.MyGenericController;
import org.msh.serv.dto.site.BlogDto;
import org.msh.ctrl.enums.MyHttpStatus;
import org.msh.common.exceptions.MyExc;
import org.msh.serv.service.site.BlogService;
import org.msh.ctrl.wrapper.PanelApiResponseWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/panel/blog")
public class BlogPanelController implements MyGenericController<BlogDto> {


    private final BlogService blogService;

    @Autowired
    public BlogPanelController(BlogService blogService) {
        this.blogService = blogService;
    }




    @CacheEvict(cacheNames = "myApiCache5min", key = "'blog_' + #id")
    @MyAutenticationAnnotation("blog_inf")
    @Override
    public PanelApiResponseWrapper<BlogDto> findByIdCtrl(Long id) {
        return PanelApiResponseWrapper
                .<BlogDto>builder()
                .tdata(blogService.findByIdSrv(id))
                .msg("")
                .status(MyHttpStatus.Success)
                .build();
    }


    @CacheEvict(cacheNames = "myApiCache5min", key = "'blog_all_'+#page+'_'+#size")
    @MyAutenticationAnnotation("blog_lst")
    @Override
    public PanelApiResponseWrapper<List<BlogDto>> findAllCtrl(Integer page, Integer size) {
        {
            PanelApiResponseWrapper<List<BlogDto>> res;
            try {
                Page<BlogDto> data = blogService.findAllSrv(page,size);
                res = PanelApiResponseWrapper
                        .<List<BlogDto>>builder()
                        .tdata(data.toList())
                        .msg("")
                        .status(MyHttpStatus.Success)
                        .totalCount((int) data.getTotalElements())
                        .totalCount(data.getTotalPages())
                        .build();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                res = PanelApiResponseWrapper
                        .<List<BlogDto>>builder()
                        .tdata(null)
                        .msg(e.getMessage())
                        .status(MyHttpStatus.Failed)
                        .build();
            }
            return res;
        }
    }


    @MyAutenticationAnnotation("blog_del")
    @Override
    public PanelApiResponseWrapper<Boolean> deleteByIdCtrl(Long id) {
        return PanelApiResponseWrapper
                .<Boolean>builder()
                .tdata(blogService.deleteByIdSrv(id))
                .msg("")
                .status(MyHttpStatus.Success)
                .build();
    }

    @MyAutenticationAnnotation("blog_add")
    @Override
    public PanelApiResponseWrapper<BlogDto> addCtrl(BlogDto blogDto) throws MyExc {
        return PanelApiResponseWrapper
                .<BlogDto>builder()
                .tdata(blogService.addSrv(blogDto))
                .msg("")
                .status(MyHttpStatus.Success)
                .build();
    }

    @MyAutenticationAnnotation("blog_upd")
    @Override
    public PanelApiResponseWrapper<BlogDto> updateCtrl(BlogDto blogDto) throws MyExc {
        return PanelApiResponseWrapper
                .<BlogDto>builder()
                .tdata(blogService.updateSrv(blogDto))
                .msg("")
                .status(MyHttpStatus.Success)
                .build();
    }

    
    
    @GetMapping("/title/{title}")
    public PanelApiResponseWrapper<BlogDto> getByTitle(@PathVariable("title") String title)
    {
        PanelApiResponseWrapper<BlogDto> res;
        try {
            res = PanelApiResponseWrapper
                    .<BlogDto>builder()
                    .tdata(blogService.findByTitle(title))
                    .msg("")
                    .status(MyHttpStatus.Success)
                    .build();
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            res = PanelApiResponseWrapper
                    .<BlogDto>builder()
                    .tdata(null)
                    .msg(e.getMessage())
                    .status(MyHttpStatus.Failed)
                    .build();
        }
        return res;
    }


}
