package org.msh.dto.user;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChangePassDto {

    //private Long Id;

    private String oldPass;

    private String newPass;

    private String newPass2;
}
