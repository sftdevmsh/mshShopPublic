package org.msh.serv.config.mapper.user;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.msh.serv.dto.user.PermissionDto;
import org.msh.serv.dto.user.RoleDto;
import org.msh.repo.entity.user.PermissionEnt;
import org.msh.repo.entity.user.RoleEnt;
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
                using(converterToPermissionDto()).map(source.getPermissionEnts()).setPermissionDtos(null);
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
