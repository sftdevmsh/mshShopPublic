package org.msh.service.site;

import org.modelmapper.ModelMapper;
import org.msh.dto.product.ProductDto;
import org.msh.dto.site.NavDto;
import org.msh.entity.site.NavEnt;
import org.msh.repositoryJpa.site.BlogRepositoryJpa;
import org.msh.repositoryJpa.site.NavRepositoryJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NavService {

    private final NavRepositoryJpa navRepositoryJpa;
    private final ModelMapper modelMapper;

    @Autowired
    public NavService(NavRepositoryJpa navRepositoryJpa, ModelMapper modelMapper)
    {
        this.navRepositoryJpa = navRepositoryJpa;
        this.modelMapper = modelMapper;
    }

    public List<NavDto> findAllSrv()
    {
//        return navRepositoryJpa.findAll()
        return navRepositoryJpa.findAllByEnabledIsTrueOrderByOrderNumberAsc()
                .stream()
                .map(x-> modelMapper.map(x, NavDto.class))
                .toList();
    }


}
