package org.msh.serv.dto.file;

//import javax.persistence.*;
import lombok.*;

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
