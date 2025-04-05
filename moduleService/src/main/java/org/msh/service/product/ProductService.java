package org.msh.service.product;

import lombok.SneakyThrows;
import org.modelmapper.ModelMapper;
import org.msh.config.mapper.product.ProductMapper;
import org.msh.dto.product.ProductCategoryDto;
import org.msh.dto.product.ProductDto;
import org.msh.entity.product.ProductCategoryEnt;
import org.msh.entity.product.ProductEnt;
import org.msh.enums.ProductQueryType;
import org.msh.repositoryJpa.product.ColorRepositoryJpa;
import org.msh.repositoryJpa.product.ProductCategoryRepositoryJpa;
import org.msh.repositoryJpa.product.ProductRepositoryJpa;
import org.msh.repositoryJpa.product.SizeRepositoryJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    private final ProductRepositoryJpa productRepository;
    private final ProductCategoryRepositoryJpa productCategoryRepositoryJpa ;
    private final ColorRepositoryJpa colorRepositoryJpa;
    private final SizeRepositoryJpa sizeRepositoryJpa;
    private final ProductMapper mapper;
    private final ModelMapper modelMapper;
    private final ProductMapper productMapper;


    @Autowired
    public ProductService(ProductRepositoryJpa productRepository
            , ProductCategoryRepositoryJpa productCategoryRepositoryJpa
            , ColorRepositoryJpa colorRepositoryJpa
            , SizeRepositoryJpa sizeRepositoryJpa
            , ProductMapper productMapper
            , ModelMapper modelMapper)
    {
        this.productRepository = productRepository;
        this.productCategoryRepositoryJpa = productCategoryRepositoryJpa;
        this.colorRepositoryJpa = colorRepositoryJpa;
        this.sizeRepositoryJpa = sizeRepositoryJpa;
        this.mapper = productMapper;
        this.modelMapper = modelMapper;
        this.productMapper = productMapper;
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
        dtoDb.setImg(dto.getImg());
        dtoDb.setPrice(dto.getPrice());
        //dtoDb.setExist(dto.getExist());
        //dtoDb.setEnabled(dto.getEnabled());
        dtoDb.setColorDtos(dto.getColorDtos());
        dtoDb.setSizeDtos(dto.getSizeDtos());

        ProductEnt product = productRepository.save(mapper.map(dtoDb));
        return mapper.map(product); //return dtoDb;
    }

    public List<ProductDto> findTop(ProductQueryType queryType)
    {
        List<ProductEnt> res = new ArrayList<>();
        //
        switch (queryType) {
            case ProductQueryType.latest -> {
                res = productRepository.findTopLatest();
            }
            case ProductQueryType.popular -> {
                res = productRepository.findTopPopular();
            }
            case ProductQueryType.expensive -> {
                res = productRepository.findTopExpensive();
            }
            case ProductQueryType.cheap -> {
                res = productRepository.findTopCheap();
            }
        }
        //
        return res.stream().map(productMapper::map).toList();
    }





    //region category of product
    public List<ProductCategoryDto> findAllCategoriesSrv()
    {
        return productCategoryRepositoryJpa.findAllByEnabledIsTrueOrderByTitleAsc()
                .stream()
                //.map(mapper::map)
                .map(x->modelMapper.map(x,ProductCategoryDto.class))
                .toList();
    }
    //endregion






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
        //if(dto.getBrand() == null || dto.getImg().isEmpty())
            //throw new Exception("Error! validationModelProduct _ wrong product Brand");
    }
   //endregion

}
