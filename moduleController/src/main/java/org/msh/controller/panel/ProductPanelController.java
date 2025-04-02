package org.msh.controller.panel;

import jakarta.servlet.http.HttpServletRequest;
import org.msh.api.enums.MyHttpStatus;
import org.msh.api.model.ApiResponse;
import org.msh.config.annotation.MyAutenticationAnnotation;
import org.msh.dto.product.ProductDto;
import org.msh.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

@RestController
@RequestMapping("/api/panel/product/")
public class ProductPanelController {

    private final ProductService productService;
    private final RestClient.Builder builder;

    @Autowired
    public ProductPanelController(ProductService productService, RestClient.Builder builder) {
        this.productService = productService;
        this.builder = builder;
    }

    @GetMapping("{id}")
    @MyAutenticationAnnotation("product_info") //authentication
    public ApiResponse<ProductDto> getById(@PathVariable("id") Long id, HttpServletRequest httpServletRequest)
    {
        return  ApiResponse
                .<ProductDto>builder()
                .status(MyHttpStatus.Success)
                .msg("")
                .tdata(productService.findByIdSrv(id))
                .build();
    }

}
