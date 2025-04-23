package org.msh.controller.panel.file;

import org.msh.config.annotation.MyAutenticationAnnotation;
import org.msh.controller.panel.myGenerics.MyGenericController;
import org.msh.dto.file.FileDto;
import org.msh.enums.MyHttpStatus;
import org.msh.exceptions.MyExc;
import org.msh.service.file.FileService;
import org.msh.wrapper.PanelApiResponseWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/panel/file")
public class FilePanelController implements MyGenericController<FileDto> {
    private final FileService fileService;

    @Autowired
    public FilePanelController(FileService fileService) {
        this.fileService = fileService;
    }


    @MyAutenticationAnnotation("file_lst , file_inf")
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
    public PanelApiResponseWrapper<Boolean> deleteByIdCtrl(Long id) {
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
        return PanelApiResponseWrapper
                .<FileDto>builder()
                .tdata(fileService.upload(file))
                .msg("")
                .status(MyHttpStatus.Success)
                .build();
    }

}
