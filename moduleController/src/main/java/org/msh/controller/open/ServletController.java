package org.msh.controller.open;

import jakarta.servlet.http.*;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Enumeration;

@RestController
@RequestMapping("/")
public class ServletController {

    //http://localhost:8080/cookie
    @GetMapping("cookie")
    public String cookie(@CookieValue(value = "name", defaultValue = "friend") String name){
        return "hello "+name;
    }

    //http://localhost:8080/servlet?param1=AAA
    @GetMapping("servlet")
    public void servlet(HttpServletRequest request
                        , HttpServletResponse response
                        , HttpSession sessionInp
                        ) throws IOException {

        String p1 = request.getParameter("param1");
        String address = request.getRequestURL().toString();
        String agent = request.getHeader("user-agent");
        Enumeration<String> enmr = request.getHeaderNames();
        //
        System.out.println(p1);
        System.out.println(agent);
        System.out.println(address);
        while (enmr.hasMoreElements()) {
            String key = enmr.nextElement();
            String val = request.getHeader(key);
            System.out.println(key+" : "+val);
        }


        //response.sendRedirect("http://localhost:8080/api/product/");
        response.setStatus(404);//HttpStatus.NOT_FOUND
        //
        response.setHeader("myheader" , "123");
        //
        Cookie cookie = new Cookie("myCookie" , "123");
        cookie.setMaxAge(7*24*60*60);//1  week
        response.addCookie(cookie);

        sessionInp.setAttribute("mySession", "123");
        //
        //
        HttpSession sessionReq = request.getSession(true);//create if there was no session
        sessionReq.setAttribute("mySession2", "123");
        //
        Object uid = sessionReq.getAttribute("userId");
        //
        sessionReq.invalidate();//logout

    }

}
