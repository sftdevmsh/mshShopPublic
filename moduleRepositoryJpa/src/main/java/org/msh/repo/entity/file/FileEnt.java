package org.msh.repo.entity.file;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

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
    private String name;

    @Column(length = 1000, nullable = false)
    private String path;

    private String uuid;
    private String extension;
    private String contentType;
    private Long size;
    private LocalDateTime createDate;

}
