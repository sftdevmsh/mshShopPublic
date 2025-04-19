package org.msh.service.product;

import lombok.SneakyThrows;
import org.modelmapper.ModelMapper;
import org.msh.dto.product.ProductCategoryDto;
import org.msh.dto.product.ProductDto;
import org.msh.entity.product.ProductCategoryEnt;
import org.msh.exceptions.MyExc;
import org.msh.repositoryJpa.product.ColorRepositoryJpa;
import org.msh.repositoryJpa.product.ProductCategoryRepositoryJpa;
import org.msh.repositoryJpa.product.SizeRepositoryJpa;
import org.msh.service.generics.MyGenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductCategoryService implements MyGenericService<ProductCategoryDto> {

    private final ProductCategoryRepositoryJpa productCategoryRepository;
    private final ModelMapper modelMapper;


    @Autowired
    public ProductCategoryService(ProductCategoryRepositoryJpa productCategoryRepository
            , ProductCategoryRepositoryJpa productCategoryRepositoryJpa
            , ColorRepositoryJpa colorRepositoryJpa
            , SizeRepositoryJpa sizeRepositoryJpa
            , ModelMapper modelMapper)
    {
        this.productCategoryRepository = productCategoryRepository;
        this.modelMapper = modelMapper;
    }





    @Override
    public ProductCategoryDto findByIdSrv(Long id)
    {
        ProductCategoryEnt ent = null;
        try {
            ent = productCategoryRepository.findById(id).orElseThrow();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return (ent == null) ? null : modelMapper.map(ent,ProductCategoryDto.class) ;
    }


    @Override
    public List<ProductCategoryDto> findAllSrv()
    {
        return productCategoryRepository
                .findAll()
                .stream()
                .map(x -> modelMapper.map(x,ProductCategoryDto.class))
                .toList();
    }

    @Override
    public Page<ProductCategoryDto> findAllSrv(Integer page, Integer size) {
        return productCategoryRepository
                .findAll(Pageable.ofSize(size).withPage(page))
                //.findAllByEnabledIsTrueOrderByTitleAsc(Pageable.ofSize(size).withPage(page))
                .map(x-> modelMapper.map(x, ProductCategoryDto.class));
    }

    public Page<ProductCategoryDto> findAllEnabledSrv(Integer page, Integer size) {
        return productCategoryRepository
                .findAllByEnabledIsTrueOrderByTitleAsc(Pageable.ofSize(size).withPage(page))
                .map(x-> modelMapper.map(x, ProductCategoryDto.class));
    }



    @Override
    public Boolean deleteByIdSrv(Long id)
    {
        validationModelId(id);
        productCategoryRepository.deleteById(id);
        return true;
    }

    @Override
    public ProductCategoryDto addSrv(ProductCategoryDto dto) throws MyExc {
        validateDto(dto,false);
        //
        ProductCategoryEnt ent = productCategoryRepository.save(modelMapper.map(dto, ProductCategoryEnt.class));
        //
        return modelMapper.map(ent,ProductCategoryDto.class);
    }

    @Override
    public ProductCategoryDto updateSrv(ProductCategoryDto dto) {
        validateDto(dto,true);
        //
        ProductCategoryEnt ent = modelMapper.map(dto, ProductCategoryEnt.class);
        //
        ProductCategoryDto resDto = null;
        ProductCategoryEnt dbEnt = null;
        //
        try {
            dbEnt = productCategoryRepository.findById(dto.getId()).orElseThrow();
            //
            dbEnt.setTitle(Optional.ofNullable(ent.getTitle()).orElse(dbEnt.getTitle()));
            dbEnt.setDescription(Optional.ofNullable(ent.getDescription()).orElse(dbEnt.getDescription()));
            dbEnt.setImg(Optional.ofNullable(ent.getImg()).orElse(dbEnt.getImg()));
            dbEnt.setEnabled(Optional.ofNullable(ent.getEnabled()).orElse(dbEnt.getEnabled()));
            //
            resDto = modelMapper.map(productCategoryRepository.save(dbEnt),ProductCategoryDto.class);
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
    @Override
    public void validationModelId(Long id)
    {
        if(id == null || id<=0)
            throw new Exception("Error! validationModelId _ wrong id");
    }

    @SneakyThrows
    @Override
    public void validateDto(ProductCategoryDto dto, Boolean checkId)
    {
        if(dto == null)
            throw new Exception("Error! validationModelProduct _ null product");
        if(checkId && (dto.getId() == null || dto.getId()<1))
            throw new Exception("Error! validationModelProduct _ wrong id");
    }
    //endregion


}
