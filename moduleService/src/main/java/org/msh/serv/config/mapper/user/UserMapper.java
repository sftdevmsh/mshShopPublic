package org.msh.serv.config.mapper.user;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.msh.serv.dto.user.LimitedUserDto;
import org.msh.serv.dto.user.RoleDto;
import org.msh.serv.dto.user.UserDto;
import org.msh.repo.entity.user.RoleEnt;
import org.msh.repo.entity.user.UserEnt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class UserMapper {
    private final ModelMapper modelMapper;
    private final RoleMapper roleMapper;

    @Autowired
    public UserMapper(ModelMapper modelMapper
                      ,RoleMapper roleMapper
                        ) {
        this.modelMapper = modelMapper;
        this.roleMapper = roleMapper;

        this.modelMapper.addMappings(new PropertyMap<UserEnt, UserDto>() {
            //
            @Override
            protected void configure() {
                using(converterToRoleDto()).map(source.getRoleEnts()).setRoleDtos(null);
            }
        });
    }


    private Converter<Set<RoleEnt>,Set<RoleDto>> converterToRoleDto() {
        //
        return c ->  c.getSource() == null ? null :
                            c.getSource()
                                    .stream()
                                    .map(roleMapper::map)
                                    .collect(Collectors.toSet());
    }


    public UserEnt map(UserDto dto)
    {
        return this.modelMapper.map(dto, UserEnt.class);
    }
    public UserDto map(UserEnt ent)
    {
        return this.modelMapper.map(ent, UserDto.class);
    }
    //
    public LimitedUserDto limitedMap(UserEnt ent)
    {
        return this.modelMapper.map(ent, LimitedUserDto.class);
    }

}
