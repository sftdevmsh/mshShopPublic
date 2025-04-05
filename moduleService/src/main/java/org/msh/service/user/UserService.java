package org.msh.service.user;

import lombok.SneakyThrows;
import org.msh.exceptions.NotFoundExc;
import org.msh.config.mapper.user.UserMapper;
import org.msh.dto.user.LimitedUserDto;
import org.msh.dto.user.LoginDto;
import org.msh.dto.user.UserDto;
import org.msh.entity.user.UserEnt;
import org.msh.repositoryJpa.user.PermissionRepositoryJpa;
import org.msh.repositoryJpa.user.RoleRepositoryJpa;
import org.msh.repositoryJpa.user.UserRepositoryJpa;
import org.msh.util.HashUtil;
import org.msh.util.MyJwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepositoryJpa userRepositoryJpa;
    private final PermissionRepositoryJpa permissionRepositoryJpa;
    private final RoleRepositoryJpa roleRepositoryJpa;
    private final UserMapper userMapper;
    private final MyJwtUtil myJwtUtil;


    @Autowired
    public UserService(UserRepositoryJpa userRepositoryJpa
                        , PermissionRepositoryJpa permissionRepositoryJpa
                        , RoleRepositoryJpa roleRepositoryJpa
                       , UserMapper userMapper, MyJwtUtil myJwtUtil
    ) {
        this.userRepositoryJpa = userRepositoryJpa;
        this.permissionRepositoryJpa = permissionRepositoryJpa;
        this.roleRepositoryJpa = roleRepositoryJpa;
        this.userMapper = userMapper;
        this.myJwtUtil = myJwtUtil;
    }


    public LimitedUserDto login(LoginDto dto) throws NotFoundExc {
        String username = dto.getUsername();
        String password = HashUtil.encrypt(dto.getPassword());
        UserEnt userEnt = userRepositoryJpa.findByUsernameIgnoreCaseAndPassword(username,password).orElseThrow(NotFoundExc::new);
        //
        LimitedUserDto limitedUserDto = userMapper.limitedMap(userEnt);
        //
        String t = myJwtUtil.generateToken(username);
        System.out.println("toke is : " + t);
        limitedUserDto.setJwtToken(t);
        System.out.println(myJwtUtil.getUsernameFromJwtToken(t));
        //
        return limitedUserDto;
    }


    public UserDto loginGetAllInfo(LoginDto dto) throws NotFoundExc {
        String username = dto.getUsername();
        String password = HashUtil.encrypt(dto.getPassword());
        UserEnt userEnt = userRepositoryJpa.findByUsernameIgnoreCaseAndPassword(username,password).orElseThrow(NotFoundExc::new);
        UserDto userDto = userMapper.map(userEnt);
        return userDto;
    }

    public LimitedUserDto findByUsernameLimited(String username) {
        LimitedUserDto dto = null;
        try {
//            UserEnt ent = userRepositoryJpa.findByUsernameIgnoreCase(username).orElseThrow(NotFoundExc::new);
            UserEnt ent = userRepositoryJpa.findByUsernameIgnoreCaseQry(username).orElseThrow(NotFoundExc::new);
             dto = userMapper.limitedMap(ent);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        return dto;
    }
    public UserDto findByUsername(String username) {
        UserDto dto = null;
        try {
//            UserEnt ent = userRepositoryJpa.findByUsernameIgnoreCase(username).orElseThrow(NotFoundExc::new);
            UserEnt ent = userRepositoryJpa.findByUsernameIgnoreCaseQry(username).orElseThrow(NotFoundExc::new);
            dto = userMapper.map(ent);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        return dto;
    }


    @SneakyThrows
    public UserDto findById(Long id)
    {
        UserEnt ent = userRepositoryJpa.findById(id).orElseThrow(NotFoundExc::new);
        return userMapper.map(ent);
    }

}
