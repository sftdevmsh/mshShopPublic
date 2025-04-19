package org.msh.service.product;

import lombok.SneakyThrows;
import org.modelmapper.ModelMapper;
import org.msh.config.mapper.product.ProductMapper;
import org.msh.dto.product.ProductCategoryDto;
import org.msh.dto.product.ProductDto;
import org.msh.entity.product.ProductEnt;
import org.msh.enums.ProductQueryType;
import org.msh.repositoryJpa.product.ColorRepositoryJpa;
import org.msh.repositoryJpa.product.ProductCategoryRepositoryJpa;
import org.msh.repositoryJpa.product.ProductRepositoryJpa;
import org.msh.repositoryJpa.product.SizeRepositoryJpa;
import org.msh.service.generics.MyInfGnrSrv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import static java.util.Arrays.stream;

@Service
public class ProductService implements MyInfGnrSrv<ProductDto> {

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


    @Override
    public List<ProductDto> findAllSrv()
    {
        return productRepository.findAll()
                .stream()
//                .map(x -> mapper.map(x))
                .map(mapper::map)
                .toList();
    }

    @Override
    public Page<ProductDto> findAllSrv(Integer page, Integer size) {
        return productRepository
                .findAll(Pageable.ofSize(size).withPage(page))
                //.stream()
                .map(mapper::map);
                //.toList();
    }

    @Override
    public ProductDto findByIdSrv(Long id)
    {
        validationModelId(id);
//        return productRepository.getByIdProduct(id);
        ProductEnt ent = productRepository.findById(id).orElseThrow();
        ProductDto dto = (ent == null) ? null : mapper.map(ent) ;
        return dto;
    }



    public List<ProductDto> findByBrandLikeSrv(String brand)
    {
        List<ProductEnt> lst = productRepository.findByBrandLike(brand).orElseThrow();

        return lst.stream().map(mapper::map).toList();
    }

    @Override
    public Boolean deleteByIdSrv(Long id)
    {
        validationModelId(id);
//        productRepository.deleteProduct(id);
        productRepository.deleteById(id);
        return true;
    }

    @Override
    public ProductDto addSrv(ProductDto dto)
    {
        validateDto(dto,false);
        //
        ProductEnt ent = productRepository.save(mapper.map(dto));
        //
        return mapper.map(ent);
    }

    @Override
    public ProductDto updateSrv(ProductDto dto) {
        validateDto(dto,true);
        //
        ProductEnt ent = productMapper.map(dto);
        //
        ProductEnt entDb = null;
        try {
            entDb = productRepository.findById(dto.getId()).orElseThrow();
        }catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
        //
        //assert entDb != null;
        //
        entDb.setImg(Optional.ofNullable(ent.getImg()).orElse(entDb.getImg()));
        entDb.setPrice(Optional.ofNullable(ent.getPrice()).orElse(entDb.getPrice()));
        entDb.setExist(Optional.ofNullable(ent.getExist()).orElse(entDb.getExist()));
        entDb.setEnabled(Optional.ofNullable(ent.getExist()).orElse(entDb.getEnabled()));
        entDb.setColorEnts(Optional.ofNullable(ent.getColorEnts()).orElse(entDb.getColorEnts()));
        entDb.setSizeEnts(Optional.ofNullable(ent.getSizeEnts()).orElse(entDb.getSizeEnts()));
        //
        entDb = productRepository.save(entDb);
        return mapper.map(entDb); //return dtoDb;
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

    @Override
    @SneakyThrows
    public void validateDto(ProductDto dto, Boolean checkId)
    {
        if(dto == null)
            throw new Exception("Error! validationModelProduct _ null product");
        if(checkId && (dto.getId() == null || dto.getId()<1))
            throw new Exception("Error! validationModelProduct _ wrong id");
    }
    //endregion

    public Optional<HashMap<Long,Long>> getProductPrices(List<Long> productIds)
    {
        return productRepository.getProductPrices(productIds);
    }
}
