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
public class PanelApiResponseWrapper<T> {

    private String msg;
    private MyHttpStatus status;
    private T tdata;
    private Integer total;
    private int totalCount = 0;
    private int totalPages = 0;

}
