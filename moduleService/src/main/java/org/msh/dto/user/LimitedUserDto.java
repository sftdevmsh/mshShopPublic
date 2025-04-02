package org.msh.dto.user;

import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

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
}
