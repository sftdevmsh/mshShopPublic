package org.msh.entity.user;

import jakarta.persistence.*;
import lombok.*;


@Entity(name = "CustomerEnt")
@Table(name = "tbl_customer")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerEnt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_customer")
    private Long Id;

//    @Column(length = 100, nullable = false)
//    private String username;
//    @Column(length = 100)
//    private String password;

    @Column(length = 100)
    private String firstName;
    @Column(length = 100)
    private String lastName;

//    @Column(length = 11, nullable = false)
//    private String mobile;
//    @Column(length = 50)
//    private String email;

    @Column(length = 1000)
    private String tel;
    @Column(length = 1000)
    private String address;
    @Column(length = 1000)
    private String postalCode;
}
