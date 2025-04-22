package org.msh.entity.user;

import jakarta.persistence.*;
import lombok.*;
import org.msh.entity.invoice.InvoiceEnt;

import java.time.LocalDateTime;
import java.util.Set;


@Entity(name = "UserEnt")
@Table(name = "tbl_user")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserEnt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Long Id;

    @Column(length = 100, nullable = false)
    private String username;
    @Column(length = 100)
    private String password;

//    @Column(length = 100)
//    private String firstName;
//    @Column(length = 100)
//    private String lastName;

    @Column(length = 11, nullable = false)
    private String mobile;
    @Column(length = 50)
    private String email;

    private LocalDateTime registerTime;
    private Boolean enabled;


//    @OneToOne
//    private UserEnt userEnt;//customer
    @OneToOne
    private CustomerEnt customerEnt;//customer

    @ManyToMany
    @JoinTable(name = "rel_user_role"
            , joinColumns = @JoinColumn(name = "id_user")
            , inverseJoinColumns = @JoinColumn(name = "id_role")
            )
    private Set<RoleEnt> roleEnts;


//    @OneToMany
//    private Set<InvoiceEnt> invoiceEnts;

}
