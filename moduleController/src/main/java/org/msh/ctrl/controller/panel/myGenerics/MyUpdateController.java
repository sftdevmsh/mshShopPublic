package org.msh.ctrl.controller.panel.myGenerics;

import org.msh.common.exceptions.MyExc;
import org.msh.ctrl.wrapper.PanelApiResponseWrapper;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface MyUpdateController<T> {
    @PutMapping("/upd")
    PanelApiResponseWrapper<T> updateCtrl(@RequestBody T t) throws MyExc;
}
