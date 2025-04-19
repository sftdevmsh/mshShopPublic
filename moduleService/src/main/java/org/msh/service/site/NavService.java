package org.msh.service.site;

import org.modelmapper.ModelMapper;
import org.msh.dto.site.NavDto;
import org.msh.entity.site.NavEnt;
import org.msh.exceptions.MyExc;
import org.msh.repositoryJpa.site.NavRepositoryJpa;
import org.msh.service.generics.MyInfGnrSrv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NavService implements MyInfGnrSrv<NavDto> {

    private final NavRepositoryJpa navRepositoryJpa;
    private final ModelMapper modelMapper;

    @Autowired
    public NavService(NavRepositoryJpa navRepositoryJpa, ModelMapper modelMapper)
    {
        this.navRepositoryJpa = navRepositoryJpa;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<NavDto> findAllSrv()
    {
        return navRepositoryJpa
                //.findAllByEnabledIsTrueOrderByOrderNumberAsc()
                .findAll()
                .stream()
                .map(x-> modelMapper.map(x, NavDto.class))
                .toList();
    }

    @Override
    public Page<NavDto> findAllSrv(Integer page, Integer size)
    {
        return navRepositoryJpa
                //.findAllByEnabledIsTrueOrderByOrderNumberAsc(
                .findAll(
                        Pageable
                        .ofSize(size)
                        .withPage(page)
                        )
                //.stream()
                .map(x-> modelMapper.map(x, NavDto.class));
                //.toList();
    }

    @Override
    public NavDto findByIdSrv(Long id) {
        //
        NavDto dto = null;
        //
        try {
            NavEnt navEnt = navRepositoryJpa.findById(id).orElseThrow();
            dto =modelMapper.map(navEnt, NavDto.class);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        //
        return dto;
    }


    @Override
    public NavDto addSrv(NavDto dto) throws MyExc {
        validateDto(dto,false);
        //
        NavEnt navEnt = modelMapper.map(dto, NavEnt.class);
        //
        Integer i = navRepositoryJpa.myFindLastOrderNumber();
        if(i==null)
            i=0;
        navEnt.setOrderNumber(++i);
        //
        return modelMapper.map(navRepositoryJpa.save(navEnt) , NavDto.class);
    }

    @Override
    public Boolean deleteByIdSrv(Long id) {
        navRepositoryJpa.deleteById(id);
        return true;
    }

    @Override
    public NavDto updateSrv(NavDto dto) throws MyExc {
        validateDto(dto,true);
        //
        NavEnt entOld = null;
        try {
            entOld = navRepositoryJpa.findFirstById(dto.getId()).orElseThrow();
        }catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        //
        entOld.setLink(Optional.ofNullable(dto.getLink()).orElse(entOld.getLink()));
        entOld.setTitle(Optional.ofNullable(dto.getTitle()).orElse(entOld.getTitle()));
        entOld.setOrderNumber(Optional.ofNullable(dto.getOrderNumber()).orElse(entOld.getOrderNumber()));
        //
        return modelMapper.map(navRepositoryJpa.save(entOld),NavDto.class);
    }

    @Override
    public void validateDto(NavDto dto, Boolean checkId) throws MyExc {
        if(dto==null)
            throw new MyExc("validateDto ...");
        if(dto.getTitle()==null || dto.getTitle().isEmpty())
            throw new MyExc("validateDto ...");
        if(dto.getLink()==null || dto.getLink().isEmpty())
            throw new MyExc("validateDto ...");
        if(checkId && (dto.getId() == null || dto.getId()<1))
            throw new MyExc("validateDto ...");
    }
}
