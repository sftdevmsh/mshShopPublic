package org.msh.service.site;

import org.modelmapper.ModelMapper;
import org.msh.repositoryJpa.site.ContentRepositoryJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContentService {

    private final ContentRepositoryJpa contentRepositoryJpa;
    private final ModelMapper modelMapper;

    @Autowired
    public ContentService(ContentRepositoryJpa contentRepositoryJpa
            , ModelMapper modelMapper)
    {
        this.contentRepositoryJpa = contentRepositoryJpa;
        this.modelMapper = modelMapper;
    }
}
