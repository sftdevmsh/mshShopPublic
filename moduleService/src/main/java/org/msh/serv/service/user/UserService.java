package org.msh.serv.service.user;

import lombok.SneakyThrows;
import org.msh.serv.dto.user.*;
import org.modelmapper.ModelMapper;
import org.msh.serv.dto.payment.GotoPaymentDto;
import org.msh.repo.entity.user.CustomerEnt;
import org.msh.repo.entity.user.RoleEnt;
import org.msh.common.exceptions.MyExc;
import org.msh.common.exceptions.NotFoundExc;
import org.msh.serv.config.mapper.user.UserMapper;
import org.msh.repo.entity.user.UserEnt;
import org.msh.repo.repositoryJpa.user.CustomerRepositoryJpa;
import org.msh.repo.repositoryJpa.user.PermissionRepositoryJpa;
import org.msh.repo.repositoryJpa.user.RoleRepositoryJpa;
import org.msh.repo.repositoryJpa.user.UserRepositoryJpa;
import org.msh.serv.dto.user.*;
import org.msh.serv.service.generics.MyGenericService;
import org.msh.serv.service.generics.MyGenericServiceCls;
import org.msh.common.util.MyHashUtil;
import org.msh.serv.util.MyJwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
public class UserService extends MyGenericServiceCls implements MyGenericService<UserDto> {

