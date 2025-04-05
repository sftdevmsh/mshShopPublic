package org.msh.controller.open.site;

import org.msh.api.enums.MyHttpStatus;
import org.msh.api.model.ApiResponse;
import org.msh.dto.site.ContentDto;
import org.msh.dto.site.NavDto;
import org.msh.service.site.ContentService;
import org.msh.service.site.NavService;
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
    public ApiResponse<List<ContentDto>> getAll(
            @RequestParam(value = "page", required = false) Integer page
            , @RequestParam(value = "size", required = false) Integer size)
    {
        ApiResponse<List<ContentDto>> res;
        try {
            res = ApiResponse
                    .<List<ContentDto>>builder()
                    .tdata(contentService.findAllSrv())
                    .msg("")
                    .status(MyHttpStatus.Success)
                    .build();
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            res = ApiResponse
                    .<List<ContentDto>>builder()
                    .tdata(null)
                    .msg(e.getMessage())
                    .status(MyHttpStatus.Failed)
                    .build();
        }
        return res;
    }


    @GetMapping("/title/{title}")
    public ApiResponse<ContentDto> getByKey(@PathVariable("title") String title)
    {
        ApiResponse<ContentDto> res;
        try {
            res = ApiResponse
                    .<ContentDto>builder()
                    .tdata(contentService.findByTitleSrv(title))
                    .msg("")
                    .status(MyHttpStatus.Success)
                    .build();
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            res = ApiResponse
                    .<ContentDto>builder()
                    .tdata(null)
                    .msg(e.getMessage())
                    .status(MyHttpStatus.Failed)
                    .build();
        }
        return res;
    }

}
