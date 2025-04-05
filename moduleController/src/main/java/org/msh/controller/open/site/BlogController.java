package org.msh.controller.open.site;

import org.msh.api.enums.MyHttpStatus;
import org.msh.api.model.ApiResponse;
import org.msh.dto.site.BlogDto;
import org.msh.dto.site.NavDto;
import org.msh.service.site.BlogService;
import org.msh.service.site.NavService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/blog")
public class BlogController {
    private final BlogService blogService;

    @Autowired
    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }


    @GetMapping("")
    public ApiResponse<List<BlogDto>> getAll()
    {
        ApiResponse<List<BlogDto>> res;
        try {
            res = ApiResponse
                    .<List<BlogDto>>builder()
                    .tdata(blogService.findAllSrv())
                    .msg("")
                    .status(MyHttpStatus.Success)
                    .build();
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            res = ApiResponse
                    .<List<BlogDto>>builder()
                    .tdata(null)
                    .msg(e.getMessage())
                    .status(MyHttpStatus.Failed)
                    .build();
        }
        return res;
    }
}
