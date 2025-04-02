package org.msh.api.model;

import lombok.*;
import org.msh.api.enums.MyHttpStatus;

import java.util.List;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse<T> {
    private String msg;
    private MyHttpStatus status;
    private T tdata;
//My Api response
}
