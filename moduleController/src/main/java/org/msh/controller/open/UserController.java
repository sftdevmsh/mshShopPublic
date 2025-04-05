package org.msh.controller.open;

import jakarta.servlet.http.HttpServletRequest;
import org.msh.api.enums.MyHttpStatus;
import org.msh.api.model.ApiResponse;
import org.msh.dto.user.LimitedUserDto;
import org.msh.dto.user.LoginDto;
import org.msh.dto.user.UserDto;
import org.msh.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user/")
public class UserController {

    UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }



    @PostMapping("login")
    public ApiResponse<LimitedUserDto> login(@RequestBody LoginDto dto)  {
        try {
            return ApiResponse
                    .<LimitedUserDto>builder()
                    .tdata(userService.login(dto))
                    .status(MyHttpStatus.Success)
                    .msg("")
                    .build();
        }
        catch (Exception e)
        {
            return ApiResponse
                    .<LimitedUserDto>builder()
                    .tdata(null)
                    .status(MyHttpStatus.Failed)
                    .msg(e.getMessage())
                    .build();
        }
    }
    @PostMapping("login2")
    public ApiResponse<UserDto> loginGetAllInfo(@RequestBody LoginDto dto)  {
        try {
            return ApiResponse
                    .<UserDto>builder()
                    .tdata(userService.loginGetAllInfo(dto))
                    .status(MyHttpStatus.Success)
                    .msg("")
                    .build();
        }
        catch (Exception e)
        {
            return ApiResponse
                    .<UserDto>builder()
                    .tdata(null)
                    .status(MyHttpStatus.Failed)
                    .msg(e.getMessage())
                    .build();
        }
    }


    @GetMapping("test/{id}")
//    @MyAutenticationAnnotation("user_info") //authentication
    public ApiResponse<UserDto> testing(@PathVariable("id") Long id, HttpServletRequest httpServletRequest)
    {
        return  ApiResponse
                .<UserDto>builder()
                .status(MyHttpStatus.Success)
                .msg("")
                .tdata(userService.findById(id))
                .build();
    }

}
