package org.msh.wrapper;

import lombok.*;
import org.msh.enums.MyHttpStatus;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponseWrapper<T> {
    private String msg;
    private MyHttpStatus status;
    private T tdata;
//My Api response
}
