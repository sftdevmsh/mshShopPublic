package org.msh.config.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.msh.dto.user.UserDto;
import org.msh.service.user.UserService;
import org.msh.util.MyJwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class MyJwtFilter implements Filter {

    private final MyJwtUtil myJwtUtil;
    private final UserService userService;
    public static final String Attr_CURRENT_USER = "Attr_CURRENT_USER" ;


    @Autowired
    public MyJwtFilter(MyJwtUtil myJwtUtil, UserService userService) {
        this.myJwtUtil = myJwtUtil;
        this.userService = userService;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest)request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        //
        /*
        //
        //Note:
        //below code is not needed here(done in MyFilterConfig)
        //as we assign URLs+precedence of filters using {MyFilterConfig + MyJwtFilter}
        //
        String url = httpServletRequest.getRequestURL().toString();
        System.out.println();
        if(!url.contains("/api/panel")) {
            chain.doFilter(request, response);
            return;
        }
        */
        //
        //region Authentication
        String strToken = extractTokenFromHeader(httpServletRequest);
        if(!strToken.isEmpty() && myJwtUtil.validateJwtToken(strToken)) {
            String username = myJwtUtil.getUsernameFromJwtToken(strToken);
            UserDto userDto = userService.findByUsername(username);
            request.setAttribute(Attr_CURRENT_USER, userDto);
            chain.doFilter(request, response);
        }
        else {
            httpServletResponse.getWriter().write("Access Denied !!!");
        }
        //endregion
        return;
    }


    private static String extractTokenFromHeader(HttpServletRequest httpServletRequest) {
        String strToken = "";
        String strAuthorization = httpServletRequest.getHeader("Authorization");
        //
        String prefix = "Bearer ";
        if(strAuthorization!=null && strAuthorization.startsWith(prefix))
                strToken = strAuthorization.substring(prefix.length());
        //
        return strToken;
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
