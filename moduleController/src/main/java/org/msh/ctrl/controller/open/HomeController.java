package org.msh.ctrl.controller.open;

import org.msh.ctrl.enums.MyHttpStatus;
//import org.msh.service.payment.zarinpalThirdParty.StaticVars;
import org.msh.serv.service.payment.ServicePayment;
import org.msh.ctrl.wrapper.ApiResponseWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("")
public class HomeController {

    @Value("${payment.zarinpal.callBackUrl}")
    private String callBackUrl;

    private final ServicePayment servicePayment;

    @Autowired
    public HomeController(ServicePayment servicePayment) {
        this.servicePayment = servicePayment;
    }

    @GetMapping("/pg/StartPay/{Authority}")
    public ApiResponseWrapper<String> StartPay(@PathVariable String Authority)
    {
        return ApiResponseWrapper
                .<String>builder()
                .status(MyHttpStatus.Success)
                .tdata(callBackUrl + "?Authority=" + Authority + "&Status=OK")
                .msg("going from payment page to callbackUrl")
                .build();
    }


    //our callbackUrl used after payment from ZarinPal gateway:
    @GetMapping("/verify")
    public ApiResponseWrapper<String> verify(@RequestParam String Authority, @RequestParam String Status)
    {
        return ApiResponseWrapper
                .<String>builder()
                .status(MyHttpStatus.Success)
                .tdata(servicePayment.verify(Authority,Status))
                .msg(Status)
                .build();
    }

}
