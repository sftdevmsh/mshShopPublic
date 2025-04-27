package org.msh.serv.service.site;

import lombok.SneakyThrows;
import org.modelmapper.ModelMapper;
import org.msh.serv.dto.site.SliderDto;
import org.msh.repo.entity.file.FileEnt;
import org.msh.repo.entity.site.SliderEnt;
import org.msh.common.exceptions.MyExc;
import org.msh.repo.repositoryJpa.site.SliderRepositoryJpa;
import org.msh.serv.service.generics.MyGenericService;
import org.msh.serv.service.generics.MyGenericServiceCls;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SliderService extends MyGenericServiceCls implements MyGenericService<SliderDto> {

    private final SliderRepositoryJpa sliderRepositoryJpa;
    private final ModelMapper modelMapper;

    @Autowired
    public SliderService(SliderRepositoryJpa sliderRepositoryJpa, ModelMapper modelMapper)
    {
        this.sliderRepositoryJpa = sliderRepositoryJpa;
        this.modelMapper = modelMapper;
    }

    
    
    
    @Override
    public SliderDto findByIdSrv(Long id) {
        validationModelId(id);
        //
        SliderDto dto = null;
        //
        try {
            SliderEnt ent = sliderRepositoryJpa.findById(id).orElseThrow();
            dto =modelMapper.map(ent, SliderDto.class);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        //
        return dto;
    }

    @Override
    public List<SliderDto> findAllSrv()
    {
        return sliderRepositoryJpa
                //.findAllByEnabledIsTrueOrderByOrderNumberAsc()
                .findAll()
                .stream()
                .map(x-> modelMapper.map(x, SliderDto.class))
                .toList();
    }

    @Override
    public Page<SliderDto> findAllSrv(Integer page, Integer size)
    {
        page = validatePage(page);
        size = validateSize(size);
        //
        return sliderRepositoryJpa
                //.findAllByEnabledIsTrueOrderByOrderNumberAsc(
                .findAll(
                        Pageable
                                .ofSize(size)
                                .withPage(page)
                )
                //.stream()
                .map(x-> modelMapper.map(x, SliderDto.class));
        //.toList();
    }


    @Override
    public SliderDto addSrv(SliderDto dto) throws MyExc {
        validateDto(dto,false);
        //
        SliderEnt sliderEnt = modelMapper.map(dto, SliderEnt.class);
        //
        Integer i = sliderRepositoryJpa.myFindLastOrderNumber();
        if(i==null)
            i=0;
        sliderEnt.setOrderNumber(++i);
        //
        return modelMapper.map(sliderRepositoryJpa.save(sliderEnt) , SliderDto.class);
    }

    @Override
    public Boolean deleteByIdSrv(Long id) {
        validationModelId(id);
        sliderRepositoryJpa.deleteById(id);
        return true;
    }


    @Override
    public SliderDto updateSrv(SliderDto dto) throws MyExc {
        validateDto(dto,true);
        //
        SliderDto dtoRes = null;
        SliderEnt dbEnt = null;
        try {
            dbEnt = sliderRepositoryJpa.findFirstById(dto.getId()).orElseThrow();
            //
            dbEnt.setTitle(Optional.ofNullable(dto.getTitle()).orElse(dbEnt.getTitle()));
            dbEnt.setLink(Optional.ofNullable(dto.getLink()).orElse(dbEnt.getLink()));
            dbEnt.setOrderNumber(Optional.ofNullable(dto.getOrderNumber()).orElse(dbEnt.getOrderNumber()));
            //
            FileEnt img = modelMapper.map(dto.getImg(),FileEnt.class);
            dbEnt.setImg(Optional.ofNullable(img).orElse(dbEnt.getImg()));
            //
            dtoRes = modelMapper.map(sliderRepositoryJpa.save(dbEnt),SliderDto.class);
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

    @Override
    public void validateDto(SliderDto dto, Boolean checkId) throws MyExc {
        if(dto==null)
            throw new MyExc("validateDto ...");
        if(checkId && (dto.getId() == null || dto.getId()<1))
            throw new MyExc("validateDto ...");
        if(dto.getTitle()==null || dto.getTitle().isEmpty())
            throw new MyExc("validateDto ...");
        if(dto.getLink()==null || dto.getLink().isEmpty())
            throw new MyExc("validateDto ...");
        if(dto.getImg()==null
                || dto.getImg().getId() == null
                || dto.getImg().getId() < 1
                || dto.getImg().getPath().isEmpty()
                || dto.getImg().getName().isEmpty())
            throw new MyExc("validateDto ...");
    }
    //endregion


    @Transactional
    public boolean swapUpSrv(Long id) {
        validationModelId(id);
        SliderEnt ent = sliderRepositoryJpa.findById(id).orElseThrow();
        boolean res = false;
        Optional<SliderEnt> previous = sliderRepositoryJpa
                .findFirstByOrderNumberLessThanOrderByOrderNumberDesc(ent.getOrderNumber());
        if(previous.isPresent())
        {
            SliderEnt pre = previous.get();
            Integer orderNumber = ent.getOrderNumber();
            ent.setOrderNumber(pre.getOrderNumber());
            pre.setOrderNumber(orderNumber);
            ArrayList<SliderEnt> arr = new ArrayList<SliderEnt>();
            arr.add(ent);
            arr.add(pre);
            sliderRepositoryJpa.saveAll(arr);
            res = true;
        }
        return res;
    }
    @Transactional
    public boolean swapDownSrv(Long id) {
        validationModelId(id);
        SliderEnt ent = sliderRepositoryJpa.findById(id).orElseThrow();
        boolean res = false;
        Optional<SliderEnt> after = sliderRepositoryJpa
                .findFirstByOrderNumberGreaterThanOrderByOrderNumberAsc(ent.getOrderNumber());
        if(after.isPresent())
        {
            SliderEnt aft = after.get();
            Integer orderNumber = ent.getOrderNumber();
            ent.setOrderNumber(aft.getOrderNumber());
            aft.setOrderNumber(orderNumber);
            ArrayList<SliderEnt> arr = new ArrayList<SliderEnt>();
            arr.add(ent);
            arr.add(aft);
            sliderRepositoryJpa.saveAll(arr);
            res = true;
        }
        return res;
    }

}
