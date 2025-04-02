package org.msh.config.mapper;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.msh.dto.user.PermissionDto;
import org.msh.dto.user.RoleDto;
import org.msh.entity.user.PermissionEnt;
import org.msh.entity.user.RoleEnt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class RoleMapper {
    private final ModelMapper modelMapper;

    @Autowired
    public RoleMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;

        this.modelMapper.addMappings(new PropertyMap<RoleEnt, RoleDto>() {
            //
            @Override
            protected void configure() {
                using(converterToPermissionDto()).map(source.getSetPermissionEnt()).setSettPermissionDto(null);
            }
        });
    }


    private Converter<Set<PermissionEnt>,Set<PermissionDto>> converterToPermissionDto() {
        //
        return c ->  c.getSource() == null ? null :
                            c.getSource()
                                    .stream()
                                    .map(x->modelMapper.map(x,PermissionDto.class))
                                    .collect(Collectors.toSet());
    }


    public RoleEnt map(RoleDto dto)
    {
        return this.modelMapper.map(dto, RoleEnt.class);
    }
    public RoleDto map(RoleEnt ent)
    {
        return this.modelMapper.map(ent, RoleDto.class);
    }

}
