package org.msh.controller.panel.myGenerics;

import org.msh.exceptions.MyExc;
import org.msh.wrapper.PanelApiResponseWrapper;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface MyUpdateController<T> {
    @PutMapping("/upd")
    PanelApiResponseWrapper<T> updateCtrl(@RequestBody T t) throws MyExc;
}
