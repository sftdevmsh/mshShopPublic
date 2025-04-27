package org.msh.ctrl.controller.panel.myGenerics;

import org.msh.common.exceptions.MyExc;
import org.msh.ctrl.wrapper.PanelApiResponseWrapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface MyAddController<T> {
    @PostMapping("/add")
    PanelApiResponseWrapper<T> addCtrl(@RequestBody T t) throws MyExc;
}
