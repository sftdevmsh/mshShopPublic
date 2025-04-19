package org.msh.wrapper;

import lombok.*;
import org.msh.enums.MyHttpStatus;

@Setter
@Getter
@Builder
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
