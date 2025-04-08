package org.msh.controller.open.site;

import org.msh.enums.MyHttpStatus;
import org.msh.wrapper.ApiResponseWrapper;
import org.msh.dto.site.ContentDto;
import org.msh.service.site.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/content")
public class ContentController {
    private final ContentService contentService;

    @Autowired
    public ContentController(ContentService contentService) {
        this.contentService = contentService;
    }


    @GetMapping("")
    public ApiResponseWrapper<List<ContentDto>> getAll(
            @RequestParam(value = "page", required = false) Integer page
            , @RequestParam(value = "size", required = false) Integer size)
    {
        ApiResponseWrapper<List<ContentDto>> res;
        try {
            res = ApiResponseWrapper
                    .<List<ContentDto>>builder()
                    .tdata(contentService.findAllSrv())
                    .msg("")
                    .status(MyHttpStatus.Success)
                    .build();
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            res = ApiResponseWrapper
                    .<List<ContentDto>>builder()
                    .tdata(null)
                    .msg(e.getMessage())
                    .status(MyHttpStatus.Failed)
                    .build();
        }
        return res;
    }


    @GetMapping("/title/{title}")
    public ApiResponseWrapper<ContentDto> getByKey(@PathVariable("title") String title)
    {
        ApiResponseWrapper<ContentDto> res;
        try {
            res = ApiResponseWrapper
                    .<ContentDto>builder()
                    .tdata(contentService.findByTitleSrv(title))
                    .msg("")
                    .status(MyHttpStatus.Success)
                    .build();
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            res = ApiResponseWrapper
                    .<ContentDto>builder()
                    .tdata(null)
                    .msg(e.getMessage())
                    .status(MyHttpStatus.Failed)
                    .build();
        }
        return res;
    }

}
