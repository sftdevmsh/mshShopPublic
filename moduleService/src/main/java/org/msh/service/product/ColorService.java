package org.msh.service.product;

import lombok.SneakyThrows;
import org.modelmapper.ModelMapper;
import org.msh.dto.product.ColorDto;
import org.msh.entity.product.ColorEnt;
import org.msh.repositoryJpa.product.ColorRepositoryJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ColorService {

    private final ColorRepositoryJpa colorRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public ColorService(ColorRepositoryJpa colorRepository, ModelMapper modelMapper)
    {
        this.colorRepository = colorRepository;
        this.modelMapper = modelMapper;
    }

    public List<ColorEnt> getAllSrv()
    {
//        return colorRepository.getAllcolor();
        return colorRepository.findAll();
    }

    public ColorEnt getByIdSrv(Long id)
    {
        validationModelId(id);
//        return colorRepository.getByIdcolor(id);
        return colorRepository.findById(id).orElseThrow();
    }

    public void deleteSrv(Long id)
    {
        validationModelId(id);
        colorRepository.deleteById(id);
    }

    public ColorDto addSrv(ColorDto dto)
    {
        validationModelDto(dto);
        dto.setId(null);
        ColorEnt color = modelMapper.map(dto, ColorEnt.class);
        return modelMapper.map(color, ColorDto.class);
    }

    public ColorDto updateSrv(ColorDto dto) {
        validationModelId(dto.getId());
        validationModelDto(dto);
        ColorEnt dtoDb = getByIdSrv(dto.getId());
        dtoDb.setColor(dto.getColor());
        dtoDb.setHex(dto.getHex());
        ColorEnt color = colorRepository.save(modelMapper.map(dtoDb, ColorEnt.class));
        return modelMapper.map(color, ColorDto.class); //return dtoDb;
    }



    //region private methods
    @SneakyThrows
    private void validationModelId(Long id)
    {
        if(id == null || id<=0)
            throw new Exception("Error! validationModelId _ wrong id");
    }
    @SneakyThrows
    private void validationModelDto(ColorDto dto)
    {
        if(dto == null)
            throw new Exception("Error! validationModelColor _ null color");
        if(dto.getColor() == null || dto.getColor().isEmpty())
            throw new Exception("Error! validationModelColor _ wrong color color");
        if(dto.getHex() == null || dto.getHex().isEmpty())
            throw new Exception("Error! validationModelColor _ wrong color hex");
    }
   //endregion
}
