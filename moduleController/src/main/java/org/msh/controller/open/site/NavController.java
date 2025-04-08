package org.msh.controller.open.site;

import org.msh.enums.MyHttpStatus;
import org.msh.wrapper.ApiResponseWrapper;
import org.msh.dto.site.NavDto;
import org.msh.service.site.NavService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/nav")
public class NavController {
    private final NavService navService;

    @Autowired
    public NavController(NavService navService) {
        this.navService = navService;
    }


    @GetMapping("")
    public ApiResponseWrapper<List<NavDto>> getAll()
    {
        ApiResponseWrapper<List<NavDto>> res;
        try {
            res = ApiResponseWrapper
                    .<List<NavDto>>builder()
                    .tdata(navService.findAllSrv())
                    .msg("")
                    .status(MyHttpStatus.Success)
                    .build();
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            res = ApiResponseWrapper
                    .<List<NavDto>>builder()
                    .tdata(null)
                    .msg(e.getMessage())
                    .status(MyHttpStatus.Failed)
                    .build();
        }
        return res;
    }
}
