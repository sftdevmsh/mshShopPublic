package org.msh.controller.open;

import org.msh.dto.payment.GotoPaymentDto;
import org.msh.enums.MyHttpStatus;
import org.msh.exceptions.MyExc;
import org.msh.service.payment.PaymentService;
import org.msh.wrapper.ApiResponseWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    private final PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @Transactional
    @PostMapping("/gotoPayment")
    protected ApiResponseWrapper<String> gotoPayment(@RequestBody GotoPaymentDto gotoPaymentDto)
    {
        try {
            return ApiResponseWrapper
                    .<String>builder()
                    .tdata(paymentService.gotoPayment(gotoPaymentDto))
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

}
