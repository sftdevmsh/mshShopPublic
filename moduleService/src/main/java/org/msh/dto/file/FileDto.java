package org.msh.dto.file;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FileDto {

    private Long id;

    private String name;

    private String path;

    private String uuid;
    private String extension;
    private String contentType;
    private Long size;
    //private LocalDateTime createDate;

}
