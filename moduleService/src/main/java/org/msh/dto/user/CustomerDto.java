package org.msh.dto.user;

import jakarta.persistence.Column;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerDto {
    private Long Id;
    private String firstName;
    private String lastName;
    private String tel;
    private String address;
    private String postalCode;

}
