package org.msh.controller.open.site;

import org.msh.api.enums.MyHttpStatus;
import org.msh.api.model.ApiResponse;
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
    public ApiResponse<List<NavDto>> getAll()
    {
        ApiResponse<List<NavDto>> res;
        try {
            res = ApiResponse
                    .<List<NavDto>>builder()
                    .tdata(navService.findAllSrv())
                    .msg("")
                    .status(MyHttpStatus.Success)
                    .build();
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            res = ApiResponse
                    .<List<NavDto>>builder()
                    .tdata(null)
                    .msg(e.getMessage())
                    .status(MyHttpStatus.Failed)
                    .build();
        }
        return res;
    }
}
