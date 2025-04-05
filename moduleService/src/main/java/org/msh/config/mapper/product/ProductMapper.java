package org.msh.config.mapper.product;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.msh.dto.file.FileDto;
import org.msh.dto.product.ColorDto;
import org.msh.dto.product.ProductDto;
import org.msh.dto.product.SizeDto;
import org.msh.entity.file.FileEnt;
import org.msh.entity.product.ColorEnt;
import org.msh.entity.product.ProductEnt;
import org.msh.entity.product.SizeEnt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ProductMapper {
    private final ModelMapper modelMapper;

    @Autowired
    public ProductMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;

        this.modelMapper.addMappings(new PropertyMap<ProductEnt, ProductDto>() {
            //
            @Override
            protected void configure() {
                using(converterToColorDtos()).map(source.getColorEnts()).setColorDtos(null);
                using(converterToSizeDtos()).map(source.getSizeEnts()).setSizeDtos(null);
                using(converterToFileDto()).map(source.getImg()).setImg(null);
                //
                //
                //if (source != null) {
                //    List<CustomProductColor> lst = source.getPcs();
                //    List<Color> colors = lst.stream().map(cpc -> cpc.getColor()).toList();
                //    List<ColorDto> colorDtos = colors.stream().map(c ->
                //            {
                //                c.setHex("manipulated");
                //                return modelMapper.map(c, ColorDto.class);
                //            }
                //    ).toList();
                //    //
                //    source.setPcs(null);
                //    ProductDto res = modelMapper.map(source, ProductDto.class);
                //    res.setColorDtos(colorDtos);
                //}
            }
        });
    }

    private Converter<Set<ColorEnt>, Set<ColorDto>> converterToColorDtos() {
        return c ->  c.getSource() == null ? null :
                c.getSource().stream().map(x->
                        modelMapper.map(x, ColorDto.class)
                ).collect(Collectors.toSet());
    }

    private Converter<Set<SizeEnt>, Set<SizeDto>> converterToSizeDtos() {
        return c ->  c.getSource() == null ? null :
                c.getSource().stream().map(x->
                        modelMapper.map(x, SizeDto.class)
                ).collect(Collectors.toSet());
    }

    private Converter<FileEnt, FileDto> converterToFileDto() {
        return c ->  c.getSource() == null ? null :
                        modelMapper.map(c.getSource(), FileDto.class)
                ;
    }


    public ProductDto map(ProductEnt product)
    {
        return this.modelMapper.map(product, ProductDto.class);
    }

    public ProductEnt map(ProductDto productDto)
    {
        return this.modelMapper.map(productDto, ProductEnt.class);
    }
}
