package org.msh.serv.dto.user;

import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {

    private Long Id;

    private String username;
    private String password;

    private String mobile;
    private String email;

    private LocalDateTime registerTime;
    private Boolean enabled;

    private Set<RoleDto> roleDtos;

    //private String jwtToken;

    //private String firstName;
    //private String lastName;
    private CustomerDto customerDto;

}
