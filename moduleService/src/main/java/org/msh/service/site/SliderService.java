package org.msh.service.site;

import org.modelmapper.ModelMapper;
import org.msh.repositoryJpa.site.NavRepositoryJpa;
import org.msh.repositoryJpa.site.SliderRepositoryJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SliderService {

    private final SliderRepositoryJpa sliderRepositoryJpa;
    private final ModelMapper modelMapper;

    @Autowired
    public SliderService(SliderRepositoryJpa sliderRepositoryJpa, ModelMapper modelMapper)
    {
        this.sliderRepositoryJpa = sliderRepositoryJpa;
        this.modelMapper = modelMapper;
    }
}
