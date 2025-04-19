package org.msh.service.product;

import lombok.SneakyThrows;
import org.modelmapper.ModelMapper;
import org.msh.dto.product.ProductCategoryDto;
import org.msh.dto.product.SizeDto;
import org.msh.entity.product.SizeEnt;
import org.msh.exceptions.MyExc;
import org.msh.repositoryJpa.product.ColorRepositoryJpa;
import org.msh.repositoryJpa.product.SizeRepositoryJpa;
import org.msh.repositoryJpa.product.SizeRepositoryJpa;
import org.msh.service.generics.MyGenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SizeService implements MyGenericService<SizeDto> {

    private final SizeRepositoryJpa sizeRepository;
    private final ModelMapper modelMapper;


    @Autowired
    public SizeService(SizeRepositoryJpa sizeRepository
            , ModelMapper modelMapper)
    {
        this.sizeRepository = sizeRepository;
        this.modelMapper = modelMapper;
    }





    @Override
    public SizeDto findByIdSrv(Long id)
    {
        SizeEnt ent = null;
        try {
            ent = sizeRepository.findById(id).orElseThrow();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return (ent == null) ? null : modelMapper.map(ent,SizeDto.class) ;
    }


    @Override
    public List<SizeDto> findAllSrv()
    {
        return sizeRepository.findAll()
                .stream()
                .map(x -> modelMapper.map(x,SizeDto.class))
                .toList();
    }

    @Override
    public Page<SizeDto> findAllSrv(Integer page, Integer size) {
        return sizeRepository
                .findAll(Pageable.ofSize(size).withPage(page))
                .map(x-> modelMapper.map(x, SizeDto.class));
    }



    @Override
    public Boolean deleteByIdSrv(Long id)
    {
        sizeRepository.deleteById(id);
        return true;
    }

    @Override
    public SizeDto addSrv(SizeDto dto) throws MyExc {
        validateDto(dto,false);
        //
        SizeEnt ent = sizeRepository.save(modelMapper.map(dto, SizeEnt.class));
        //
        return modelMapper.map(ent,SizeDto.class);
    }

    @Override
    public SizeDto updateSrv(SizeDto dto)  {
        validateDto(dto,true);
        //
        SizeEnt ent = modelMapper.map(dto, SizeEnt.class);
        //
        SizeDto resDto = null;
        SizeEnt dbEnt = null;
        //
        try {
            dbEnt = sizeRepository.findById(dto.getId()).orElseThrow();
            //
            dbEnt.setName(Optional.ofNullable(ent.getName()).orElse(dbEnt.getName()));
            dbEnt.setDescription(Optional.ofNullable(ent.getDescription()).orElse(dbEnt.getDescription()));
            //
            resDto = modelMapper.map(sizeRepository.save(dbEnt),SizeDto.class);
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
    public void validateDto(SizeDto dto, Boolean checkId)
    {
        if(dto == null)
            throw new Exception("Error! validationModelProduct _ null product");
        if(checkId && (dto.getId() == null || dto.getId()<1))
            throw new Exception("Error! validationModelProduct _ wrong id");
    }
    //endregion

}
