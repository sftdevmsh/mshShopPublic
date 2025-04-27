package org.msh.serv.service.site;

import lombok.SneakyThrows;
import org.modelmapper.ModelMapper;
import org.msh.serv.dto.site.BlogDto;
import org.msh.repo.entity.file.FileEnt;
import org.msh.repo.entity.site.BlogEnt;
import org.msh.repo.enums.BlogStatus;
import org.msh.common.exceptions.MyExc;
import org.msh.repo.repositoryJpa.site.BlogRepositoryJpa;
import org.msh.serv.service.generics.MyGenericService;
import org.msh.serv.service.generics.MyGenericServiceCls;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class BlogService extends MyGenericServiceCls implements MyGenericService<BlogDto> {

    private final BlogRepositoryJpa blogRepositoryJpa;
    private final ModelMapper modelMapper;


    @Autowired
    public BlogService(BlogRepositoryJpa blogRepositoryJpa
            , ModelMapper modelMapper)
    {
        this.blogRepositoryJpa = blogRepositoryJpa;
        this.modelMapper = modelMapper;
    }




    @Override
    public BlogDto findByIdSrv(Long id) {
        //
        BlogDto dto = null;
        //
        try {
            BlogEnt blogEnt = blogRepositoryJpa.findById(id).orElseThrow();
            dto =modelMapper.map(blogEnt, BlogDto.class);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        //
        return dto;
    }

    @Override
    public List<BlogDto> findAllSrv()
    {
        return blogRepositoryJpa
                //.findAllByEnabledIsTrueOrderByOrderNumberAsc()
                .findAll()
                .stream()
                .map(x-> modelMapper.map(x, BlogDto.class))
                .toList();
    }

    @Override
    public Page<BlogDto> findAllSrv(Integer page, Integer size)
    {
        page = validatePage(page);
        size = validateSize(size);
        //
        return blogRepositoryJpa
                .myFindAllPublished(
                        Pageable
                                .ofSize(size)
                                .withPage(page)
                )
                .map(x-> modelMapper.map(x, BlogDto.class));
    }


    @Override
    public Boolean deleteByIdSrv(Long id) {
        validationModelId(id);
        blogRepositoryJpa.deleteById(id);
        return true;
    }

    @Override
    public BlogDto addSrv(BlogDto dto) {
        validateDto(dto,false);
        //
        BlogEnt blogEnt = modelMapper.map(dto, BlogEnt.class);
        //
        if(blogEnt.getPublishDate()==null)
            blogEnt.setPublishDate(LocalDateTime.now());
        //
        if(blogEnt.getBlogStatus()==null)
            blogEnt.setBlogStatus(BlogStatus.Published);
        //
        return modelMapper.map(blogRepositoryJpa.save(blogEnt) , BlogDto.class);
    }

    @Override
    public BlogDto updateSrv(BlogDto dto)  {
        validateDto(dto,true);
        //
        BlogDto dtoRes = null;
        BlogEnt entOld = null;
        try {
            entOld = blogRepositoryJpa.findFirstById(dto.getId()).orElseThrow();
            //
            entOld.setTitle(Optional.ofNullable(dto.getTitle()).orElse(entOld.getTitle()));
            entOld.setSubTitle(Optional.ofNullable(dto.getSubTitle()).orElse(entOld.getSubTitle()));
            entOld.setDescription(Optional.ofNullable(dto.getDescription()).orElse(entOld.getDescription()));
            //
            String status = "";
            for(BlogStatus b : BlogStatus.values())
                if(b.name().equalsIgnoreCase(dto.getBlogStatus().toString()))
                    status = dto.getBlogStatus().toString();
            if(!status.isEmpty())
                entOld.setBlogStatus(Optional.ofNullable(dto.getBlogStatus()).orElse(entOld.getBlogStatus()));
            //
            FileEnt img = modelMapper.map(dto.getImg(), FileEnt.class);
            entOld.setImg(Optional.ofNullable(img).orElse(entOld.getImg()));
            //
            dtoRes = modelMapper.map(blogRepositoryJpa.save(entOld),BlogDto.class);
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
    public void validateDto(BlogDto dto, Boolean checkId) {
        if(dto == null)
            throw new MyExc("validateDto ...");
        if(checkId && (dto.getId() == null || dto.getId()<1))
            throw new MyExc("validateDto ...");
        if(dto.getTitle()==null || dto.getTitle().isEmpty())
            throw new MyExc("validateDto ...");
        if(dto.getSubTitle()==null || dto.getSubTitle().isEmpty())
            throw new MyExc("validateDto ...");
        if(dto.getDescription()==null || dto.getDescription().isEmpty())
            throw new MyExc("validateDto ...");
        if(dto.getImg()==null
                || dto.getImg().getId() == null
                || dto.getImg().getId() < 1
                || dto.getImg().getPath().isEmpty()
                || dto.getImg().getName().isEmpty())
            throw new MyExc("validateDto ...");
    }
    //endregion



    public BlogDto findByTitle(String title) {
        BlogEnt ent = null;
        BlogDto dto = null;
        try {
            ent = blogRepositoryJpa.findFirstByTitleEqualsIgnoreCase(title).orElseThrow();
            dto = modelMapper.map(ent, BlogDto.class);
        }catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        return dto;
    }
}
