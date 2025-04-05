package org.msh.service.file;

import org.modelmapper.ModelMapper;
import org.msh.repositoryJpa.file.FileRepositoryJpa;
import org.msh.repositoryJpa.payment.PaymentRepositoryJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FileService {

    private final FileRepositoryJpa fileRepositoryJpa;
    private final ModelMapper modelMapper;

    @Autowired
    public FileService(FileRepositoryJpa fileRepositoryJpa, ModelMapper modelMapper)
    {
        this.fileRepositoryJpa = fileRepositoryJpa;
        this.modelMapper = modelMapper;
    }
}
