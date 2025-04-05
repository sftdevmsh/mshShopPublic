package org.msh.service.site;

import org.modelmapper.ModelMapper;
import org.msh.dto.site.BlogDto;
import org.msh.dto.site.ContentDto;
import org.msh.dto.site.SingleBlogDto;
import org.msh.entity.site.BlogEnt;
import org.msh.exceptions.NotFoundExc;
import org.msh.repositoryJpa.file.FileRepositoryJpa;
import org.msh.repositoryJpa.site.BlogRepositoryJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

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


    public List<BlogDto> findAllSrv(Integer page, Integer size)
    {
        if(page == null)
            page = 0;
        if(size == null)
            size = 10;  //default value

        //return blogRepositoryJpa.findAllByOrderByTitleAsc(Pageable.ofSize(size).withPage(page))
        return blogRepositoryJpa.myFindAllPublished(Pageable.ofSize(size).withPage(page))
                .stream()
                .map(x-> modelMapper.map(x, BlogDto.class))
                .toList();
    }


    public SingleBlogDto findById(Long id)
    {
        SingleBlogDto dto = null;
        try {
            BlogEnt ent = blogRepositoryJpa.findById(id).orElseThrow(NotFoundExc::new);
            dto = modelMapper.map(ent, SingleBlogDto.class);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return dto;
    }

    public SingleBlogDto findByTitle(String title)
    {
        SingleBlogDto dto = null;
        try {
            BlogEnt ent = blogRepositoryJpa.findFirstByTitleEqualsIgnoreCase(title).orElseThrow(NotFoundExc::new);
            dto = modelMapper.map(ent, SingleBlogDto.class);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return dto;
    }



}
