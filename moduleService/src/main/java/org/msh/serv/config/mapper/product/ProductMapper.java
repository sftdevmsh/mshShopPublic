package org.msh.serv.config.mapper.product;

import org.msh.serv.dto.product.ColorDto;
import org.msh.serv.dto.product.ProductDto;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.msh.serv.dto.product.*;
import org.msh.serv.dto.product.SizeDto;
import org.msh.repo.entity.product.ColorEnt;
import org.msh.repo.entity.product.ProductEnt;
import org.msh.repo.entity.product.SizeEnt;
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
                //
                //
                //if (source != null) {
                //    List<CustomProductColor> lst = source.getPcs();
                //    List<Color> sizes = lst.stream().map(cpc -> cpc.getColor()).toList();
                //    List<ColorDto> sizeDtos = sizes.stream().map(c ->
                //            {
                //                c.setHex("manipulated");
                //                return modelMapper.map(c, ColorDto.class);
                //            }
                //    ).toList();
                //    //
                //    source.setPcs(null);
                //    ProductDto res = modelMapper.map(source, ProductDto.class);
                //    res.setColorDtos(sizeDtos);
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




    public ProductDto map(ProductEnt product)
    {
        return this.modelMapper.map(product, ProductDto.class);
    }

    public ProductEnt map(ProductDto productDto)
    {
        return this.modelMapper.map(productDto, ProductEnt.class);
    }

//    public ProductCategoryDto map(ProductCategoryEnt ent) {
//        return  this.modelMapper.map(ent,ProductCategoryDto.class);
//    }
}
