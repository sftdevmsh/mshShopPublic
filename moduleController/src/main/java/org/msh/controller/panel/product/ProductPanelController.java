package org.msh.controller.panel.product;

import org.msh.config.annotation.MyAutenticationAnnotation;
import org.msh.controller.panel.myGenerics.MyGenericController;
import org.msh.enums.MyHttpStatus;
import org.msh.exceptions.MyExc;
import org.msh.service.product.ProductService;
import org.msh.dto.product.ProductDto;
import org.msh.wrapper.PanelApiResponseWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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



    @MyAutenticationAnnotation("product_list , product_info")
    @Override
    public PanelApiResponseWrapper<ProductDto> findByIdCtrl(Long id) {
        return  PanelApiResponseWrapper
                .<ProductDto>builder()
                .status(MyHttpStatus.Success)
                .msg("")
                .tdata(productService.findByIdSrv(id))
                .build();
    }

    @MyAutenticationAnnotation("product_list")
    @Override
    public PanelApiResponseWrapper<List<ProductDto>> findAllCtrl(Integer page, Integer size) {
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

    @MyAutenticationAnnotation("product_edit")
    @Override
    public PanelApiResponseWrapper<ProductDto> updateCtrl(ProductDto productDto) throws MyExc {
        return  PanelApiResponseWrapper
                .<ProductDto>builder()
                .status(MyHttpStatus.Success)
                .msg("")
                .tdata(productService.updateSrv(productDto))
                .build();
    }






    @MyAutenticationAnnotation("product_list")
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
