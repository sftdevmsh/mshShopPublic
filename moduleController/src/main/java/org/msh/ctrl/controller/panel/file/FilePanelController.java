package org.msh.ctrl.controller.panel.file;

import org.msh.ctrl.config.annotation.MyAutenticationAnnotation;
import org.msh.ctrl.controller.panel.myGenerics.MyGenericController;
import org.msh.serv.dto.file.FileDto;
import org.msh.ctrl.enums.MyHttpStatus;
import org.msh.common.exceptions.MyExc;
import org.msh.serv.service.file.FileService;
import org.msh.ctrl.wrapper.PanelApiResponseWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@RestController
@RequestMapping("/api/panel/file")
public class FilePanelController implements MyGenericController<FileDto> {

    @Value("${my.file.upload.path}")
    public String uploadPath;

    private final FileService fileService;


    @Autowired
    public FilePanelController(FileService fileService) {
        this.fileService = fileService;
    }


    @MyAutenticationAnnotation("file_inf")
    @Override
    public PanelApiResponseWrapper<FileDto> findByIdCtrl(Long id) {
        return PanelApiResponseWrapper
                .<FileDto>builder()
                .tdata(fileService.findByIdSrv(id))
                .msg("")
                .status(MyHttpStatus.Success)
                .build();
    }

    @MyAutenticationAnnotation("file_lst")
    @Override
    public PanelApiResponseWrapper<List<FileDto>> findAllCtrl(Integer page, Integer size) {
        {
            PanelApiResponseWrapper<List<FileDto>> res;
            try {
                Page<FileDto> data = fileService.findAllSrv(page,size);
                res = PanelApiResponseWrapper
                        .<List<FileDto>>builder()
                        .tdata(data.toList())
                        .msg("")
                        .status(MyHttpStatus.Success)
                        .totalCount((int) data.getTotalElements())
                        .totalCount(data.getTotalPages())
                        .build();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                res = PanelApiResponseWrapper
                        .<List<FileDto>>builder()
                        .tdata(null)
                        .msg(e.getMessage())
                        .status(MyHttpStatus.Failed)
                        .build();
            }
            return res;
        }
    }


    @MyAutenticationAnnotation("file_del")
    @Override
    public PanelApiResponseWrapper<Boolean> deleteByIdCtrl(@PathVariable("id") Long id) {
        return PanelApiResponseWrapper
                .<Boolean>builder()
                .tdata(fileService.deleteByIdSrv(id))
                .msg("")
                .status(MyHttpStatus.Success)
                .build();
    }

    //todo: use upload instead
    @MyAutenticationAnnotation("file_add")
    @Override
    public PanelApiResponseWrapper<FileDto> addCtrl(FileDto fileDto) throws MyExc {
        return PanelApiResponseWrapper
                .<FileDto>builder()
                .tdata(null)
                .msg("not applicable, upload instead")
                .status(MyHttpStatus.Success)
                .build();
    }

    @MyAutenticationAnnotation("file_upd")
    @Override
    public PanelApiResponseWrapper<FileDto> updateCtrl(FileDto fileDto) throws MyExc {
        return PanelApiResponseWrapper
                .<FileDto>builder()
                .tdata(fileService.updateSrv(fileDto))
                .msg("")
                .status(MyHttpStatus.Success)
                .build();
    }







    //todo: implement upload
    @MyAutenticationAnnotation("file_upl")
    @PostMapping("/upload")
    public PanelApiResponseWrapper<FileDto> uploadCtrl(@RequestParam("file") MultipartFile file) throws MyExc {
        //
        FileDto resDto = null;
        //
        if(file == null
                || file.isEmpty()
                || file.getOriginalFilename() == null
                || file.getOriginalFilename().isEmpty()) {
            System.out.println("can not upload a null file...");
            throw new MyExc("can not upload a null file...");
        }
        else {
            try {
                System.out.println("file.getName() : " +file.getName());
                System.out.println("file.getOriginalFilename() : "+ file.getOriginalFilename());
                //
                String fileName = Objects.requireNonNull(file.getOriginalFilename())
                        .substring(0, file.getOriginalFilename().lastIndexOf("."));
                String extensionName = file.getOriginalFilename()
                        .substring(file.getOriginalFilename().lastIndexOf(".") + 1);
                //
                String path = fileName + "." + extensionName;
                path = getChangedFileName(path);
                //
                String uploadPath = getUploadPath(path);
                Path savePath = Paths.get(uploadPath);
                //
                java.nio.file.Files.write(savePath, file.getBytes());
                //
                FileDto dto = FileDto
                        .builder()
                        .name(fileName)
                        .extension(extensionName)
                        .path(path)
                        .contentType(file.getContentType())
                        .uuid(UUID.randomUUID().toString())
                        .size(file.getSize())
                        .build();
                //
                resDto = fileService.uploadSrv(dto);
            } catch (Exception e) {
                System.out.println("couldn't save file...");
            }
        }
        //
        return PanelApiResponseWrapper
                .<FileDto>builder()
                .tdata(resDto)
                .msg("")
                .status(MyHttpStatus.Success)
                .build();
    }


    public String getChangedFileName(String fullName) {
        //todo: change fullName appropriately
        return "AA"+fullName;
    }


    public String getUploadPath(String path) {
        System.out.println("getUploadPath : "+uploadPath + File.separator + path);
        return uploadPath + File.separator + path;
    }


}
