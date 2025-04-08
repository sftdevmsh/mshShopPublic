package org.msh.service.user;

import lombok.SneakyThrows;
import org.modelmapper.ModelMapper;
import org.msh.dto.payment.GotoPaymentDto;
import org.msh.dto.user.CustomerDto;
import org.msh.entity.user.CustomerEnt;
import org.msh.entity.user.RoleEnt;
import org.msh.exceptions.MyExc;
import org.msh.exceptions.NotFoundExc;
import org.msh.config.mapper.user.UserMapper;
import org.msh.dto.user.LimitedUserDto;
import org.msh.dto.user.LoginDto;
import org.msh.dto.user.UserDto;
import org.msh.entity.user.UserEnt;
import org.msh.repositoryJpa.user.CustomerRepositoryJpa;
import org.msh.repositoryJpa.user.PermissionRepositoryJpa;
import org.msh.repositoryJpa.user.RoleRepositoryJpa;
import org.msh.repositoryJpa.user.UserRepositoryJpa;
import org.msh.util.MyHashUtil;
import org.msh.util.MyJwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepositoryJpa userRepositoryJpa;
    private final CustomerRepositoryJpa customerRepositoryJpa;
    private final PermissionRepositoryJpa permissionRepositoryJpa;
    private final RoleRepositoryJpa roleRepositoryJpa;
    private final UserMapper userMapper;
    private final MyJwtUtil myJwtUtil;
    private final ModelMapper modelMapper;


    @Autowired
    public UserService(UserRepositoryJpa userRepositoryJpa
            , CustomerRepositoryJpa customerRepositoryJpa
            , PermissionRepositoryJpa permissionRepositoryJpa
            , RoleRepositoryJpa roleRepositoryJpa
            , UserMapper userMapper
            , MyJwtUtil myJwtUtil,
                       ModelMapper modelMapper) {
        this.userRepositoryJpa = userRepositoryJpa;
        this.customerRepositoryJpa = customerRepositoryJpa;
        this.permissionRepositoryJpa = permissionRepositoryJpa;
        this.roleRepositoryJpa = roleRepositoryJpa;
        this.userMapper = userMapper;
        this.myJwtUtil = myJwtUtil;
        this.modelMapper = modelMapper;
    }


    public LimitedUserDto login(LoginDto dto) throws NotFoundExc {
        String username = dto.getUsername();
        String password = MyHashUtil.encrypt(dto.getPassword());
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
        String password = MyHashUtil.encrypt(dto.getPassword());
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


    //Note: @Transactional in paymentController
    public UserEnt saveAndCreate(GotoPaymentDto gotoPaymentDto) throws MyExc {
        UserDto userDto = UserDto
                .builder()
                .username(gotoPaymentDto.getUsername())
                .mobile(gotoPaymentDto.getMobile())
                .password(gotoPaymentDto.getPassword())
                .email(gotoPaymentDto.getEmail())
                //.enabled(true)
                .customerDto(CustomerDto
                        .builder()
                        .firstName(gotoPaymentDto.getFirstname())
                        .lastName(gotoPaymentDto.getLastname())
                        .tel(gotoPaymentDto.getTel())
                        .address(gotoPaymentDto.getAddress())
                        .postalCode(gotoPaymentDto.getPostalCode())
                        .build())
                .build();
        //
        //todo: complete validation
        validate(userDto);
        //
        CustomerEnt customerEnt = customerRepositoryJpa.save(
                modelMapper.map(userDto.getCustomerDto(), CustomerEnt.class)
        );
        //
        UserEnt ue = modelMapper.map(userDto, UserEnt.class);
        //
        //make changes to ue::::
        //
        ue.setCustomerEnt(customerEnt);//customer with id
        //
        Optional<RoleEnt> re = roleRepositoryJpa.findFirstByNameEqualsIgnoreCase("user");
        if(re.isPresent())
        {
            HashSet<RoleEnt> roleEnts = new HashSet<>();
            roleEnts.add(re.get());
            ue.setRoleEnts(roleEnts);
        }
        //
        ue.setPassword(MyHashUtil.encrypt(userDto.getPassword()));
        //
        ue.setRegisterTime(LocalDateTime.now());
        //
        ue.setEnabled(true);
        //
        ue = userRepositoryJpa.save(ue);// id after saving
        //
//        return userMapper.map(ue);
        return ue;
    }
    //
    private static void validate(UserDto userDto) throws MyExc {
        if(userDto.getUsername()==null || userDto.getUsername().isEmpty())
            throw new MyExc("empty username");
        if(userDto.getCustomerDto()==null)
            throw new MyExc("empty customer");
        if(userDto.getCustomerDto().getFirstName()==null || userDto.getCustomerDto().getFirstName().isEmpty())
            throw new MyExc("empty customer firstname");
    }

}
