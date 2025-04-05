package org.msh.controller.open.site;

import org.msh.api.enums.MyHttpStatus;
import org.msh.api.model.ApiResponse;
import org.msh.dto.site.BlogDto;
import org.msh.dto.site.NavDto;
import org.msh.dto.site.SingleBlogDto;
import org.msh.service.site.BlogService;
import org.msh.service.site.NavService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public ApiResponse<List<BlogDto>> getAll(
            @RequestParam(value = "page", required = false) Integer page
            , @RequestParam(value = "size", required = false) Integer size)
    {
        ApiResponse<List<BlogDto>> res;
        try {
            res = ApiResponse
                    .<List<BlogDto>>builder()
                    .tdata(blogService.findAllSrv(page,size))
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


    @GetMapping("/{id}")
    public ApiResponse<SingleBlogDto> getById(@PathVariable("id") Long id)
    {
        ApiResponse<SingleBlogDto> res;
        try {
            res = ApiResponse
                    .<SingleBlogDto>builder()
                    .tdata(blogService.findById(id))
                    .msg("")
                    .status(MyHttpStatus.Success)
                    .build();
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            res = ApiResponse
                    .<SingleBlogDto>builder()
                    .tdata(null)
                    .msg(e.getMessage())
                    .status(MyHttpStatus.Failed)
                    .build();
        }
        return res;
    }

    @GetMapping("/title/{title}")
    public ApiResponse<SingleBlogDto> getByTitle(@PathVariable("title") String title)
    {
        ApiResponse<SingleBlogDto> res;
        try {
            res = ApiResponse
                    .<SingleBlogDto>builder()
                    .tdata(blogService.findByTitle(title))
                    .msg("")
                    .status(MyHttpStatus.Success)
                    .build();
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            res = ApiResponse
                    .<SingleBlogDto>builder()
                    .tdata(null)
                    .msg(e.getMessage())
                    .status(MyHttpStatus.Failed)
                    .build();
        }
        return res;
    }


}
