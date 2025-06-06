package org.msh.ctrl.controller.open;

import org.msh.serv.service.product.ProductService;
import org.msh.ctrl.wrapper.ApiResponseWrapper;
import org.msh.ctrl.enums.MyHttpStatus;
import org.msh.serv.dto.product.ProductDto;
import org.msh.serv.enums.ProductQueryType;
import org.msh.repo.repositoryJpa.product.ProductCategoryRepositoryJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    private final ProductService productService;
    //private final ProductCategoryRepositoryJpa productCategoryRepositoryJpa;

    @Autowired
    public ProductController(ProductService productService, ProductCategoryRepositoryJpa productCategoryRepositoryJpa) {
        this.productService = productService;
        //this.productCategoryRepositoryJpa = productCategoryRepositoryJpa;
    }

    //public ResponseEntity<ApiResponse<List<Product>>> getAllCtrl() to ge deeper to set HttpStatus ...
    //http://localhost:8080/api/product/
    //@RequestMapping(value = "all",method = RequestMethod.GET)
    @GetMapping("")
    public ApiResponseWrapper<List<ProductDto>> getAllCtrl()
    {
        List<ProductDto> lst = productService.findAllSrv();
        return ApiResponseWrapper
                .<List<ProductDto>>builder()
                .tdata(lst)
                .status(MyHttpStatus.Success)
                .msg("")
                .build();
    }

    @GetMapping("/top/{type}")
    public ApiResponseWrapper<List<ProductDto>> getAllCtrl(@PathVariable("type") ProductQueryType type)
    {
        List<ProductDto> lst = productService.findTop(type);
        return ApiResponseWrapper
                .<List<ProductDto>>builder()
                .tdata(lst)
                .status(MyHttpStatus.Success)
                .msg("")
                .build();
    }



    //region 2 ways to code get
//    //http://localhost:8080/api/product/1
//    //Note: to use the same path "" we use @PathVariable
//    @GetMapping("{id}")
//    public Product getByIdCtrlWay1(@PathVariable(value = "id") Long id)
//    {
//        return productService.getByIdSrvProduct(id);
//    }
//    //http://localhost:8080/api/product/one?key=1
//    //Alternatively we can use another path
//    @GetMapping("one")
//    public Product getByIdCtrlWay2(@RequestParam(value = "key") Long id)
//    {
//        return productService.getByIdSrvProduct(id);
//    }
    //endregion

    //http://localhost:8080/api/product/sku/1?page=1
    //Note: to use the same path "" we use @PathVariable
    @GetMapping("brand/{brand}")
    public ApiResponseWrapper<List<ProductDto>> findByBrandLikeCtrl(
            @PathVariable(value = "brand") String brand
            , @RequestParam(value = "page", required = false) Integer page
            , @RequestParam(value = "size", required = false) Integer size)
    {
        System.out.println("pageNum : "+page);

        return ApiResponseWrapper
                .<List<ProductDto>>builder()
                .tdata(productService.findByBrandLikeSrv(brand))
                .status(MyHttpStatus.Success)
                .msg("")
                .build();
    }


}
