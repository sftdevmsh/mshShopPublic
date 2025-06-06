package org.msh.serv.service.file;

import lombok.SneakyThrows;
import org.modelmapper.ModelMapper;
import org.msh.serv.dto.file.FileDto;
import org.msh.repo.entity.file.FileEnt;
import org.msh.common.exceptions.MyExc;
import org.msh.common.exceptions.NotFoundExc;
import org.msh.repo.repositoryJpa.file.FileRepositoryJpa;
import org.msh.serv.service.generics.MyGenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class FileService implements MyGenericService<FileDto> {


    private final FileRepositoryJpa fileRepositoryJpa;
    private final ModelMapper modelMapper;

    @Autowired
    public FileService(FileRepositoryJpa fileRepositoryJpa, ModelMapper modelMapper)
    {
        this.fileRepositoryJpa = fileRepositoryJpa;
        this.modelMapper = modelMapper;
    }


    //region MyGenericService
    @Override
    @SneakyThrows
    public FileDto findByIdSrv(Long id)
    {
        validationModelId(id);
        FileEnt ent = fileRepositoryJpa.findById(id).orElseThrow(NotFoundExc::new);
        return modelMapper.map(ent , FileDto.class);
    }

    @Override
    public List<FileDto> findAllSrv() {
        return fileRepositoryJpa.findAll()
                .stream()
                .map(x->modelMapper.map(x, FileDto.class))
                .toList();
    }

    @Override
    public Page<FileDto> findAllSrv(Integer page, Integer size) {
        return fileRepositoryJpa.findAll(Pageable.ofSize(size).withPage(page))
                .map(x->modelMapper.map(x, FileDto.class));
    }


    @Override
    public FileDto addSrv(FileDto dto) throws MyExc {
        validateDto(dto,false);
        FileEnt fileEnt = modelMapper.map(dto, FileEnt.class);
        return modelMapper.map(fileRepositoryJpa.save(fileEnt), FileDto.class);
    }

    @Override
    public Boolean deleteByIdSrv(Long id) {
        validationModelId(id);
        fileRepositoryJpa.deleteById(id);
        return true;
    }

    @Override
    public FileDto updateSrv(FileDto dto) throws MyExc {
        validateDto(dto,true);
        FileEnt fileEnt = modelMapper.map(dto, FileEnt.class);
        FileEnt fileEntDb = fileRepositoryJpa.findById(dto.getId()).orElseThrow();
        //
        fileEntDb.setPath(fileEnt.getPath());
        fileEntDb.setName(fileEnt.getName());
        //
        return modelMapper.map(fileRepositoryJpa.save(fileEnt) ,  FileDto.class);
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
    public void validateDto(FileDto fileDto, Boolean checkId) throws MyExc {
        if(fileDto.getPath()==null || fileDto.getPath().isEmpty())
            throw new MyExc("empty filename");
        if(fileDto.getName()==null || fileDto.getName().isEmpty())
            throw new MyExc("empty customer firstname");
    }
    //endregion
    //endregion






    public FileDto uploadSrv(FileDto dto) throws MyExc {
        FileDto resDto = null;
        try{
            FileEnt ent = FileEnt
                    .builder()
                    .createDate(LocalDateTime.now())//not in dto
                    .name(dto.getName())
                    .extension(dto.getExtension())
                    .path(dto.getPath())
                    .uuid(UUID.randomUUID().toString())
                    .size(dto.getSize())
                    .contentType(dto.getContentType())
                    .build();
            ent = fileRepositoryJpa.save(ent);
            resDto = modelMapper.map(ent, FileDto.class);
        } catch (Exception e) {
            System.out.println("couldn't save file...");
        }
        //
        return resDto;
    }


    public FileDto findByPathSrv(String path) {
        System.out.println("findByPathSrv : "+path);
        FileDto fileDto = null;
        try {
            FileEnt ent = fileRepositoryJpa.findFirstByPathEqualsIgnoreCase(path).orElseThrow();
            fileDto = modelMapper.map(ent, FileDto.class);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        return fileDto;
    }
}
