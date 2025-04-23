//package org.msh.controller.open;
//
//import jakarta.servlet.http.HttpServletRequest;
//import org.msh.enums.MyHttpStatus;
//import org.msh.wrapper.ApiResponseWrapper;
//import org.msh.dto.user.LimitedUserDto;
//import org.msh.dto.user.LoginDto;
//import org.msh.dto.user.UserDto;
//import org.msh.service.user.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/api/user")
//public class UserController {
//
//    UserService userService;
//
//    @Autowired
//    public UserController(UserService userService) {
//        this.userService = userService;
//    }
//
//
//
//    @PostMapping("/login")
//    public ApiResponseWrapper<LimitedUserDto> login(@RequestBody LoginDto dto)  {
//        try {
//            return ApiResponseWrapper
//                    .<LimitedUserDto>builder()
//                    .tdata(userService.login(dto))
//                    .status(MyHttpStatus.Success)
//                    .msg("")
//                    .build();
//        }
//        catch (Exception e)
//        {
//            return ApiResponseWrapper
//                    .<LimitedUserDto>builder()
//                    .tdata(null)
//                    .status(MyHttpStatus.Failed)
//                    .msg(e.getMessage())
//                    .build();
//        }
//    }
//
//    @PostMapping("login2")
//    public ApiResponseWrapper<UserDto> loginGetAllInfo(@RequestBody LoginDto dto)  {
//        try {
//            return ApiResponseWrapper
//                    .<UserDto>builder()
//                    .tdata(userService.loginGetAllInfo(dto))
//                    .status(MyHttpStatus.Success)
//                    .msg("")
//                    .build();
//        }
//        catch (Exception e)
//        {
//            return ApiResponseWrapper
//                    .<UserDto>builder()
//                    .tdata(null)
//                    .status(MyHttpStatus.Failed)
//                    .msg(e.getMessage())
//                    .build();
//        }
//    }
//
//
//    @GetMapping("/test/{id}")
////    @MyAutenticationAnnotation("user_info") //authentication
//    public ApiResponseWrapper<UserDto> testing(@PathVariable("id") Long id, HttpServletRequest httpServletRequest)
//    {
//        return  ApiResponseWrapper
//                .<UserDto>builder()
//                .status(MyHttpStatus.Success)
//                .msg("")
//                .tdata(userService.findByIdSrv(id))
//                .build();
//    }
//
//
//}
