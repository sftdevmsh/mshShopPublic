package org.msh.ctrl.config.aspect;


import jakarta.servlet.http.HttpServletRequest;
import lombok.SneakyThrows;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.msh.ctrl.enums.MyHttpStatus;
import org.msh.ctrl.config.annotation.MyAutenticationAnnotation;
import org.msh.ctrl.config.filter.MyJwtFilter;
import org.msh.serv.dto.user.PermissionDto;
import org.msh.serv.dto.user.UserDto;
import org.msh.ctrl.wrapper.PanelApiResponseWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Aspect
public class MyAuthenticationAspect {
    private final HttpServletRequest httpServletRequest;

    @Autowired
    public MyAuthenticationAspect(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }


    @SneakyThrows
    @Around("@annotation(myAutenticationAnnotation)")
    public Object checkPermission(ProceedingJoinPoint proceedingJoinPoint, MyAutenticationAnnotation myAutenticationAnnotation) {
        UserDto dto = (UserDto) httpServletRequest.getAttribute(MyJwtFilter.Attr_CURRENT_USER);
        if(dto == null)
        {
            return PanelApiResponseWrapper
                    .<UserDto>builder()
                    .status(MyHttpStatus.AccessDenied)
                    .msg("Access Denied !!! Please Login.")
                    .tdata(null)
                    .build();
        }

        List<String> lstPermissions = dto.getRoleDtos().stream()
                .flatMap(r ->
                        r.getPermissionDtos()
                                .stream()
                                .map(PermissionDto::getName))
                .toList();
        //
        System.out.println("list of permissions");
        for(String p : lstPermissions)
            System.out.println(p);
        //
        String strPermission = myAutenticationAnnotation.value();
        if(!lstPermissions.contains(strPermission)) {
            return PanelApiResponseWrapper
                    .<UserDto>builder()
                    .status(MyHttpStatus.AccessDenied)
                    .msg("Access Denied !!! No Permission.")
                    .tdata(null)
                    .build();
        }
        return proceedingJoinPoint.proceed();
    }
}
