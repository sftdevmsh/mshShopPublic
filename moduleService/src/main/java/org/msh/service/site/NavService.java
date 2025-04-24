package org.msh.service.site;

import lombok.SneakyThrows;
import org.modelmapper.ModelMapper;
import org.msh.dto.site.BlogDto;
import org.msh.dto.site.NavDto;
import org.msh.entity.site.NavEnt;
import org.msh.exceptions.MyExc;
import org.msh.repositoryJpa.site.NavRepositoryJpa;
import org.msh.service.generics.MyGenericService;
import org.msh.service.generics.MyGenericServiceCls;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class NavService extends MyGenericServiceCls implements MyGenericService<NavDto> {

    private final NavRepositoryJpa navRepositoryJpa;
    private final ModelMapper modelMapper;

    @Autowired
    public NavService(NavRepositoryJpa navRepositoryJpa, ModelMapper modelMapper)
    {
        this.navRepositoryJpa = navRepositoryJpa;
        this.modelMapper = modelMapper;
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
        page = validatePage(page);
        size = validateSize(size);
        //
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
    public NavDto addSrv(NavDto dto) {
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
        validationModelId(id);
        navRepositoryJpa.deleteById(id);
        return true;
    }

    @Override
    public NavDto updateSrv(NavDto dto) throws MyExc {
        validateDto(dto,true);
        //
        NavDto dtoRes = null;
        NavEnt entOld = null;
        try {
            entOld = navRepositoryJpa.findFirstById(dto.getId()).orElseThrow();
            //
            entOld.setLink(Optional.ofNullable(dto.getLink()).orElse(entOld.getLink()));
            entOld.setTitle(Optional.ofNullable(dto.getTitle()).orElse(entOld.getTitle()));
            entOld.setOrderNumber(Optional.ofNullable(dto.getOrderNumber()).orElse(entOld.getOrderNumber()));
            //
            dtoRes = modelMapper.map(navRepositoryJpa.save(entOld),NavDto.class);
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
    public void validateDto(NavDto dto, Boolean checkId) {
        if(dto == null)
            throw new MyExc("validateDto ...");
        if(checkId && (dto.getId() == null || dto.getId()<1))
            throw new MyExc("validateDto ...");
        if(dto.getTitle()==null || dto.getTitle().isEmpty())
            throw new MyExc("validateDto ...");
        if(dto.getLink()==null || dto.getLink().isEmpty())
            throw new MyExc("validateDto ...");
    }
    //endregion




    @Transactional
    public boolean swapUpSrv(Long id) {
        validationModelId(id);
        NavEnt ent = navRepositoryJpa.findById(id).orElseThrow();
        boolean res = false;
        Optional<NavEnt> previous = navRepositoryJpa
                .findFirstByOrderNumberLessThanOrderByOrderNumberDesc(ent.getOrderNumber());
        if(previous.isPresent())
        {
            NavEnt pre = previous.get();
            Integer orderNumber = ent.getOrderNumber();
            ent.setOrderNumber(pre.getOrderNumber());
            pre.setOrderNumber(orderNumber);
            ArrayList<NavEnt> arr = new ArrayList<NavEnt>();
            arr.add(ent);
            arr.add(pre);
            navRepositoryJpa.saveAll(arr);
            res = true;
        }
        return res;
    }
    @Transactional
    public boolean swapDownSrv(Long id) {
        validationModelId(id);
        NavEnt ent = navRepositoryJpa.findById(id).orElseThrow();
        boolean res = false;
        Optional<NavEnt> after = navRepositoryJpa
                .findFirstByOrderNumberGreaterThanOrderByOrderNumberAsc(ent.getOrderNumber());
        if(after.isPresent())
        {
            NavEnt aft = after.get();
            Integer orderNumber = ent.getOrderNumber();
            ent.setOrderNumber(aft.getOrderNumber());
            aft.setOrderNumber(orderNumber);
            ArrayList<NavEnt> arr = new ArrayList<NavEnt>();
            arr.add(ent);
            arr.add(aft);
            navRepositoryJpa.saveAll(arr);
            res = true;
        }
        return res;
    }
}
