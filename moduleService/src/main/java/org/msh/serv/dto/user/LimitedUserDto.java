package org.msh.serv.dto.user;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LimitedUserDto {

    private Long Id;

    private String username;

    private String firstName;
    private String lastName;

    private String jwtToken;


    private String oldPass;

    private String newPass;


}
