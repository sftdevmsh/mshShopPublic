package org.msh.ctrl.controller.panel.product;

import org.msh.ctrl.config.annotation.MyAutenticationAnnotation;
import org.msh.ctrl.controller.panel.myGenerics.MyGenericController;
import org.msh.serv.dto.product.ProductCategoryDto;
import org.msh.ctrl.enums.MyHttpStatus;
import org.msh.common.exceptions.MyExc;
import org.msh.serv.service.product.ProductCategoryService;
import org.msh.ctrl.wrapper.PanelApiResponseWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

import java.util.List;

@RestController
@RequestMapping("/api/panel/productCategory/")
public class ProductCategoryPanelController 
        implements MyGenericController<ProductCategoryDto> {

    private final ProductCategoryService productCategoryService;
    private final RestClient.Builder builder;

    @Autowired
    public ProductCategoryPanelController(ProductCategoryService productCategoryService, RestClient.Builder builder) {
        this.productCategoryService = productCategoryService;
        this.builder = builder;
    }

//    @GetMapping("{id}")
//    @MyAutenticationAnnotation("category_info") //authentication
//    public ApiResponseWrapper<ProductCategoryDto> getById(@PathVariable("id") Long id, HttpServletRequest httpServletRequest)
//    {
//        return  ApiResponseWrapper
//                .<ProductCategoryDto>builder()
//                .status(MyHttpStatus.Success)
//                .msg("")
//                .tdata(productCategoryService.findByIdSrv(id))
//                .build();
//    }



    @MyAutenticationAnnotation("category_lst , category_inf")
    @Override
    public PanelApiResponseWrapper<ProductCategoryDto> findByIdCtrl(Long id) {
        return  PanelApiResponseWrapper
                .<ProductCategoryDto>builder()
                .status(MyHttpStatus.Success)
                .msg("")
                .tdata(productCategoryService.findByIdSrv(id))
                .build();
    }

    @MyAutenticationAnnotation("category_lst")
    @Override
    public PanelApiResponseWrapper<List<ProductCategoryDto>> findAllCtrl(Integer page, Integer size) {
        return  PanelApiResponseWrapper
                .<List<ProductCategoryDto>>builder()
                .status(MyHttpStatus.Success)
                .msg("")
                .tdata(productCategoryService.findAllSrv(page,size).toList())
                .build();
    }
    //
    @MyAutenticationAnnotation("category_lst")
    @GetMapping("/get/enabled")
    public PanelApiResponseWrapper<List<ProductCategoryDto>> findAllEnablesCtrl(Integer page, Integer size) {
        return  PanelApiResponseWrapper
                .<List<ProductCategoryDto>>builder()
                .status(MyHttpStatus.Success)
                .msg("")
                .tdata(productCategoryService.findAllEnabledSrv(page,size).toList())
                .build();
    }

    @MyAutenticationAnnotation("category_del")
    @Override
    public PanelApiResponseWrapper<Boolean> deleteByIdCtrl(Long id) {
        return  PanelApiResponseWrapper
                .<Boolean>builder()
                .status(MyHttpStatus.Success)
                .msg("")
                .tdata(productCategoryService.deleteByIdSrv(id))
                .build();
    }

    @MyAutenticationAnnotation("category_add")
    @Override
    public PanelApiResponseWrapper<ProductCategoryDto> addCtrl(ProductCategoryDto productCategoryDto) throws MyExc {
        return  PanelApiResponseWrapper
                .<ProductCategoryDto>builder()
                .status(MyHttpStatus.Success)
                .msg("")
                .tdata(productCategoryService.addSrv(productCategoryDto))
                .build();
    }

    @MyAutenticationAnnotation("category_upd")
    @Override
    public PanelApiResponseWrapper<ProductCategoryDto> updateCtrl(ProductCategoryDto productCategoryDto) throws MyExc {
        return  PanelApiResponseWrapper
                .<ProductCategoryDto>builder()
                .status(MyHttpStatus.Success)
                .msg("")
                .tdata(productCategoryService.updateSrv(productCategoryDto))
                .build();
    }
}
