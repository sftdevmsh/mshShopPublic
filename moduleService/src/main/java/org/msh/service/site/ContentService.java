package org.msh.service.site;

import lombok.SneakyThrows;
import org.modelmapper.ModelMapper;
import org.msh.dto.product.SizeDto;
import org.msh.dto.site.ContentDto;
import org.msh.entity.site.ContentEnt;
import org.msh.exceptions.MyExc;
import org.msh.repositoryJpa.site.ContentRepositoryJpa;
import org.msh.service.generics.MyGenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContentService implements MyGenericService<ContentDto> {

    private final ContentRepositoryJpa contentRepositoryJpa;
    private final ModelMapper modelMapper;

    @Autowired
    public ContentService(ContentRepositoryJpa contentRepositoryJpa
            , ModelMapper modelMapper)
    {
        this.contentRepositoryJpa = contentRepositoryJpa;
        this.modelMapper = modelMapper;
    }

    
    
    
    @Override
    public ContentDto findByIdSrv(Long id) {
        //
        ContentDto dto = null;
        //
        try {
            ContentEnt contentEnt = contentRepositoryJpa.findById(id).orElseThrow();
            dto =modelMapper.map(contentEnt, ContentDto.class);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        //
        return dto;
    }

    @Override
    public List<ContentDto> findAllSrv()
    {
        return contentRepositoryJpa
                //.findAllByEnabledIsTrueOrderByOrderNumberAsc()
                .findAll()
                .stream()
                .map(x-> modelMapper.map(x, ContentDto.class))
                .toList();
    }

    @Override
    public Page<ContentDto> findAllSrv(Integer page, Integer size)
    {
        return contentRepositoryJpa
                //.findAllByEnabledIsTrueOrderByOrderNumberAsc(
                .findAll(
                        Pageable
                                .ofSize(size)
                                .withPage(page)
                )
                //.stream()
                .map(x-> modelMapper.map(x, ContentDto.class));
        //.toList();
    }

    @Override
    public Boolean deleteByIdSrv(Long id) {
        validationModelId(id);
        contentRepositoryJpa.deleteById(id);
        return true;
    }

    @Override
    public ContentDto addSrv(ContentDto dto) throws MyExc {
        validateDto(dto,false);
        //
        ContentEnt contentEnt = modelMapper.map(dto, ContentEnt.class);
        //
        return modelMapper.map(contentRepositoryJpa.save(contentEnt) , ContentDto.class);
    }

    @Override
    public ContentDto updateSrv(ContentDto dto) throws MyExc {
        validateDto(dto,true);
        //
        ContentDto dtoRes = null;
        ContentEnt dbEnt = null;
        try {
            dbEnt = contentRepositoryJpa.findFirstById(dto.getId()).orElseThrow();
            //
            dbEnt.setTitle(Optional.ofNullable(dto.getTitle()).orElse(dbEnt.getTitle()));
            dbEnt.setValue(Optional.ofNullable(dto.getValue()).orElse(dbEnt.getValue()));
            //
            dtoRes = modelMapper.map(contentRepositoryJpa.save(dbEnt),ContentDto.class);
            //
        }catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        //
        return dtoRes;
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
    public void validateDto(ContentDto dto, Boolean checkId) {
        if(dto == null)
            throw new MyExc("validateDto ...");
        if(checkId && (dto.getId() == null || dto.getId()<1))
            throw new MyExc("validateDto ...");
        if(dto.getTitle()==null || dto.getTitle().isEmpty())
            throw new MyExc("validateDto ...");
        if(dto.getValue()==null || dto.getValue().isEmpty())
            throw new MyExc("validateDto ...");
    }


}
