package org.msh.service.product;

import lombok.SneakyThrows;
import org.modelmapper.ModelMapper;
import org.msh.dto.product.ColorDto;
import org.msh.dto.product.ProductCategoryDto;
import org.msh.entity.product.ColorEnt;
import org.msh.exceptions.MyExc;
import org.msh.repositoryJpa.product.ColorRepositoryJpa;
import org.msh.service.generics.MyGenericService;
import org.msh.service.generics.MyGenericServiceCls;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ColorService extends MyGenericServiceCls implements MyGenericService<ColorDto> {

    private final ColorRepositoryJpa colorRepository;
    private final ModelMapper modelMapper;


    @Autowired
    public ColorService(ColorRepositoryJpa colorRepository
            , ModelMapper modelMapper)
    {
        this.colorRepository = colorRepository;
        this.modelMapper = modelMapper;
    }



    @Override
    public ColorDto findByIdSrv(Long id)
    {
        ColorEnt ent = null;
        try {
            ent = colorRepository.findById(id).orElseThrow();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return (ent == null) ? null : modelMapper.map(ent,ColorDto.class) ;
    }


    @Override
    public List<ColorDto> findAllSrv()
    {
        return colorRepository.findAll()
                .stream()
                .map(x -> modelMapper.map(x,ColorDto.class))
                .toList();
    }

    @Override
    public Page<ColorDto> findAllSrv(Integer page, Integer size) {
        page = validatePage(page);
        size = validateSize(size);
        //
        return colorRepository
                .findAll(Pageable.ofSize(size).withPage(page))
                .map(x-> modelMapper.map(x, ColorDto.class));
    }




    @Override
    public Boolean deleteByIdSrv(Long id)
    {
        validationModelId(id);
        colorRepository.deleteById(id);
        return true;
    }

    @Override
    public ColorDto addSrv(ColorDto dto)  {
        validateDto(dto,false);
        //
        ColorEnt ent = colorRepository.save(modelMapper.map(dto, ColorEnt.class));
        //
        return modelMapper.map(ent,ColorDto.class);
    }

    @Override
    public ColorDto updateSrv(ColorDto dto)  {
        validateDto(dto,true);
        //
        ColorEnt ent = modelMapper.map(dto, ColorEnt.class);
        //
        ColorDto resDto = null;
        ColorEnt dbEnt = null;
        //
        try {
            dbEnt = colorRepository.findById(dto.getId()).orElseThrow();
            //
            dbEnt.setName(Optional.ofNullable(ent.getName()).orElse(dbEnt.getName()));
            dbEnt.setHex(Optional.ofNullable(ent.getHex()).orElse(dbEnt.getHex()));
            //
            resDto = modelMapper.map(colorRepository.save(dbEnt),ColorDto.class);
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
    public void validateDto(ColorDto dto, Boolean checkId)
    {
        if(dto == null)
            throw new Exception("Error! validationModelProduct _ null product");
        if(checkId && (dto.getId() == null || dto.getId()<1))
            throw new Exception("Error! validationModelProduct _ wrong id");
    }
    //endregion

}
