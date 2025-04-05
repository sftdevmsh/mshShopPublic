package org.msh.service.site;

import org.modelmapper.ModelMapper;
import org.msh.repositoryJpa.file.FileRepositoryJpa;
import org.msh.repositoryJpa.site.BlogRepositoryJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BlogService {

    private final BlogRepositoryJpa blogRepositoryJpa;
    private final ModelMapper modelMapper;

    @Autowired
    public BlogService(BlogRepositoryJpa blogRepositoryJpa
            , ModelMapper modelMapper)
    {
        this.blogRepositoryJpa = blogRepositoryJpa;
        this.modelMapper = modelMapper;
    }
}
