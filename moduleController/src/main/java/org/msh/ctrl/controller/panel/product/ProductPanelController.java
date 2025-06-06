package org.msh.ctrl.controller.panel.product;

import org.msh.ctrl.config.annotation.MyAutenticationAnnotation;
import org.msh.ctrl.controller.panel.myGenerics.MyGenericController;
import org.msh.ctrl.enums.MyHttpStatus;
import org.msh.common.exceptions.MyExc;
import org.msh.serv.service.product.ProductService;
import org.msh.serv.dto.product.ProductDto;
import org.msh.ctrl.wrapper.PanelApiResponseWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClient;

import java.util.List;

@RestController
@RequestMapping("/api/panel/product/")
public class ProductPanelController implements MyGenericController<ProductDto> {

    private final ProductService productService;
    private final RestClient.Builder builder;

    @Autowired
    public ProductPanelController(ProductService productService, RestClient.Builder builder) {
        this.productService = productService;
        this.builder = builder;
    }

//    @GetMapping("{id}")
//    @MyAutenticationAnnotation("product_info") //authentication
//    public ApiResponseWrapper<ProductDto> getById(@PathVariable("id") Long id, HttpServletRequest httpServletRequest)
//    {
//        return  ApiResponseWrapper
//                .<ProductDto>builder()
//                .status(MyHttpStatus.Success)
//                .msg("")
//                .tdata(productService.findByIdSrv(id))
//                .build();
//    }



    @MyAutenticationAnnotation("product_lst , product_inf")
    @Override
    public PanelApiResponseWrapper<ProductDto> findByIdCtrl(Long id) {
        return  PanelApiResponseWrapper
                .<ProductDto>builder()
                .status(MyHttpStatus.Success)
                .msg("")
                .tdata(productService.findByIdSrv(id))
                .build();
    }

    @MyAutenticationAnnotation("product_lst")
    @Override
    public PanelApiResponseWrapper<List<ProductDto>> findAllCtrl(
        @RequestParam(value = "page", required = false) Integer page
        , @RequestParam(value = "size", required = false) Integer size)//Integer page, Integer size)
    {
        return  PanelApiResponseWrapper
                .<List<ProductDto>>builder()
                .status(MyHttpStatus.Success)
                .msg("")
                .tdata(productService.findAllSrv(page,size).toList())
                .build();
    }

    @MyAutenticationAnnotation("product_del")
    @Override
    public PanelApiResponseWrapper<Boolean> deleteByIdCtrl(Long id) {
        return  PanelApiResponseWrapper
                .<Boolean>builder()
                .status(MyHttpStatus.Success)
                .msg("")
                .tdata(productService.deleteByIdSrv(id))
                .build();
    }

    @MyAutenticationAnnotation("product_add")
    @Override
    public PanelApiResponseWrapper<ProductDto> addCtrl(ProductDto productDto) throws MyExc {
        return  PanelApiResponseWrapper
                .<ProductDto>builder()
                .status(MyHttpStatus.Success)
                .msg("")
                .tdata(productService.addSrv(productDto))
                .build();
    }

    @MyAutenticationAnnotation("product_upd")
    @Override
    public PanelApiResponseWrapper<ProductDto> updateCtrl(ProductDto productDto) throws MyExc {
        return  PanelApiResponseWrapper
                .<ProductDto>builder()
                .status(MyHttpStatus.Success)
                .msg("")
                .tdata(productService.updateSrv(productDto))
                .build();
    }






    @MyAutenticationAnnotation("product_lst")
    @GetMapping("get/category/{cid}")
    public PanelApiResponseWrapper<List<ProductDto>> findAllByProductCategoryEnt_IdCtrl(
            @PathVariable("cid") Long cid
            , Integer page
            , Integer size) {
        return  PanelApiResponseWrapper
                .<List<ProductDto>>builder()
                .status(MyHttpStatus.Success)
                .msg("")
                .tdata(productService.findAllByProductCategoryEnt_IdSrv(cid, page,size).toList())
                .build();
    }

}
