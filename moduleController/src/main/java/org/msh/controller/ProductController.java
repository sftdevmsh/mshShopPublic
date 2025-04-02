package org.msh.controller;

import org.msh.api.model.ApiResponse;
import org.msh.api.enums.MyHttpStatus;
import org.msh.dto.product.ProductDto;
import org.msh.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    //public ResponseEntity<ApiResponse<List<Product>>> getAllCtrl() to ge deeper to set HttpStatus ...
    //http://localhost:8080/api/product/
    //@RequestMapping(value = "all",method = RequestMethod.GET)
    @GetMapping("")
    public ApiResponse<List<ProductDto>> getAllCtrl()
    {
        List<ProductDto> lst = productService.findAllSrv();
        return ApiResponse
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

    //http://localhost:8080/api/product/1?page=1
    //Note: to use the same path "" we use @PathVariable
    @GetMapping("{id}")
    public ApiResponse<ProductDto> getByIdCtrl(@PathVariable(value = "id") Long id
                                ,@RequestParam(value = "page", required = false) Integer pageNum)
    {
        System.out.println("pageNum : "+pageNum);

        return ApiResponse
                .<ProductDto>builder()
                .tdata(productService.findByIdSrv(id))
                .status(MyHttpStatus.Success)
                .msg("")
                .build();
    }

    //http://localhost:8080/api/product/sku/1?page=1
    //Note: to use the same path "" we use @PathVariable
    @GetMapping("sku/{sku}")
    public ApiResponse<ProductDto> findByIdCtrl(@PathVariable(value = "sku") String sku
            ,@RequestParam(value = "page", required = false) Integer pageNum)
    {
        System.out.println("pageNum : "+pageNum);

        return ApiResponse
                .<ProductDto>builder()
                .tdata(productService.findBySkuSrv(sku))
                .status(MyHttpStatus.Success)
                .msg("")
                .build();
    }

    //http://localhost:8080/api/product/sku/1?page=1
    //Note: to use the same path "" we use @PathVariable
    @GetMapping("brand/{brand}")
    public ApiResponse<List<ProductDto>> findByBrandLikeCtrl(@PathVariable(value = "brand") String brand
            ,@RequestParam(value = "page", required = false) Integer pageNum)
    {
        System.out.println("pageNum : "+pageNum);

        return ApiResponse
                .<List<ProductDto>>builder()
                .tdata(productService.findByBrandLikeSrv(brand))
                .status(MyHttpStatus.Success)
                .msg("")
                .build();
    }


    @PostMapping("")
    public ApiResponse<ProductDto> addCtrl(@RequestBody ProductDto dto)
    {
        ApiResponse<ProductDto> apiRes;
        try {
            productService.addSrv(dto);
            apiRes = ApiResponse
                    .<ProductDto>builder()
                    .tdata(null)
                    .status(MyHttpStatus.Success)
                    .msg("")
                    .build();
        }
        catch (Exception e)
        {
            apiRes = ApiResponse
                    .<ProductDto>builder()
                    .tdata(null)
                    .status(MyHttpStatus.Failed)
                    .msg(e.getMessage())
                    .build();
        }
        return apiRes;
    }

}
