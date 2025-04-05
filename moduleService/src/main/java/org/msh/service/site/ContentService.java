package org.msh.service.site;

import org.modelmapper.ModelMapper;
import org.msh.dto.site.BlogDto;
import org.msh.dto.site.ContentDto;
import org.msh.dto.site.NavDto;
import org.msh.entity.site.ContentEnt;
import org.msh.exceptions.NotFoundExc;
import org.msh.repositoryJpa.site.ContentRepositoryJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<ContentDto> findAllSrv()
    {
        return contentRepositoryJpa.findAllByOrderByTitleAsc()
                .stream()
                .map(x-> modelMapper.map(x, ContentDto.class))
                .toList();
    }

    public ContentDto findByTitleSrv(String title)
    {
        ContentDto dto = null;
        try {
            ContentEnt ent = contentRepositoryJpa.findFirstByTitleEqualsIgnoreCase(title).orElseThrow(NotFoundExc::new);
            dto = modelMapper.map(ent, ContentDto.class);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return dto;
    }

}
