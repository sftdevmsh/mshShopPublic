package org.msh.controller.open.site;

import org.msh.api.enums.MyHttpStatus;
import org.msh.api.model.ApiResponse;
import org.msh.dto.site.NavDto;
import org.msh.dto.site.SliderDto;
import org.msh.service.site.NavService;
import org.msh.service.site.SliderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/slider")
public class SliderController {
    private final SliderService sliderService;

    @Autowired
    public SliderController(SliderService sliderService) {
        this.sliderService = sliderService;
    }


    @GetMapping("")
    public ApiResponse<List<SliderDto>> getAll()
    {
        ApiResponse<List<SliderDto>> res;
        try {
            res = ApiResponse
                    .<List<SliderDto>>builder()
                    .tdata(sliderService.findAllSrv())
                    .msg("")
                    .status(MyHttpStatus.Success)
                    .build();
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            res = ApiResponse
                    .<List<SliderDto>>builder()
                    .tdata(null)
                    .msg(e.getMessage())
                    .status(MyHttpStatus.Failed)
                    .build();
        }
        return res;
    }
}
