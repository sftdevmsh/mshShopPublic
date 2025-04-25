package org.msh.controller.open;

import org.msh.dto.user.LimitedUserDto;
import org.msh.dto.user.LoginDto;
import org.msh.dto.user.UserDto;
import org.msh.enums.MyHttpStatus;
import org.msh.exceptions.NotFoundExc;
import org.msh.service.user.UserService;
import org.msh.wrapper.ApiResponseWrapper;
import org.msh.wrapper.PanelApiResponseWrapper;
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
