package org.msh.entity.site;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "content")
@Table(name = "tbl_content")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ContentEnt {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id_content")
    private Long id;

    @Column(length = 1000, nullable = false)
    private String title;

    @Column(length = 1000, nullable = false)
    private String value;

}
