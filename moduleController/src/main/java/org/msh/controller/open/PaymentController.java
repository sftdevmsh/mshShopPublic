package org.msh.controller.open;

import org.msh.dto.payment.GotoPaymentDto;
import org.msh.enums.MyHttpStatus;
import org.msh.enums.PaymentGateway;
import org.msh.exceptions.MyExc;
import org.msh.service.payment.ServicePayment;
import org.msh.wrapper.ApiResponseWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    private final ServicePayment servicePayment;

    @Autowired
    public PaymentController(ServicePayment servicePayment) {
        this.servicePayment = servicePayment;
    }

    @Transactional
    @PostMapping("/gotoPayment")
    protected ApiResponseWrapper<String> gotoPayment(@RequestBody GotoPaymentDto gotoPaymentDto)
    {
        try {
            return ApiResponseWrapper
                    .<String>builder()
                    .tdata(servicePayment.gotoPayment(gotoPaymentDto))
                    .status(MyHttpStatus.Success)
                    .msg("")
                    .build();
        } catch (MyExc e) {
            return ApiResponseWrapper
                    .<String>builder()
                    .tdata(null)
                    .status(MyHttpStatus.Failed)
                    .msg(e.getMessage())
                    .build();
        }
    }
    /*
    {
    "username": "u11",
    "password": "p11",
    "mobile": "m11",
    "email": "e11",
    "firstname": "fn11",
    "lastname": "ln11",
    "tel": "t11",
    "address": "a11",
    "postalCode": "p11",
    "paymentGateway": "ZarinPal",
    "basketItems": [
            {
                "productId": 1,
                "colorId": 1,
                "sizeId": 1,
                "quantity": 1
            }
        ]
    }
    */

    @GetMapping("/gateways")
    public ApiResponseWrapper<List<String>> getAllPaymentGateways()
    {
        return ApiResponseWrapper.<List<String>>builder()
                .msg("")
                .status(MyHttpStatus.Success)
                .tdata(servicePayment.getAllPaymentGateways())
                .build();
    }

}
