package org.msh.entity.file;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "FileEnt")
@Table(name = "tbl_file")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FileEnt {

    @Column(name="id_file")
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(length = 1000, nullable = false)
    private String title;

    @Column(length = 1000, nullable = false)
    private String path;

}
