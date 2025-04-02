package org.msh.config.mapper;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.msh.dto.product.ColorDto;
import org.msh.dto.product.ProductDto;
import org.msh.entity.product.RelProductColor;
import org.msh.entity.product.ProductEnt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

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
                using(converterToColors()).map(source.getLstRelProductColor()).setLstColorDto(null);
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

    private Converter<List<RelProductColor>,List<ColorDto>> converterToColors() {

        return c ->  c.getSource() == null ? null :
                            c.getSource().stream().map(x->
                                modelMapper.map(x.getColorEnt(), ColorDto.class)
                                ).toList()
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