    private final UserRepositoryJpa userRepositoryJpa;
    private final CustomerRepositoryJpa customerRepositoryJpa;
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
        this.roleRepositoryJpa = roleRepositoryJpa;
        this.userMapper = userMapper;
        this.myJwtUtil = myJwtUtil;
        this.modelMapper = modelMapper;
    }


    //region MygenericService
    @Override
    @SneakyThrows
    public UserDto findByIdSrv(Long id)
    {
        validationModelId(id);
        UserEnt ent = userRepositoryJpa.findById(id).orElseThrow(NotFoundExc::new);
        return userMapper.map(ent);
    }

    @Override
    public List<UserDto> findAllSrv() {
        return userRepositoryJpa.findAll()
                .stream()
                .map(x->modelMapper.map(x, UserDto.class))
                .toList();
    }

    @Override
    public Page<UserDto> findAllSrv(Integer page, Integer size) {
        page = validatePage(page);
        size = validateSize(size);
        //
        return userRepositoryJpa.findAll(Pageable.ofSize(size).withPage(page))
                .map(x->modelMapper.map(x, UserDto.class));
    }


    @Override
    public UserDto addSrv(UserDto dto) throws MyExc {
        validateDto(dto,false);
        UserEnt userEnt = modelMapper.map(dto, UserEnt.class);
        return modelMapper.map(userRepositoryJpa.save(userEnt), UserDto.class);
    }

    @Override
    public Boolean deleteByIdSrv(Long id) {
        validationModelId(id);
        userRepositoryJpa.deleteById(id);
        return true;
    }

    @Override
    public UserDto updateSrv(UserDto dto) throws MyExc {
        validateDto(dto,true);
        UserEnt userEnt = userMapper.map(dto);
        UserEnt userEntDb = userRepositoryJpa.findById(dto.getId()).orElseThrow();
        //
        userEntDb.setCustomerEnt(userEnt.getCustomerEnt());
        userEntDb.setRoleEnts(userEnt.getRoleEnts());
        userEntDb.setRegisterTime(userEnt.getRegisterTime());
        //todo: update email, or mobile  with another process, after verification
        //userEntDb.setEmail(userEnt.getEmail());
        //userEntDb.setEnabled(dto.getEnabled());
        //
        return userMapper.map(userRepositoryJpa.save(userEnt));
    }

    //region private methods for validation
    @SneakyThrows
    @Override
    public void validationModelId(Long id)
    {
        if(id == null || id<=0)
            throw new Exception("Error! validationModelId _ wrong id");
    }

    @Override
    public void validateDto(UserDto userDto, Boolean checkId) throws MyExc {
        if(userDto.getUsername()==null || userDto.getUsername().isEmpty())
            throw new MyExc("empty username");
        if(userDto.getCustomerDto()==null)
            throw new MyExc("empty customer");
        if(userDto.getCustomerDto().getFirstName()==null || userDto.getCustomerDto().getFirstName().isEmpty())
            throw new MyExc("empty customer firstname");
        if(checkId && (userDto.getId() == null || userDto.getId()<1))
            throw new MyExc("id validation error");
        validationUserPassword(userDto.getPassword());
    }
    //endregion
    //endregion






    public void validationUserPassword(String pass) throws MyExc {
        if(pass == null || pass.isEmpty())
            throw new MyExc("password is empty");
        if(pass.length()<8)
            throw new MyExc("password length is small");
        //
        /*
            ^                 # start-of-string
            (?=.*[0-9])       # a digit must occur at least once
            (?=.*[a-z])       # a lower case letter must occur at least once
            (?=.*[A-Z])       # an upper case letter must occur at least once
            (?=.*[@#$%^&+=])  # a special character must occur at least once
            (?=\S+$)          # no whitespace allowed in the entire string
            .{8,}             # anything, at least eight places though
            $                 # end-of-string
        */
        String pattern = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}";
        if(!pass.matches(pattern))
            throw new MyExc("password length is small");
    }

    public LimitedUserDto login(LoginDto ld) throws NotFoundExc {
        String username = ld.getUsername();
        String password = MyHashUtil.encrypt(ld.getPassword());
        System.out.println("login pass id : "+password);
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
        return userMapper.map(userEnt);
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


    //Note: @Transactional in paymentController
    public UserEnt saveAndCreate(GotoPaymentDto gotoPaymentDto) throws MyExc {
        //todo: complete validation
        //
        Optional<UserEnt> oue = userRepositoryJpa
                .findByUsernameIgnoreCaseQry(gotoPaymentDto.getUsername());
        if(oue.isPresent())
        {
            throw new MyExc("user already exists");
        }

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
        validateDto(userDto,false);
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
        return ue;
    }


    public UserDto changePassByAdminSrv(UserDto dto) throws MyExc {
        //
        validateDto(dto,true);//includes id and pass validation
        //
        UserEnt dbEnt = userRepositoryJpa.findById(dto.getId()).orElseThrow();
        //
        String newPass = MyHashUtil.encrypt(dto.getPassword());
        dbEnt.setPassword(newPass);
        //
        return userMapper.map(userRepositoryJpa.save(dbEnt));
    }


    public UserDto changePassByUserSrv(UserDto dto , ChangePassDto cpDto) throws MyExc {
        if(!cpDto.getNewPass().equals(cpDto.getNewPass2()))
            throw new MyExc("incorrect new password match");
        //
        validationUserPassword(cpDto.getNewPass());
        //
        validationModelId(dto.getId());
        UserEnt dbEnt = userRepositoryJpa.findById(dto.getId()).orElseThrow();
        //
        String oldPass = MyHashUtil.encrypt(cpDto.getOldPass());
        if(!oldPass.equals(dbEnt.getPassword()))
            throw new MyExc("incorrect old password");
        //
        String newPass = MyHashUtil.encrypt(cpDto.getNewPass());
        dbEnt.setPassword(newPass);
        //
        return userMapper.map(userRepositoryJpa.save(dbEnt));
    }

    public UserDto changeProfileSrv(UserDto dto , ChangeProfileDto cpDto) throws MyExc {
        validationModelId(dto.getId());
        UserEnt dbEnt = userRepositoryJpa.findById(dto.getId()).orElseThrow();
        //
        dbEnt.setEmail(Optional.ofNullable(cpDto.getEmail()).orElse(dbEnt.getEmail()));
        dbEnt.setMobile(Optional.ofNullable(cpDto.getMobile()).orElse(dbEnt.getMobile()));
        dbEnt.getCustomerEnt()
                .setFirstName(Optional.ofNullable(cpDto.getFirstName())
                .orElse(dbEnt.getCustomerEnt().getFirstName()));
        dbEnt.getCustomerEnt()
                .setLastName(Optional.ofNullable(cpDto.getLastName())
                .orElse(dbEnt.getCustomerEnt().getLastName()));
        dbEnt.getCustomerEnt()
                .setPostalCode(Optional.ofNullable(cpDto.getPostalCode())
                .orElse(dbEnt.getCustomerEnt().getPostalCode()));
        dbEnt.getCustomerEnt()
                .setAddress(Optional.ofNullable(cpDto.getAddress())
                .orElse(dbEnt.getCustomerEnt().getAddress()));
        dbEnt.getCustomerEnt()
                .setTel(Optional.ofNullable(cpDto.getTel())
                .orElse(dbEnt.getCustomerEnt().getTel()));
        //
        return userMapper.map(userRepositoryJpa.save(dbEnt));
    }

    //todo: validate email,mobile , ... before doing update/save

}
