package org.msh.entity.file;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "file")
@Table(name = "tbl_file")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FileEnt {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id_file")
    private Long id;

    @Column(length = 1000, nullable = false)
    private String title;

    @Column(length = 1000, nullable = false)
    private String path;

}
