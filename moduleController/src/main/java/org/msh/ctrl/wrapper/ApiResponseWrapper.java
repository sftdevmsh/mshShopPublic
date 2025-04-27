package org.msh.ctrl.wrapper;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.msh.ctrl.enums.MyHttpStatus;

@Setter
@Getter
//@Builder
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponseWrapper<T> {
    private String msg;
    private MyHttpStatus status;
    private T tdata;
//My Api response
}
