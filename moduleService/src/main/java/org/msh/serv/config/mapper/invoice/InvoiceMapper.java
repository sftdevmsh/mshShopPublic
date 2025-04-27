package org.msh.serv.config.mapper.invoice;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.msh.serv.dto.invoice.InvoiceDto;
import org.msh.serv.dto.invoice.InvoiceItemDto;
import org.msh.repo.entity.invoice.InvoiceEnt;
import org.msh.repo.entity.invoice.InvoiceItemEnt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class InvoiceMapper {
    private final ModelMapper modelMapper;

    @Autowired
    public InvoiceMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;

        this.modelMapper.addMappings(new PropertyMap<InvoiceEnt, InvoiceDto>() {
            //
            @Override
            protected void configure() {
                using(converterToInvoiceItemDtos()).map(source.getInvoiceItemEnts()).setInvoiceItemDtos(null);
            }
        });
    }

    private Converter<Set<InvoiceItemEnt>, Set<InvoiceItemDto>> converterToInvoiceItemDtos() {
        return c ->  c.getSource() == null ? null :
                c.getSource().stream().map(x->
                        modelMapper.map(x, InvoiceItemDto.class)
                ).collect(Collectors.toSet());
    }




    public InvoiceDto map(InvoiceEnt ent)
    {
        return this.modelMapper.map(ent, InvoiceDto.class);
    }

    public InvoiceEnt map(InvoiceDto dto)
    {
        return this.modelMapper.map(dto, InvoiceEnt.class);
    }

//    public ProductCategoryDto map(ProductCategoryEnt ent) {
//        return  this.modelMapper.map(ent,ProductCategoryDto.class);
//    }
}
