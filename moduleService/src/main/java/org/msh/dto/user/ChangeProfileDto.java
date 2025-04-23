package org.msh.dto.user;

import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChangeProfileDto {

    private String mobile;
    private String email;


    private String firstName;
    private String lastName;
    private String tel;
    private String address;
    private String postalCode;

}
