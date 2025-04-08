package org.msh.controller.open.site;

import org.msh.enums.MyHttpStatus;
import org.msh.wrapper.ApiResponseWrapper;
import org.msh.dto.site.BlogDto;
import org.msh.dto.site.SingleBlogDto;
import org.msh.service.site.BlogService;
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
    public ApiResponseWrapper<List<BlogDto>> getAll(
            @RequestParam(value = "page", required = false) Integer page
            , @RequestParam(value = "size", required = false) Integer size)
    {
        ApiResponseWrapper<List<BlogDto>> res;
        try {
            res = ApiResponseWrapper
                    .<List<BlogDto>>builder()
                    .tdata(blogService.findAllSrv(page,size))
                    .msg("")
                    .status(MyHttpStatus.Success)
                    .build();
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            res = ApiResponseWrapper
                    .<List<BlogDto>>builder()
                    .tdata(null)
                    .msg(e.getMessage())
                    .status(MyHttpStatus.Failed)
                    .build();
        }
        return res;
    }


    @GetMapping("/{id}")
    public ApiResponseWrapper<SingleBlogDto> getById(@PathVariable("id") Long id)
    {
        ApiResponseWrapper<SingleBlogDto> res;
        try {
            res = ApiResponseWrapper
                    .<SingleBlogDto>builder()
                    .tdata(blogService.findById(id))
                    .msg("")
                    .status(MyHttpStatus.Success)
                    .build();
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            res = ApiResponseWrapper
                    .<SingleBlogDto>builder()
                    .tdata(null)
                    .msg(e.getMessage())
                    .status(MyHttpStatus.Failed)
                    .build();
        }
        return res;
    }

    @GetMapping("/title/{title}")
    public ApiResponseWrapper<SingleBlogDto> getByTitle(@PathVariable("title") String title)
    {
        ApiResponseWrapper<SingleBlogDto> res;
        try {
            res = ApiResponseWrapper
                    .<SingleBlogDto>builder()
                    .tdata(blogService.findByTitle(title))
                    .msg("")
                    .status(MyHttpStatus.Success)
                    .build();
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            res = ApiResponseWrapper
                    .<SingleBlogDto>builder()
                    .tdata(null)
                    .msg(e.getMessage())
                    .status(MyHttpStatus.Failed)
                    .build();
        }
        return res;
    }


}
