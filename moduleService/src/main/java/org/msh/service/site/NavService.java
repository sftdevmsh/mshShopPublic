package org.msh.service.site;

import org.modelmapper.ModelMapper;
import org.msh.repositoryJpa.site.BlogRepositoryJpa;
import org.msh.repositoryJpa.site.NavRepositoryJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
