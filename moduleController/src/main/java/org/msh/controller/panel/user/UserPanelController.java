package org.msh.controller.panel.user;

import org.msh.config.annotation.MyAutenticationAnnotation;
import org.msh.config.filter.MyJwtFilter;
import org.msh.controller.panel.myGenerics.MyGenericController;
import org.msh.dto.user.ChangePassDto;
import org.msh.dto.user.ChangeProfileDto;
import org.msh.dto.user.LimitedUserDto;
import org.msh.enums.MyHttpStatus;
import org.msh.exceptions.MyExc;
import org.msh.service.user.UserService;
import org.msh.dto.user.UserDto;
import org.msh.wrapper.PanelApiResponseWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

import java.util.List;

@RestController
@RequestMapping("/api/panel/user/")
public class UserPanelController implements MyGenericController<UserDto> {

    private final UserService userService;
    private final RestClient.Builder builder;

    @Autowired
    public UserPanelController(UserService userService, RestClient.Builder builder) {
        this.userService = userService;
        this.builder = builder;
    }

//    @GetMapping("{id}")
//    @MyAutenticationAnnotation("user_info") //authentication
//    public ApiResponseWrapper<UserDto> getById(@PathVariable("id") Long id, HttpServletRequest httpServletRequest)
//    {
//        return  ApiResponseWrapper
//                .<UserDto>builder()
//                .status(MyHttpStatus.Success)
//                .msg("")
//                .tdata(userService.findById(id))
//                .build();
//    }

    @MyAutenticationAnnotation("user_inf")
    @Override
    public PanelApiResponseWrapper<UserDto> findByIdCtrl(Long id) {
        return  PanelApiResponseWrapper
                .<UserDto>builder()
                .status(MyHttpStatus.Success)
                .msg("")
                .tdata(userService.findByIdSrv(id))
                .build();
    }

    @MyAutenticationAnnotation("user_lst")
    @Override
    public PanelApiResponseWrapper<List<UserDto>> findAllCtrl(Integer page, Integer size) {
        return  PanelApiResponseWrapper
                .<List<UserDto>>builder()
                .status(MyHttpStatus.Success)
                .msg("")
                .tdata(userService.findAllSrv(page,size).toList())
                .build();
    }

    @MyAutenticationAnnotation("user_del")
    @Override
    public PanelApiResponseWrapper<Boolean> deleteByIdCtrl(Long id) {
        return  PanelApiResponseWrapper
                .<Boolean>builder()
                .status(MyHttpStatus.Success)
                .msg("")
                .tdata(userService.deleteByIdSrv(id))
                .build();
    }

    @MyAutenticationAnnotation("user_add")
    @Override
    public PanelApiResponseWrapper<UserDto> addCtrl(UserDto userDto) throws MyExc {
        return  PanelApiResponseWrapper
                .<UserDto>builder()
                .status(MyHttpStatus.Success)
                .msg("")
                .tdata(userService.addSrv(userDto))
                .build();
    }

    @MyAutenticationAnnotation("user_upd")
    @Override
    public PanelApiResponseWrapper<UserDto> updateCtrl(UserDto userDto) throws MyExc {
        return  PanelApiResponseWrapper
                .<UserDto>builder()
                .status(MyHttpStatus.Success)
                .msg("")
                .tdata(userService.updateSrv(userDto))
                .build();
    }



    @MyAutenticationAnnotation("user_upd_pass_by_admin")
    @PutMapping("/upd/pass_by_admin")
    public PanelApiResponseWrapper<UserDto> changePassByAdminCtrl(UserDto userDto) throws MyExc {
        return  PanelApiResponseWrapper
                .<UserDto>builder()
                .status(MyHttpStatus.Success)
                .msg("")
                .tdata(userService.changePassByAdminSrv(userDto))
                .build();
    }
    @MyAutenticationAnnotation("user_upd_pass_by_user")
    @PutMapping("/upd/pass_by_user")
    public PanelApiResponseWrapper<UserDto> changePassByUserCtrl(HttpRequest request, ChangePassDto cpDto) throws MyExc {
        UserDto dto = (UserDto) request.getAttributes().get(MyJwtFilter.Attr_CURRENT_USER);
        return  PanelApiResponseWrapper
                .<UserDto>builder()
                .status(MyHttpStatus.Success)
                .msg("")
                .tdata(userService.changePassByUserSrv(dto, cpDto))
                .build();
    }
    @MyAutenticationAnnotation("user_upd_profile")
    @PutMapping("/upd/profile")
    public PanelApiResponseWrapper<UserDto> changeProfileCtrl(HttpRequest request, ChangeProfileDto cpDto) throws MyExc {
        UserDto dto = (UserDto) request.getAttributes().get(MyJwtFilter.Attr_CURRENT_USER);
        return  PanelApiResponseWrapper
                .<UserDto>builder()
                .status(MyHttpStatus.Success)
                .msg("")
                .tdata(userService.changeProfileSrv(dto, cpDto))
                .build();
    }


}
