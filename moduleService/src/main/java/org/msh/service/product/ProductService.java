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
import org.msh.service.generics.MyGenericService;
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
public class ProductService implements MyGenericService<ProductDto> {

    private final ProductRepositoryJpa productRepository;
    private final ProductMapper mapper;
    private final ProductMapper productMapper;


    @Autowired
    public ProductService(ProductRepositoryJpa productRepository
            , ProductMapper productMapper)
    {
        this.productRepository = productRepository;
        this.mapper = productMapper;
        this.productMapper = productMapper;
    }



    @Override
    public ProductDto findByIdSrv(Long id)
    {
        validationModelId(id);
        ProductEnt ent = productRepository.findById(id).orElseThrow();
        ProductDto dto = (ent == null) ? null : mapper.map(ent) ;
        return dto;
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
        ent.setExist(true);
        ent.setEnabled(true);
        //
        return mapper.map(ent);
    }

    @Override
    public ProductDto updateSrv(ProductDto dto) {
        validateDto(dto,true);
        //
        ProductEnt ent = productMapper.map(dto);
        //
        ProductDto resDto = null;
        ProductEnt dbEnt = null;
        //
        try {
            dbEnt = productRepository.findById(dto.getId()).orElseThrow();
            //
            dbEnt.setImg(Optional.ofNullable(ent.getImg()).orElse(dbEnt.getImg()));
            dbEnt.setPrice(Optional.ofNullable(ent.getPrice()).orElse(dbEnt.getPrice()));
            dbEnt.setExist(Optional.ofNullable(ent.getExist()).orElse(dbEnt.getExist()));
            dbEnt.setEnabled(Optional.ofNullable(ent.getEnabled()).orElse(dbEnt.getEnabled()));
            dbEnt.setColorEnts(Optional.ofNullable(ent.getColorEnts()).orElse(dbEnt.getColorEnts()));
            dbEnt.setSizeEnts(Optional.ofNullable(ent.getSizeEnts()).orElse(dbEnt.getSizeEnts()));
            //
            resDto = mapper.map(productRepository.save(dbEnt));
            //
        }catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
        //
        return resDto;
    }


    //region private methods for validation
    @SneakyThrows
    public void validationModelId(Long id)
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


    public Optional<HashMap<Long,Long>> getProductPrices(List<Long> productIds)
    {
        return productRepository.getProductPrices(productIds);
    }

    public List<ProductDto> findByBrandLikeSrv(String brand)
    {
        List<ProductEnt> lst = productRepository.findByBrandLike(brand).orElseThrow();

        return lst.stream().map(mapper::map).toList();
    }


}


//    //region category of product
//    public List<ProductCategoryDto> findAllCategoriesSrv()
//    {
//        return productCategoryRepositoryJpa.findAllByEnabledIsTrueOrderByTitleAsc()
//                .stream()
//                //.map(mapper::map)
//                .map(x->modelMapper.map(x,ProductCategoryDto.class))
//                .toList();
//    }
//    //endregion
