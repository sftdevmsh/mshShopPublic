package org.msh.dto.file;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FileDto {

    private Long id;

    private String title;

    private String path;

}
