package org.msh.service.product;

import lombok.SneakyThrows;
import org.msh.config.mapper.ProductMapper;
import org.msh.dto.product.ProductDto;
import org.msh.entity.product.ProductEnt;
import org.msh.repositoryJpa.product.ProductRepositoryJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepositoryJpa productRepository;
    private final ProductMapper mapper;

    @Autowired
    public ProductService(ProductRepositoryJpa productRepository, ProductMapper productMapper)
    {
        this.productRepository = productRepository;
        this.mapper = productMapper;
    }

    public List<ProductDto> findAllSrv()
    {
        return productRepository.findAll()
                .stream()
//                .map(x -> mapper.map(x))
                .map(mapper::map)
                .toList();
    }

    public ProductDto findByIdSrv(Long id)
    {
        validationModelId(id);
//        return productRepository.getByIdProduct(id);
        ProductEnt p = productRepository.findById(id).orElseThrow();
        ProductDto pd = p!=null ? mapper.map(p) : null;
        return pd;
    }

    public ProductDto findBySkuSrv(String sku)
    {
        return mapper
                .map(productRepository.findBySKUEqualsIgnoreCase(sku).orElseThrow());
    }


    public List<ProductDto> findByBrandLikeSrv(String brand)
    {
        List<ProductEnt> lst = productRepository.findByBrandLike(brand).orElseThrow();

        return lst.stream().map(p->mapper.map(p)).toList();
    }


    public void deleteSrv(Long id)
    {
        validationModelId(id);
//        productRepository.deleteProduct(id);
        productRepository.deleteById(id);
    }

    public ProductDto addSrv(ProductDto dto)
    {
        validationModelDto(dto);
        dto.setId(null);
        ProductEnt product = productRepository.save(mapper.map(dto));

        return mapper.map(product);
    }

    public ProductDto updateSrv(ProductDto dto) {
        validationModelId(dto.getId());
        validationModelDto(dto);

        ProductDto dtoDb = findByIdSrv(dto.getId());
        dtoDb.setSKU(dto.getSKU());
        dtoDb.setModel(dto.getModel());
        dtoDb.setBrand(dto.getBrand());
        dtoDb.setPrice(dto.getPrice());

        ProductEnt product = productRepository.save(mapper.map(dtoDb));
        return mapper.map(product); //return dtoDb;
    }








    //region private methods for validation
    @SneakyThrows
    private void validationModelId(Long id)
    {
        if(id == null || id<=0)
            throw new Exception("Error! validationModelId _ wrong id");
    }
    @SneakyThrows
    private void validationModelDto(ProductDto dto)
    {
        if(dto == null)
            throw new Exception("Error! validationModelProduct _ null product");
        if(dto.getBrand() == null || dto.getBrand().isEmpty())
            throw new Exception("Error! validationModelProduct _ wrong product Brand");
    }
   //endregion




}
