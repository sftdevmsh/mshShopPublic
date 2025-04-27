package org.msh.ctrl.controller.open;

import org.msh.serv.dto.user.LimitedUserDto;
import org.msh.serv.dto.user.LoginDto;
import org.msh.ctrl.enums.MyHttpStatus;
import org.msh.common.exceptions.NotFoundExc;
import org.msh.serv.service.user.UserService;
import org.msh.ctrl.wrapper.ApiResponseWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;


@RestController
@RequestMapping("/api/user/")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService, RestClient.Builder builder) {
        this.userService = userService;
    }


    @PostMapping("/login")
    public ApiResponseWrapper<LimitedUserDto> login(@RequestBody LoginDto ld) throws NotFoundExc {
        return  ApiResponseWrapper
                .<LimitedUserDto>builder()
                .status(MyHttpStatus.Success)
                .msg("")
                .tdata(userService.login(ld))
                .build();
    }
}
