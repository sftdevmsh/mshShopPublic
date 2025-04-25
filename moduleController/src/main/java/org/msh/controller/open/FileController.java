package org.msh.controller.open;

import org.msh.config.annotation.MyAutenticationAnnotation;
import org.msh.controller.panel.myGenerics.MyGenericController;
import org.msh.dto.file.FileDto;
import org.msh.enums.MyHttpStatus;
import org.msh.exceptions.MyExc;
import org.msh.service.file.FileService;
import org.msh.wrapper.PanelApiResponseWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/panel/file")
public class FileController {
    private final FileService fileService;

    @Autowired
    public FileController(FileService fileService) {
        this.fileService = fileService;
    }




//    @MyAutenticationAnnotation("file_inf")
//    @GetMapping("/get/name/{name}")
//    public ResponseEntity<FileDto> findByNameCtrl(@PathVariable("name") String name) {
//        FileDto dto = fileService.findByNameSrv(name);
//        return ResponseEntity
//                .<FileDto>builder()
//                .tdata()
//                .msg("")
//                .status(MyHttpStatus.Success)
//                .build();
//    }




}
