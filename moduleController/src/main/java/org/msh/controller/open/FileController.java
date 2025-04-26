package org.msh.controller.open;

import org.msh.controller.panel.file.FilePanelController;
import org.msh.dto.file.FileDto;
import org.msh.exceptions.MyExc;
import org.msh.service.file.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Collections;


@RestController
@RequestMapping("/api/panel/file")
public class FileController {

    @Value("${my.file.upload.path}")
    public String uploadPath;

    private final FileService fileService;

    @Autowired
    public FileController(FileService fileService) {
        this.fileService = fileService;
    }




    @GetMapping("/get/name/{fullname}")
    public ResponseEntity<InputStreamResource> getByPathCtrl(@PathVariable("fullname") String fullname) throws MyExc, FileNotFoundException {
        try
        {
            String path = getChangedFileName(fullname);
            FileDto dto = fileService.findByPathSrv(path);
            if(dto==null || dto.getPath().isEmpty())
                throw new MyExc("no file with name : "+fullname);
            String uploadPath = getUploadPath(dto.getPath());
            File file = new File(uploadPath);
            //
            InputStream inputStream = new FileInputStream(file);
            InputStreamResource inputStreamResource = new InputStreamResource(inputStream);
            //
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setContentLength(dto.getSize());
            httpHeaders.setContentType(MediaType.parseMediaType(dto.getContentType())); //httpHeaders.setContentType(MediaType.MULTIPART_MIXED);
            //httpHeaders.setCacheControl(CacheControl.noCache());
            //
            return new ResponseEntity<>(inputStreamResource,httpHeaders, HttpStatus.OK);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            //throw new MyExc(e.getMessage());
            return null;
        }
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
