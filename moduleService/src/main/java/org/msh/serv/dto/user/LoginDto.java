package org.msh.serv.dto.user;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginDto {

    private String username;
    private String password;

}
