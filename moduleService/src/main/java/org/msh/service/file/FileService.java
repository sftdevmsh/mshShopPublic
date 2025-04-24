package org.msh.service.file;

import lombok.SneakyThrows;
import org.modelmapper.ModelMapper;
import org.msh.dto.file.FileDto;
import org.msh.entity.file.FileEnt;
import org.msh.exceptions.MyExc;
import org.msh.exceptions.NotFoundExc;
import org.msh.repositoryJpa.file.FileRepositoryJpa;
import org.msh.service.generics.MyGenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class FileService implements MyGenericService<FileDto> {

    @Value("${my.file.upload.path}")
    private String uploadpath;

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



    public FileDto upload(MultipartFile file) throws MyExc {
        FileDto resDto = null;
        //
        if(file == null
                || file.isEmpty()
                || file.getOriginalFilename() == null
                || file.getOriginalFilename().isEmpty())
            throw new MyExc("can not upload a null file...");
        else {
            System.out.println("file.getName() : " +file.getName());
            System.out.println("file.getOriginalFilename() : "+ file.getOriginalFilename());
            //
            String fileName = Objects.requireNonNull(file.getOriginalFilename())
                    .substring(0, file.getOriginalFilename().lastIndexOf("."));
            String extensionName = file.getOriginalFilename()
                    .substring(file.getOriginalFilename().lastIndexOf(".") + 1);
            //
            String fullName = fileName + "." + extensionName;
            String fullNameStrToSave = changeFileNameStrToSave(fullName);
            String savePathStr = uploadpath + File.separator + fullNameStrToSave;
            Path savePath = Paths.get(savePathStr);
            //
            try {
                java.nio.file.Files.write(savePath, file.getBytes());
                FileEnt ent = FileEnt
                        .builder()
                        .createDate(LocalDateTime.now())
                        .name(fileName)
                        .extension(extensionName)
                        .path(fullName)
                        .uuid(UUID.randomUUID().toString())
                        .size(file.getSize())
                        .build();
                ent = fileRepositoryJpa.save(ent);
                resDto = modelMapper.map(ent, FileDto.class);
            } catch (IOException e) {
                System.out.println("couldn't save file...");
                ;
            }
        }
        //
        return resDto;
    }

    private String changeFileNameStrToSave(String fullName) {
        //todo: change fullNameStrToSave appropriately
        return "AA"+fullName;
    }

    public FileDto findByNameSrv(String name) {
        FileEnt ent = null;
        try {
            ent = fileRepositoryJpa.findFirstByNameEqualsIgnoreCase(name).orElseThrow();
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        return modelMapper.map(ent, FileDto.class);
    }
}
