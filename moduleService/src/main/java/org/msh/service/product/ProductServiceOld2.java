//package org.msh.service;
//
//import lombok.SneakyThrows;
//import org.modelmapper.ModelMapper;
//import org.msh.dto.product.ProductDto;
//import org.msh.entity.manyToMany.Product;
//import org.msh.repositoryJpa.product.ProductRepositoryJpa;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class ProductServiceOld2 {
//
//    private final ProductRepositoryJpa productRepository;
//    private final ModelMapper modelMapper;
//
//    @Autowired
//    public ProductServiceOld2(ProductRepositoryJpa productRepository, ModelMapper modelMapper)
//    {
//        this.productRepository = productRepository;
//        this.modelMapper = modelMapper;
//    }
//
//    public List<ProductDto> getAllSrv()
//    {
//        return productRepository.findAll()
//                .stream()
//                .map(x -> modelMapper.map(x,ProductDto.class))
//                .toList();
//    }
//
//    public ProductDto getByIdSrv(Long id)
//    {
//        validationModelId(id);
////        return productRepository.getByIdProduct(id);
//        Product p = productRepository.findById(id).orElseThrow();
//        return modelMapper.map(p , ProductDto.class);
//    }
//
//    public ProductDto getBySkuSrv(String sku)
//    {
//        return modelMapper
//                .map(productRepository.findBySKUEqualsIgnoreCase(sku).orElseThrow()
//                    ,ProductDto.class);
//    }
//
//
//    public List<Product> getByBrandLikeSrv(String brand)
//    {
//        return productRepository.findByBrandLike(brand).orElseThrow();
//    }
//
//
//    public void deleteSrv(Long id)
//    {
//        validationModelId(id);
////        productRepository.deleteProduct(id);
//        productRepository.deleteById(id);
//    }
//
//    public ProductDto addSrvProduct(ProductDto dto)
//    {
//        validationModelDto(dto);
//        dto.setId(null);
//        Product product = productRepository.save(modelMapper.map(dto,Product.class));
//
//        return modelMapper.map(product,ProductDto.class);
//    }
//
//    public ProductDto updateSrvProduct(ProductDto dto) {
//        validationModelId(dto.getId());
//        validationModelDto(dto);
//
//        ProductDto dtoDb = getByIdSrv(dto.getId());
//        dtoDb.setSKU(dto.getSKU());
//        dtoDb.setModel(dto.getModel());
//        dtoDb.setBrand(dto.getBrand());
//        dtoDb.setPrice(dto.getPrice());
//
//        Product product = productRepository.save(modelMapper.map(dtoDb , Product.class));
//        return modelMapper.map(product, ProductDto.class); //return dtoDb;
//    }
//
//
//
//
//
//
//
//
//    //region private methods for validation
//    @SneakyThrows
//    private void validationModelId(Long id)
//    {
//        if(id == null || id<=0)
//            throw new Exception("Error! validationModelId _ wrong id");
//    }
//    @SneakyThrows
//    private void validationModelDto(ProductDto dto)
//    {
//        if(dto == null)
//            throw new Exception("Error! validationModelProduct _ null product");
//        if(dto.getBrand() == null || dto.getBrand().isEmpty())
//            throw new Exception("Error! validationModelProduct _ wrong product Brand");
//    }
//   //endregion
//
//
//
//
//}
