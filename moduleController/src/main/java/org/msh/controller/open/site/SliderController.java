package org.msh.controller.open.site;

import org.msh.enums.MyHttpStatus;
import org.msh.wrapper.ApiResponseWrapper;
import org.msh.dto.site.SliderDto;
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
    public ApiResponseWrapper<List<SliderDto>> getAll()
    {
        ApiResponseWrapper<List<SliderDto>> res;
        try {
            res = ApiResponseWrapper
                    .<List<SliderDto>>builder()
                    .tdata(sliderService.findAllSrv())
                    .msg("")
                    .status(MyHttpStatus.Success)
                    .build();
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            res = ApiResponseWrapper
                    .<List<SliderDto>>builder()
                    .tdata(null)
                    .msg(e.getMessage())
                    .status(MyHttpStatus.Failed)
                    .build();
        }
        return res;
    }
}
