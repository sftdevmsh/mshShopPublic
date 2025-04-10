package org.msh.controller.open;

import org.msh.enums.MyHttpStatus;
import org.msh.wrapper.ApiResponseWrapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
public class HomeController {


    public HomeController() {
    }

    @GetMapping("/verify")
    public ApiResponseWrapper<String> verify(@RequestParam String authority, @RequestParam String status)
    {
        return ApiResponseWrapper
                .<String>builder()
                .status(MyHttpStatus.Success)
                .tdata(authority)
                .msg(status)
                .build();
    }
}
