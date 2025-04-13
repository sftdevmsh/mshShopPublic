package org.msh.controller.open;

import org.msh.enums.MyHttpStatus;
//import org.msh.service.payment.zarinpalThirdParty.StaticVars;
import org.msh.service.payment.ServicePayment;
import org.msh.wrapper.ApiResponseWrapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("")
public class HomeController {

    @Value("${payment.callBackUrl}")
    private String callBackUrl;

    private final ServicePayment servicePayment;

    public HomeController(ServicePayment servicePayment) {
        this.servicePayment = servicePayment;
    }

    //our callbackUrl used for ZarinPal gateway:
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


    //our Mock ipgUrlMock instead of ZarinPal payment page
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


}
