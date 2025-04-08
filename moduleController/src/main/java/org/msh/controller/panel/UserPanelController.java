package org.msh.controller.panel;

import jakarta.servlet.http.HttpServletRequest;
import org.msh.enums.MyHttpStatus;
import org.msh.wrapper.ApiResponseWrapper;
import org.msh.config.annotation.MyAutenticationAnnotation;
import org.msh.dto.user.UserDto;
import org.msh.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

@RestController
@RequestMapping("/api/panel/user/")
public class UserPanelController {

    private final UserService userService;
    private final RestClient.Builder builder;

    @Autowired
    public UserPanelController(UserService userService, RestClient.Builder builder) {
        this.userService = userService;
        this.builder = builder;
    }

    @GetMapping("{id}")
    @MyAutenticationAnnotation("user_info") //authentication
    public ApiResponseWrapper<UserDto> getById(@PathVariable("id") Long id, HttpServletRequest httpServletRequest)
    {
        return  ApiResponseWrapper
                .<UserDto>builder()
                .status(MyHttpStatus.Success)
                .msg("")
                .tdata(userService.findById(id))
                .build();
    }

}
